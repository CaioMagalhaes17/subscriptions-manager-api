# 1. imagem com Java
FROM eclipse-temurin:21-jdk-alpine

# 2. diretório de trabalho
WORKDIR /app

# 3. copia o jar
COPY target/*.jar app.jar

# 4. expõe a porta da aplicação
EXPOSE 8080

# 5. comando de start
ENTRYPOINT ["java", "-jar", "app.jar"]