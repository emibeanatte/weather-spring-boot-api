<h1> Api de Datos Meteorologicos </h1>

Una API  construida con Spring Boot que obtiene y entrega datos meteorologicos de OpenWeatherMap.

<h2>Requerimientos</h2>

Antes de comenzar, asegúrate de tener instalados en tu sistema los siguientes requisitos previos:


  <li><a href="https://www.docker.com/get-started/"> Docker </a> - Docker Desktop para contenerizacion. </li>
  <li><a href= "https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html"> JDK 17 </a> - Java 17 para compilar y configurar el proyecto.</li>
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
> Asegúrate de estar en el directorio raíz de tu proyecto Spring Boot al ejecutar estos comandos. Con ellos, podrás ejecutar fácilmente la construcción y ejecución de la aplicación. 

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
  <br>

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
  - Ruta: `/weather/forecast/`
 
- ### Acceder a datos de contaminacion del aire actual para una ciudad seleccionada (GET)
  - Ruta: `/weather/air_pollution/`

> [!NOTE]
> Estos ultimos endpoints requieren el nombre de ciudad y tu <a href = "https://openweathermap.org/api"> Api Key </a> como Parametros.
> Debes Iniciar Sesion con un usuario válido para estar autenticado y realizar peticiones exitosas.

> [!TIP]
> Puedes utilizar Postman para realizar las peticiones y corroborar su funcionamiento.


<h2> Documentacion </h2>

Se encuentra disponible la documentacion generada automaticamente con Swagger.

Para acceder a ella siga estos simples pasos

1. Abra su navegador de preferencia.
2. Vaya a la ruta `/v3/api-docs`

Para acceder a los metodos detallados previamente y realizar pruebas de los endpoints:

1. Abra su navegador de preferencia.
2. Vaya a la ruta `/swagger-ui/index.html`

> [!CAUTION]
> La aplicacion esta configurada para realizar hasta 100 consultas por hora.

<h2> Ejemplos de Solicitudes y Respuestas </h2>

1. Solicitar el clima actual por nombre de ciudad:

  - Solicitud:
      `http://localhost:8080/weather/current/?q={nombre-de-ciudad}&appid={tu-API-key}`

  - Respuesta:

  ```JSON
  {
    "name": "London",
    "main": {
        "temp": 11.15,
        "humidity": 86
    },
    "wind": {
        "speed": 6.69
    },
    "weather": [
        {
            "description": "broken clouds"
        }
    ]
  }
  ```

2. Solicitar un pronostico del tiempo de 5 dias para una ciudad designada:

  - Solicitud:
      `http://localhost:8080/weather/forecast/?q={nombre-de-ciudad}&appid={tu-API-key}`

  - Respuesta:

  ```JSON
  {
    "cod": "200",
    "message": 0,
    "cnt": 40,
    "list": [
        {
            "dt": 1700438400,
            "main": {
                "temp": 10.91,
                "feelsLike": 0.0,
                "pressure": 1008,
                "humidity": 85,
                "temp_min": 10.91,
                "temp_max": 11.75,
                "sea_level": 1008,
                "grnd_level": 1007,
                "temp_kf": -0.84
            },
            "weather": [
                {
                    "id": 803,
                    "main": "Clouds",
                    "description": "broken clouds"
                }
            ],
            "clouds": {
                "all": 75
            },
            "wind": {
                "speed": 6.12,
                "deg": 256,
                "gust": 12.0
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-20 00:00:00"
        },
        {
            "dt": 1700524800,
            "main": {
                "temp": 10.56,
                "feelsLike": 0.0,
                "pressure": 1014,
                "humidity": 91,
                "temp_min": 10.56,
                "temp_max": 10.56,
                "sea_level": 1014,
                "grnd_level": 1011,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 500,
                    "main": "Rain",
                    "description": "light rain"
                }
            ],
            "clouds": {
                "all": 100
            },
            "wind": {
                "speed": 3.84,
                "deg": 337,
                "gust": 9.52
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-21 00:00:00"
        },
        {
            "dt": 1700611200,
            "main": {
                "temp": 7.72,
                "feelsLike": 0.0,
                "pressure": 1030,
                "humidity": 87,
                "temp_min": 7.72,
                "temp_max": 7.72,
                "sea_level": 1030,
                "grnd_level": 1027,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 804,
                    "main": "Clouds",
                    "description": "overcast clouds"
                }
            ],
            "clouds": {
                "all": 96
            },
            "wind": {
                "speed": 2.01,
                "deg": 4,
                "gust": 5.2
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-22 00:00:00"
        },
        {
            "dt": 1700697600,
            "main": {
                "temp": 8.2,
                "feelsLike": 0.0,
                "pressure": 1026,
                "humidity": 96,
                "temp_min": 8.2,
                "temp_max": 8.2,
                "sea_level": 1026,
                "grnd_level": 1023,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 803,
                    "main": "Clouds",
                    "description": "broken clouds"
                }
            ],
            "clouds": {
                "all": 53
            },
            "wind": {
                "speed": 2.65,
                "deg": 259,
                "gust": 9.52
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-23 00:00:00"
        },
        {
            "dt": 1700784000,
            "main": {
                "temp": 10.55,
                "feelsLike": 0.0,
                "pressure": 1017,
                "humidity": 83,
                "temp_min": 10.55,
                "temp_max": 10.55,
                "sea_level": 1017,
                "grnd_level": 1014,
                "temp_kf": 0.0
            },
            "weather": [
                {
                    "id": 804,
                    "main": "Clouds",
                    "description": "overcast clouds"
                }
            ],
            "clouds": {
                "all": 100
            },
            "wind": {
                "speed": 5.1,
                "deg": 268,
                "gust": 12.0
            },
            "visibility": 10000,
            "pop": 0,
            "dt_txt": "2023-11-24 00:00:00"
        }
    ]
  }
  ```

3. Solicitar datos de contaminacion del aire actual para una ciudad seleccionada:

   - Ruta:
      `http://localhost:8080/weather/air_pollution/?q={nombre-de-ciudad}&appid={tu-API-key}`

   - Respuesta:

  ```JSON
    {
    "list": [
        {
            "main": {
                "aqi": 2
            },
            "components": {
                "co": 226.97,
                "no": 0.0,
                "no2": 6.77,
                "o3": 63.66,
                "so2": 2.83,
                "pm2_5": 1.37,
                "pm10": 3.21,
                "nh3": 0.26
            }
        }
      ]
    }
  ```

<h2></h2>


# ¡Gracias por visitar! 👋

  







