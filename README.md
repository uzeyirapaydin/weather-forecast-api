# Little weather forecast - Case Study


## Technology Stack

- Java 17
- Spring Boot
- Maven
- Tomcat
- Swagger UI
- Docker

## Installation

To start project the following commands needs to be run on project path.
```shell
mvn clean install or  mvn clean install -DskipTests

docker-compose up -d
```

All done!

The backend service runs on local http://localhost:8080.

The backend service runs on Docker http://localhost:8090.

After deployment, to show and test API endpoints the followings can be used:

Local 
- **Swagger Documentation:** [Open API Endpoint](http://localhost:8080/swagger-ui/index.html)
- **Postman Collection:** [Download](https://raw.githubusercontent.com/uzeyirapaydin/weather-forecast-api/main/weather-forecast-api-local.postman_collection.json.json)


Docker
- **Swagger Documentation:** [Open API Endpoint](http://localhost:8090/weather-forecast-api/swagger-ui/index.html)
- **Postman Collection:** [Download](https://raw.githubusercontent.com/uzeyirapaydin/weather-forecast-api/main/weather-forecast-api-docker.postman_collection.json.json)

## Usage

### Weather Forecast Curl Request Example

There is an endpoint(`POST /api/forecast/next48h/Istanbul`) to forecast weather for next 48 hours.

curl -X 'GET' \
  'http://localhost:8090/weather-forecast-api/api/forecast/next48h/Istanbul' \
  -H 'accept: application/json' \
  -H 'X-API-KEY: IngWeatherForecast' \
  -H 'X-API-SECRET: IngWeatherForecastCaseStudy2023'



