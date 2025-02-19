# Utilise une image de base OpenJDK pour exécuter une application Java
FROM openjdk:8-jre-alpine

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le fichier JAR généré dans le conteneur
COPY target/patient-service-0.0.1-SNAPSHOT.jar app.jar

# Expose le port que le service utilise
EXPOSE 8080

# Définit la commande de démarrage de l'application
CMD ["java", "-jar", "app.jar"]
