# Etapa de Build
FROM ubuntu:latest AS build


RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install


# Etapa de Execução
FROM openjdk:17-jdk-slim

# Expondo a porta 8080 para o Spring Boot
EXPOSE 8080

# Copia o JAR gerado na etapa anterior para a imagem de execução
COPY --from=build /target/deploy_render-1.0.0.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]


