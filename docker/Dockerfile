FROM eclipse-temurin:21-jdk-jammy

# Configuración del directorio de trabajo
ENV APP_HOME=/apiordersapp
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

# Puerto expuesto por defecto en Spring Boot para este proyecto
EXPOSE 8080

# Copiar el JAR generado por Maven
COPY target/api-orders-*.jar app.jar

# Usuario no-root para asegurar las buenas prácticas en la auditoría
RUN useradd -m appuser && chown -R appuser:appuser $APP_HOME
USER appuser

# Comando de ejecución estándar
ENTRYPOINT [ "java", "-jar", "app.jar" ]