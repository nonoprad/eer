curl -H "Content-Type: application/json" -X POST -d '{"raisonSociale":"Carrefour","adresse":"30 RUE DE LA PAIE"}' http://localhost:8080/eer'

java -Dswarm.http.port=9999 -Denv.name=DH -jar target/entree-en-relation-0.0.1-SNAPSHOT-swarm.jar
