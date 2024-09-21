# Etapa de Build
FROM ubuntu:latest AS build

# Atualiza o apt-get e instala OpenJDK 22 e Maven
RUN apt-get update
RUN apt-get install openjdk-22-jdk maven -y

# Copia todos os arquivos do projeto para dentro da imagem
COPY . .

# Executa o Maven para construir o JAR
RUN mvn clean install

# Etapa de Execução
FROM openjdk:22-jdk-slim

# Expondo a porta 8080 para o Spring Boot
EXPOSE 8080

# Copia o JAR gerado na etapa anterior para a imagem de execução
COPY --from=build /target/deploy_render-1.0.0.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
