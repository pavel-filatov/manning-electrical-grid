.PHONY: help
help:
	@ echo ''
	@ echo 'Manning Electrical Grid Project: Help'
	@ echo '====================================='
	@ sed -E -n '/^[a-z].*\#\>/p' Makefile | sed -E $$'s/:[ ]*#>[ ]*/\\\n  /'

.PHONY: docker-compose-up
docker-compose-up:  #> Run Docker environment with Kafka, ZooKeeper, and Avro Schema Registry
	docker-compose -f docker-compose.yaml up

.PHONY: docker-compose-down
docker-compose-down:  #> Shutdown Docker environment
	docker-compose -f docker-compose.yaml down

.PHONY: run-server
run-server:  #> Run web server to ingest events into Kudu
	java -jar target/manning-electrical-grid-1.0-SNAPSHOT.jar server config.yaml

.PHONY: generate-events
generate-events:  #> Run events generator and post data to the target URL
	java -jar event-generators-1.1-SNAPSHOT-jar-with-dependencies.jar events -t http://localhost:9000/events

.PHONY: generate-pricing
generate-pricing:  #> Run pricing generator and post data to the target URL
	java -jar event-generators-1.1-SNAPSHOT-jar-with-dependencies.jar pricing -t http://localhost:9000/pricing

.PHONY: consume-events
consume-events:  #> Consume messages from events topic
	docker exec -it broker kafka-console-consumer --topic events --bootstrap-server broker:9092 --from-beginning
