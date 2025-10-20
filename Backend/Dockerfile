FROM openjdk:21-jdk-slim

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar arquivos do projeto
COPY pom.xml .
COPY src ./src

# Criar diretório para arquivos estáticos e copiar frontend
RUN mkdir -p src/main/resources/static
COPY frontend-build/ ./src/main/resources/static/

# Verificar se tudo foi copiado corretamente
RUN echo "=== Verificando estrutura de arquivos ===" && \
    ls -la && \
    echo "=== Arquivos do Frontend ===" && \
    ls -la src/main/resources/static/ && \
    echo "=== Arquivos Java ===" && \
    find src -name "*.java" | head -3

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar"]