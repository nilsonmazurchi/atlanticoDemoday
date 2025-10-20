# ============================================================
# STAGE 1 — Build do Frontend Angular
# ============================================================
FROM node:20-alpine AS frontend-builder

WORKDIR /frontend
COPY package.json package-lock.json ./
RUN npm install
COPY Frontend/. .
RUN npm run build --configuration production

# ============================================================
# STAGE 2 — Build do Backend Spring Boot com Maven
# ============================================================
FROM openjdk:21-jdk-slim AS backend-builder

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

COPY Backend/pom.xml ./
COPY Backend/src ./src/

# Copia o build Angular para dentro do backend antes do package
COPY --from=frontend-builder /frontend/dist/cadastro-pessoas ./src/main/resources/static

RUN mvn clean package -DskipTests

# ============================================================
# STAGE 3 — Runtime Image (final)
# ============================================================
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=backend-builder /app/target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
