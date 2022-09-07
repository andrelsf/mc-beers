# Getting Started

### Requests

Requests with `httpie`

```shell
http --json POST :8090/api/v1/beers < httpRequests/postCreateANewBeer.json

HTTP/1.1 201 Created
Connection: keep-alive
Content-Length: 0
Date: Wed, 07 Sep 2022 00:02:29 GMT
Location: /api/v1/beers/d3195424-5545-4ca5-a574-52a4a0d366e9
```

```shell
http --json GET :8090/api/v1/beers/d3195424-5545-4ca5-a574-52a4a0d366e9
```

```shell
http --json GET :8090/api/v1/beers offset==0 limit==10
```

```shell
http --json PUT :8090/api/v1/beers/baca9aa9-f8b8-4155-8876-b584e5e7ae0c < httpRequests/putUpdateABeer.json 
HTTP/1.1 204 No Content
Date: Wed, 07 Sep 2022 00:45:18 GMT
```

```shell
http --json DELETE :8090/api/v1/beers/d3195424-5545-4ca5-a574-52a4a0d366e9
HTTP/1.1 204 No Content
Date: Wed, 07 Sep 2022 00:45:18 GMT
```

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

