# =========================
# Etapa 2: Build do frontend (Angular)
# =========================
FROM node:20-alpine AS frontend-builder
WORKDIR /frontend
COPY Frontend/package*.json ./
RUN npm install
COPY Frontend/ .
RUN npm run build -- --configuration production

# =========================
# Etapa 1: Build do backend (Maven)
# =========================
FROM openjdk:21-jdk-slim AS backend-builder
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY Backend/pom.xml ./
COPY Backend/src ./src
# Copia o build Angular (independente do nome da pasta)
COPY --from=frontend-builder /frontend/dist/* ./src/main/resources/static/
RUN mvn clean package -DskipTests


# =========================
# Etapa 3: Imagem final com app completo
# =========================
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copia o JAR do backend
COPY --from=backend-builder /app/target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar ./app.jar

# Expor porta padrão do Spring Boot
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
