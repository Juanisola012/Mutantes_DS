# Etapa de construcción
FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Etapa final
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/build/libs/mutant-detector-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que correrá tu aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
