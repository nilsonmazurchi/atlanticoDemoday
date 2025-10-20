# Estágio 1: Build do Frontend
FROM node:20-alpine as frontend-builder

WORKDIR /app

COPY Frontend/package.json Frontend/package-lock.json ./
RUN npm ci

COPY Frontend/ .
RUN npm run build -- --configuration production

# Estágio 2: Build do Backend
FROM openjdk:21-jdk-slim

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar arquivos do projeto Java
COPY pom.xml .
COPY src ./src

# Copiar build do frontend do estágio anterior
COPY --from=frontend-builder /app/dist/cadastro-pessoas/ ./src/main/resources/static/

# Verificar se os arquivos foram copiados
RUN echo "=== Verificando arquivos estáticos ===" && \
    ls -la src/main/resources/static/ && \
    echo "Total de arquivos:" && \
    find src/main/resources/static/ -type f | wc -l

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar"]