.PHONY: help
help:
	@ echo ''
	@ echo 'Manning Electrical Grid Project: Help'
	@ echo '====================================='
	@ sed -E -n '/^[a-z].*\#\>/p' Makefile | sed -E $$'s/:.*#>[ ]*/\\\n  /'


##> Docker

.PHONY: docker-compose-up
docker-compose-up:  #> Run Docker environment with Kafka, ZooKeeper, and Avro Schema Registry
	docker-compose -f docker-compose.yaml up

.PHONY: docker-compose-down
docker-compose-down:  #> Shutdown Docker environment
	docker-compose -f docker-compose.yaml down

.PHONY: up
up: docker-compose-up  #> Alias for docker-compose-up

.PHONY: down
down: docker-compose-down  #> Alias for docker-compose-down


##> Web Server

.PHONY: run-server
run-server:  #> Run web server to ingest events into Kudu
	java -jar target/manning-electrical-grid-1.0-SNAPSHOT.jar server config.yaml

##> Data generator

.PHONY: generate-events
generate-events:  #> Run events generator and post data to the target URL
	java -jar event-generators-1.1-SNAPSHOT-jar-with-dependencies.jar events -t http://localhost:9000/events

.PHONY: generate-pricing
generate-pricing:  #> Run pricing generator and post data to the target URL
	java -jar event-generators-1.1-SNAPSHOT-jar-with-dependencies.jar pricing -t http://localhost:9000/pricing

##> Kafka

.PHONY: consume-events
consume-events:  #> Consume messages from events topic and print them into console
	docker exec -it schema-registry kafka-avro-console-consumer --topic events --bootstrap-server broker:9092 --from-beginning
