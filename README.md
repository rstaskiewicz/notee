# Start back-end in the Docker containers

**Requirements:** 
- Docker and Docker-compose

1. Build Docker images for api and auth servers
```
$ cd api
$ ./gradlew buildDockerImage
$ cd ..
$ cd auth
$ ./gradlew buildDockerImage
$ cd ..
```
2. Run docker-compose (in the main folder)
```
$ docker-compose up -d
```

- API server is listen on the port 8080
- Postgres DB is listen on the port 5432


# Registration

1. http://localhost:8081/oauth/token

Example json:
```
{
	"email": "hoxaver@gmail.com",
	"username": "Hoxaver Er",
	"password": "pass",
	"matchingPassword": "pass"
}
```

2. Confirm by the link from a confirmation email


# Authentication

1. http://localhost:8081/oauth/token

Example params:
```
username: hoxaver@gmail.com
password: pass
client_id: js_client
client_secret: secret
grant_type: password
```

2. Test: localhost:8080/api/test with Authorization header


# Error handling

1. Validation error JSON:

```
{
    "title": "Validation error",
    "status": 400,
    "detail": "Input validation failed",
    "timestamp": "2019-11-05T13:23:51.560Z",
    "exceptionName": "MethodArgumentNotValidException",
    "errors": {
        "registerUserAccountRequest": [
            "Passwords do not match"
        ],
        "email": [
            "Email address is already in use"
        ]
    }
}
```

2. Resource not found example JSON
```
{
    "title": "Resource not found",
    "status": 404,
    "detail": "Item does not exists",
    "timestamp": "2019-11-05T12:59:46.096Z",
    "exceptionName": "ResourceNotFoundException",
    "errors": {}
}
```
