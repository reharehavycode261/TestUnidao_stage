# Project Documentation

## Overview

This project is designed with a service-oriented architecture and MVC pattern. It now supports complex data types including JSON, Array, and UUID.

## Features

1. **Support for Complex Data Types**
   - JSON: Utilizes Gson library for JSON parsing.
   - Array: Handled as collections in entities.
   - UUID: Supported via `java.util.UUID`.

2. **Entities**
   - `Region`: Includes metadata as JSON, UUID, and subRegions as an Array.
   - `Territorie`: Includes attributes as JSON, UUID, and neighboringRegions as an Array.

## Architectural Pattern

- **Layered Architecture**: Includes services and repositories.
- **MVC Pattern**: Observed in controller implementations.

## Getting Started

- Ensure `gson-2.10.1.jar` is included in your project dependencies.

## Prerequisites

- Java Development Kit (JDK)
- Apache Maven (for build automation)

## Building the Project

- Run `mvn clean install` to compile and build the project.

## Testing

- Comprehensive unit tests have been included to ensure functionality works as expected.

## Contact

For more information, please contact the development team.