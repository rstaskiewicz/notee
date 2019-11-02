# Start back-end in the Docker containers

**Requirements:** 
- Docker and Docker-compose

1. Build Docker image for api server
```
$ cd api
$ ./gradlew buildDockerImage
```
2. Run docker-compose (in the main folder)
```
$ docker-compose up -d
```

- API server is listen on the port 8080
- Postgres DB is listen on the port 5432
