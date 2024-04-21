# Usa una imagen base de OpenJDK para Java 17
FROM openjdk:17

# Establece el directorio de trabajo en /app
WORKDIR /Servicios

RUN clean package

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/Servicios-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 en el contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]