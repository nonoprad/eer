package com.socgen.application.digital;

import com.socgen.application.digital.controller.EntreeEnRelationController;
import com.socgen.application.digital.modele.EntreeEnRelation;
import com.socgen.application.digital.service.EntreeEnRelationManager;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.postgresql.Driver;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.config.datasources.JDBCDriver;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;

public class Main {



    public static void main(String[] args) throws Exception {
        System.out.println("Running " + Main.class.getCanonicalName() + ".main");

        Swarm swarm = new Swarm();


        // Configure the Datasources subsystem with a driver
        // and a datasource
        swarm.fraction(datasourceWithPostgresql());


        swarm.fraction(new JPAFraction().defaultDatasource(("MyDS")));

        swarm.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addAllDependencies();
        deployment.addClasses(EntreeEnRelationController.class, EntreeEnRelationManager.class, EntreeEnRelation.class);
        deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");
        deployment.addResource(EntreeEnRelationController.class);

        // Start the swarm and deploy the default war
        swarm.start().deploy(deployment);
    }

        private static DatasourcesFraction datasourceWithPostgresql() {
        return new DatasourcesFraction()
                .jdbcDriver(new JDBCDriver("org.postgresql")
                        .driverName("org.postgresql")
                        .driverClassName("org.postgresql.Driver")
                        .driverXaDatasourceClassName("org.postgresql.xa.PGXADataSource")
                        .driverModuleName("org.postgresql.postgresql"))
                .dataSource("MyDS", (ds) -> {
                    ds.driverName("org.postgresql");
                    ds.connectionUrl("jdbc:postgresql://localhost:5555/postgres");
                    ds.userName("postgres");
                    ds.password("postgres");
                });
    }


}
