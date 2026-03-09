# --- Frontend Build ---
FROM node:20-alpine AS frontend-build
WORKDIR /app/frontend
COPY reactfrontend/package*.json ./
RUN npm install
COPY reactfrontend/ .
RUN npm run build

# --- Backend Build ---
FROM maven:3.9.6-eclipse-temurin-21 AS backend-build
WORKDIR /app/backend
COPY springbackend/pom.xml .
RUN mvn dependency:go-offline
COPY springbackend/src ./src
# Kopiere das Frontend-Build in den static-Ordner von Spring Boot
COPY --from=frontend-build /app/frontend/dist ./src/main/resources/static
RUN mvn clean package -DskipTests

# --- Final Image ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=backend-build /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]