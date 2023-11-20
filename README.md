<h1> Api de Datos Meteorologicos </h1>

Una API  construida con Spring Boot que obtiene y entrega datos meteorologicos de OpenWeatherMap.

<h2>Requerimientos</h2>

Before getting started, make sure you have the following prerequisites installed on your system:


  <li><a href="https://www.docker.com/get-started/"> Docker </a> - Docker para contenerizacion. </li>
  <li><a href= "https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html"> JDK 17 </a> - Java 17 para compilar y configurar el proyecto..</li>
  <li><a href="https://openweathermap.org/api"> API Key </a> - Una api key de Open Weather Map, para acceder a los datos meteorologicos. </li>
  <li>Un entorno de desarrollo integrado como Visual Studio Code o IntelliJ IDEA para trabajar con el proyecto Spring Boot.</li>

<h2>Configuración del Entorno</h2>

A continuacion se detallan los pasos a seguir para ejecutar la aplicacion:

<ol>
<li> Clone este repositorio a su maquina local: </li><br>
  
```
git clone https://github.com/emibeanatte/weather-spring-boot-api.git
```

<li> Ejecute los siguientes comandos: </li> <br>

```
./mvnw clean package -DskipTests
```

```
docker-compose build java-app
```

```
docker-compose up
```

> [!IMPORTANT]
> Asegúrate de estar en el directorio raíz de tu proyecto Spring Boot al ejecutar estos comandos. Con ellos, podrás ejecutar fácilmente la construcción y ejecución de tu aplicación. 

</ol>

<h2> Endpoints Disponibles </h2>

- Registrar Usuario (POST) </li>
  - Ruta: `/api/auth/signup`
  - Cuerpo (Body): 
  <br>

  ```JSON
  {
    "username":"tu-nombre-de-usuario",
    "password":"tu-contraseña"
  }
  ```

- Iniciar Sesion (POST)
  - Ruta: `/api/auth/signin`
  - Cuerpo (Body):

  ```JSON
  {
    "username":"tu-nombre-de-usuario",
    "password":"tu-contraseña"
  }
  ```

- Obtener tu propio Usuario (GET)
  - Ruta: `/api/users/me`

- ### Obtener el clima actual por nombre de ciudad (GET)
  - Ruta: `/weather/current/`

- ### Obtener un pronostico del tiempo de 5 dias para una ciudad designada (GET)
  - Ruta: `/weather/current/`
 
- ### Acceder a datos de contaminacion del aire actual para una ciudad seleccionada (GET)
  - Ruta: `/weather/air_pollution/`

> [!NOTE]
> Estos ultimos endpoints requieren el nombre de ciudad y tu <a href = "https://openweathermap.org/api"> Api Key </a> como Parametros.
> Debes Iniciar Sesion con un usuario válido para estar autenticado y realizar peticiones exitosas.

> [!TIP]
> Puedes utilizar Postman para realizar las peticiones y corroborar su funcionamiento.







  







