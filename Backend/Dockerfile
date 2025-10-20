FROM openjdk:21-jdk-slim

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar todos os arquivos (incluindo pom.xml, src, e frontend-build)
COPY . .

# Criar diretório para arquivos estáticos e copiar frontend
RUN mkdir -p src/main/resources/static
COPY frontend-build/ ./src/main/resources/static/

# Verificar se os arquivos estão presentes
RUN echo "=== Verificando estrutura ===" && \
    ls -la && \
    echo "=== pom.xml existe? ===" && \
    test -f pom.xml && echo "✅ pom.xml encontrado" || echo "❌ pom.xml não encontrado" && \
    echo "=== Arquivos do Frontend ===" && \
    ls -la frontend-build/ && \
    echo "=== Arquivos Java ===" && \
    find src -name "*.java" | head -3

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar"]