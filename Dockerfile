# Usa una imagen base de Java
FROM adoptopenjdk/openjdk17:alpine-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/Servicios-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que se ejecuta tu aplicación
EXPOSE 8080

# Comando para ejecutar tu aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "app.jar"]
