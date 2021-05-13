# Number Conversion API

Simple decimal to roman / hexadecimal converter RESTful API.

### Project setup
This is scala application so you must have Scala installed on your system.

1. Clone this repository to your machine:
```git clone https://github.com/rochala/number-conversion.git```
1. Start application by running:
```sbt run```

Application also provide basic unit tests which can be run by ```sbt test```

### API

API provides 2 endpoints:

* localhost:8080/healthcheck - to test if API is working
* localhost:8080/api/{conversion type}/{integer number} - endpoint to convert numbers

API provides 2 conversion types: "roman" and "hexadecimal".

Example usage:
```localhost:8080/api/roman/123/``` should result as *CXXIII*

### Dependencies

* akkaHTTP 
* scalatest
* circe
