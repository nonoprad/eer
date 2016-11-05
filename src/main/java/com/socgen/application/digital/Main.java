package com.socgen.application.digital;

import com.socgen.application.digital.controller.EntreeEnRelationController;
import com.socgen.application.digital.modele.EntreeEnRelation;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;

public class Main {



    public static void main(String[] args) throws Exception {
        System.out.println("Running " + Main.class.getCanonicalName() + ".main");

        Swarm swarm = new Swarm();

        JavaArchive driverArchiveToDeploy;

        String environnement = System.getProperty("env.name","DH");

        if (!environnement.equals("DB")) {
            swarm.fraction(buildDataSourcePostgreSQL());
            swarm.fraction(new JPAFraction().defaultDatasource(("PostgreSQLDS")));
            driverArchiveToDeploy = Swarm.artifact("org.postgresql:postgresql", "postgresql");

            swarm.start();
            swarm.deploy(driverArchiveToDeploy);

        }
        else {
            swarm.fraction(new JPAFraction().defaultDatasource("ExampleDS"));
            swarm.start();
        }

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addAllDependencies(true);
        deployment.addClasses(EntreeEnRelationController.class, EntreeEnRelation.class);
        deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
        deployment.addAsWebInfResource(new ClassLoaderAsset("log4j.xml", Main.class.getClassLoader()), "classes/log4j.xml");
        deployment.addResource(EntreeEnRelationController.class);

        // Start the swarm and deploy the default war
        swarm.deploy(deployment);
    }


    private static DatasourcesFraction buildDataSourcePostgreSQL() {
        return new DatasourcesFraction()
                .jdbcDriver("org.postgresql", (d) -> {
                    d.driverClassName("org.postgresql.Driver");
                    d.xaDatasourceClass("org.postgresql.xa.PGXADataSource");
                    d.driverModuleName("org.postgresql");
                })
                .dataSource("PostgreSQLDS", (ds) -> {
                    ds.driverName("org.postgresql");
                    ds.connectionUrl("jdbc:postgresql://localhost:5555/postgres");
                    ds.userName("postgres");
                    ds.password("postgres");
                });
    }

}
