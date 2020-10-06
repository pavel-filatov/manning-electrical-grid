.PHONY: help
help:
	@ echo 'Manning Electrical Grid Project: Help'
	@ echo '-------------------------------------'
	@ sed -E -n '/^[a-z].*\#\>/p' Makefile | sed -E $$'s/:[ ]*#>[ ]*/\\\n  /'

.PHONY: docker-compose-up
docker-compose-up:  #> Run Docker environment with Kafka, ZooKeeper, and Avro Schema Registry
	docker-compose -f docker-compose.yaml up

.PHONY: docker-compose-down
docker-compose-down:  #> Shutdown Docker environment
	docker-compose -f docker-compose.yaml down
