FROM openjdk:21-jdk-slim

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

COPY pom.xml ./
COPY src ./src/

# Copiar o build do Angular (injetado pelo pipeline)
COPY src/main/resources/static ./src/main/resources/static

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/AppCadastroPessoas-0.0.1-SNAPSHOT.jar"]