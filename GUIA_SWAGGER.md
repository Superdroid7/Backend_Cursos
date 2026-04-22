# Generación Automática de Documentación Swagger (OpenAPI)

Para generar y visualizar la documentación interactiva de la API, el proceso es completamente automático gracias a Spring Boot y `springdoc-openapi`. 

La herramienta escanea tu código al iniciar y construye la documentación en vivo. No necesitas crear ni actualizar archivos a mano. Sigue estos dos sencillos pasos:

## 1. Comando a ejecutar

El único "comando" que necesitas ejecutar es **arrancar la aplicación Spring Boot**. Puedes hacerlo de dos formas:

**Opción A: Desde tu IDE (Recomendado)**
Abre el proyecto en IntelliJ IDEA, busca la clase principal `CatalogApplication.java` y haz clic en el botón verde de "Play" (Run 'CatalogApplication').

**Opción B: Desde la Terminal**
Si prefieres usar la consola, ubícate en la raíz del proyecto y ejecuta el servidor de desarrollo de Gradle:

En Windows:
```powershell
.\gradlew.bat bootRun
```

En Mac/Linux:
```bash
./gradlew bootRun
```

## 2. Dónde ver el archivo de documentación

Una vez que la aplicación esté corriendo (verás en la consola que Tomcat inició en el puerto `8080`), la documentación ya se habrá generado en la memoria del servidor.

Para interactuar con ella, abre tu navegador web favorito (Chrome, Edge, Firefox, etc.) e ingresa a la siguiente URL:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### ¿Qué encontrarás allí?
Se abrirá la interfaz visual de **Swagger UI**. Desde ahí podrás:
- Explorar todos los endpoints disponibles, como obtener la lista de cursos (`GET`) o crear uno nuevo (`POST`).
- Ver los esquemas (modelos DTO) para entender la estructura exacta de los datos que debes enviar y recibir.
- Hacer peticiones de prueba reales directamente desde el navegador usando el botón **"Try it out"**, sin necesidad de herramientas como Postman.
