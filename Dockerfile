# ===============================
# Etapa 1 - Build do Frontend Angular
# ===============================
FROM node:20-alpine AS frontend-builder

WORKDIR /frontend

COPY package.json package-lock.json ./
RUN npm install

COPY . .
RUN npm run build --configuration production


# ===============================
# Etapa 2 - Build do Backend Spring Boot
# ===============================
FROM openjdk:21-jdk-slim AS backend-builder

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

COPY pom.xml ./
COPY src ./src/

# Copiar o build Angular gerado na etapa anterior para dentro do backend
COPY --from=frontend-builder /frontend/dist/cadastro-pessoas ./src/main/resources/static

# Empacotar o JAR
RUN mvn clean package -DskipTests


# ===============================
# Etapa 3 - Container Final
# ===============================
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=backend-builder /app/target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
