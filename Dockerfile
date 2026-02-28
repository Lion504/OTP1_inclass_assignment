# Multi-stage build for Java test project
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml first for better layer caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package

# Runtime stage - for running tests or extracting artifacts
FROM maven:3.9-eclipse-temurin-21 AS test
WORKDIR /app

# Copy built artifacts and source for test execution
COPY --from=build /app/target ./target
COPY --from=build /app/pom.xml ./pom.xml
COPY src ./src

# Default command runs tests
CMD ["mvn", "test"]
