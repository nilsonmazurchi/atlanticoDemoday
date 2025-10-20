# ============================================================
# STAGE 1 — Build do Frontend Angular
# ============================================================
FROM node:20-alpine AS frontend-builder

WORKDIR /frontend
COPY Frontend/package*.json ./
RUN npm install
COPY Frontend/ .
RUN npm run build --configuration production

# ============================================================
# STAGE 2 — Build do Backend Spring Boot com Maven
# ============================================================
FROM maven:3.9.6-eclipse-temurin-21 AS backend-builder

WORKDIR /backend
COPY Backend/pom.xml .
COPY Backend/src ./src

# Copia o build Angular para dentro do backend antes do package
COPY --from=frontend-builder /frontend/dist/cadastro-pessoas ./src/main/resources/static

RUN mvn clean package -DskipTests

# ============================================================
# STAGE 3 — Runtime Image (final)
# ============================================================
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app
COPY --from=backend-builder /backend/target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
