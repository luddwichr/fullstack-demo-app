https://reflectoring.io/spring-boot-docker/

Start DB to run backend as Jar, not as docker container:
docker-compose -f backend/docker-compose.postgres.yml up
	=> append "-d" to run in daemon mode