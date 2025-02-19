# Utilise une image de base OpenJDK 8 pour exécuter une application Java
FROM openjdk:8-jre-alpine

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copie le fichier JAR généré par Maven/Gradle dans le conteneur
# Assurez-vous que "eureka-server-0.0.1-SNAPSHOT.jar" correspond au nom exact de votre JAR
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server.jar

# Expose le port que le serveur Eureka utilise (par défaut 8761)
EXPOSE 8761

# Définit la commande de démarrage de l'application
CMD ["java", "-jar", "eureka-server.jar"]
