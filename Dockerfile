# =========================
# Etapa 1: Build do backend (Maven)
# =========================
FROM maven:3.9.8-eclipse-temurin-21 AS backend-build
WORKDIR /app
COPY Backend/pom.xml .
COPY Backend/src ./src
RUN mvn -B -DskipTests clean package

# =========================
# Etapa 2: Build do frontend (Angular)
# =========================
FROM node:18 AS frontend-build
WORKDIR /frontend
COPY Frontend/package*.json ./
RUN npm ci
COPY Frontend/ .
RUN npm run build -- --configuration production

# =========================
# Etapa 3: Imagem final com app completo
# =========================
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o JAR do backend
COPY --from=backend-build /app/target/*.jar app.jar

# Copia o frontend buildado (Angular) para servir como estático
RUN mkdir -p /app/frontend
COPY --from=frontend-build /frontend/dist/cadastro-pessoas /app/frontend

# Expor porta padrão do Spring Boot
EXPOSE 8080

# Variáveis padrão
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Comando de execução
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

