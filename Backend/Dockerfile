# ===== Etapa 1: Build do Frontend (Angular) =====
FROM node:20-alpine AS frontend-build
WORKDIR /frontend

# Copia os arquivos de dependência primeiro (melhor cache)
COPY ../Frontend/package*.json ./
RUN npm install

# Copia o restante do projeto Angular
COPY ../Frontend/ .

# Gera o build de produção do Angular
RUN npm run build --configuration production

# ===== Etapa 2: Build do Backend (Spring Boot) =====
FROM maven:3.9.8-eclipse-temurin-21 AS backend-build
WORKDIR /backend

# Copia o código do backend
COPY pom.xml .
COPY src ./src

# Cria o diretório estático e copia o build do Angular para dentro do Spring Boot
RUN mkdir -p src/main/resources/static
COPY --from=frontend-build /frontend/dist /backend/src/main/resources/static

# Faz o build do backend com Maven
RUN mvn clean package -DskipTests

# ===== Etapa 3: Runtime final (imagem leve para produção) =====
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copia apenas o JAR final gerado
COPY --from=backend-build /backend/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]