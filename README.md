# Tanzu Platform Demo

A Spring Boot application with a Vue.js frontend, designed to run on Cloud Foundry / Tanzu Platform.

## Prerequisites

- Java 11
- Maven 3.6+

## Tech Stack

- Spring Boot
- Spring MVC
- Spring Actuator
- Log4j2
- Vue 3 (CDN)
- java-cfenv

## Project Structure

```
src/main/
├── java/com/example/tpdemo/
│   ├── TpDemoApplication.java      # Application entry point
│   ├── HelloController.java        # REST controller (/hello)
│   ├── CfEnvService.java           # Cloud Foundry environment reader
│   ├── CfEnvInfo.java              # CF environment data model
│   └── CfEnvController.java        # REST controller (/cfenv)
└── resources/
    ├── application.properties       # Server and actuator configuration
    ├── log4j2.xml                   # Log4j2 configuration
    └── static/
        ├── index.html               # Vue.js single-page app
        ├── css/style.css            # Styles
        └── js/app.js                # Vue app logic
```

## Running the Application

```bash
mvn spring-boot:run
```

Open [http://localhost:8080](http://localhost:8080) in your browser.

## Endpoints

| Path | Description |
|------|-------------|
| `/` | Vue.js frontend |
| `/hello` | REST endpoint returning a greeting |
| `/cfenv` | Cloud Foundry environment info (returns 404 when not on CF) |
| `/actuator/info` | Application info (Spring Boot version, Java version) |
| `/actuator/health` | Health check endpoint |

## Frontend Features

The web UI displays:

- **Greeting message** - Response from `/hello` endpoint
- **Runtime Info** - Spring Boot and Java versions from actuator
- **Application Info** - CF application details (name, ID, instance, space, URIs)
- **Bound Services** - List of services bound to the application

When not running on Cloud Foundry, the CF-specific sections show placeholder messages.

## Cloud Foundry Environment

The application uses `java-cfenv` to read Cloud Foundry environment variables:

- `VCAP_APPLICATION` - Application metadata
- `VCAP_SERVICES` - Bound service credentials

At startup, the `CfEnvService` logs the CF environment details and makes them available via the `/cfenv` endpoint.

## Logging

Logs are written using Log4j2. The configuration is in `src/main/resources/log4j2.xml`.