FROM openjdk:21-jdk-slim

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

COPY pom.xml ./
COPY src ./src/

# Criar diretório para arquivos estáticos
RUN mkdir -p src/main/resources/static

# Copiar o build do Angular (injetado pelo pipeline)
COPY frontend-build/ ./src/main/resources/static

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar"]