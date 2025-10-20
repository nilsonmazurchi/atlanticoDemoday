# Estágio 1: Build do Frontend
FROM node:20-alpine as frontend-build

WORKDIR /app

# Copiar arquivos do frontend
COPY ../../Frontend/package.json ../../Frontend/package-lock.json ./
RUN npm ci

COPY ../../Frontend/ .
RUN npm run build -- --configuration production

# Estágio 2: Build do Backend
FROM openjdk:21-jdk-slim

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar arquivos do backend
COPY pom.xml .
COPY src ./src

# Copiar build do frontend do estágio anterior
COPY --from=frontend-build /app/dist/cadastro-pessoas/ ./src/main/resources/static/

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar"]