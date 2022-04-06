# Demo Application

This application demonstrates how to set up a web application. 
The backend consists of a Spring Boot web application with an embedded Tomcat server and a Postgres database.
The frontend consists of a single page application built with Angular and nginx as reverse proxy.
To run all parts of the application, docker compose is used.

## How to start the application

### During development (local)

- Start Postgres docker container: `docker-compose -f backend/docker-compose.postgres.yml up` (append `-d` to run in daemon mode)
- Start Backend: `cd backend && mvnw spring-boot:run`
- Start Frontend (with webpack dev server, not nginx): `cd frontend && npm run start`

### Local production like

- run `./build-an-run-local.sh` (see script for more on how things work)

## Reverse proxy
 
To avoid CORS issues due to different ports of frontend and backend, a reverse proxy is employed.
This is done by using nginx, shipped within the frontend Dockerfile.
See [Dockerfile](frontend/Dockerfile) and [nginx.conf](frontend/nginx.conf) for more.

For development, the webpack dev server ist configured to proxy requests to `http://localhost:8080`.
See [here](https://angular.io/guide/build#proxying-to-a-backend-server) for more.

## HTTPS

To prevent man-in-the-middle attacks and sniffing of plain-text network traffic, SSL is configured in this demo application.
The following resources are helpful to understand how HTTPS can be configured:
- https://www.baeldung.com/openssl-self-signed-cert
- https://medium.com/@rubenvermeulen/running-angular-cli-over-https-with-a-trusted-certificate-4a0d5f92747a
- https://nginx.org/en/docs/http/configuring_https_servers.html

Run `./create-self-signed-certificate.sh ` to create a self signed certificate for use in a non-production environment.

## Authentication / Authorization

To control access to sensitive resources, authentication and authorization is required.
In this demo, authentication and authorization using via user credentials stored in a database. 
The implementation strongly relies on features provided by Spring Security.

The following resources are helpful to understand how authentication can be configured:
- https://reflectoring.io/spring-security-password-handling/
- https://spring.io/guides/tutorials/spring-security-and-angular-js/

TODO: so far, I only implemented the backend part - the frontend part is currently missing and breaking the app

## Room for improvement

- Dockerize Spring Boot not as minimalistic as currently done (e.g. read [this guide](https://reflectoring.io/spring-boot-docker/))
- Give [this](https://snyk.io/blog/best-practices-to-build-java-containers-with-docker/) a read