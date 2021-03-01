# Bienvenido a easidiomas
Easidiomas es una ecosistema para aprender idiomas de forma social. Esto significa que aprendes idiomas al mismo tiempo que conoces gente. El ecosistema está compuesto por una aplicación móvil para los usuarios y dos aplicaciones de administración, una web y otra CLI.

## ¿Qué puedo hacer en easidiomas?
Actualmente estamos trabajando en conseguir que easidiomas ofrezca las siguientes funcionalidades:

### Como usuario, dentro de easidiomas, podrás:
- Registrarte.
- Autenticarte.
- Buscar a otras personas que estén registradas, filtrando por:
  * Nombre de usuario.
  * Nombre real.
  * Edad.
  * Idioma/s hablado.
  * Idioma/s que quieren aprender.
- Seguir a otras personas que estén registradas.
- Ver los posts públicos de las personas a las que sigues.
- Publicar posts que las personas que te siguen verán en su feed.
- Establecer conversaciones privadas con otros usuarios.

### Como administrador, dentro de easidiomas, podras:
- Ver las estadísticas del ecosistema:
  * Número de usuarios registrados en las últimas 24h.
  * Número de posts realizados en las últimas 24h.
  * Número de conversaciones nuevas en las últimas 24h.
  * Número de mensajes personales enviados en las últimas 24h.
- Ver los usuarios registrados y sus datos.

### Además de todo esto, el sistema, automaticamente, realiza las siguientes acciones:
- Identifica el idioma de los posts.
- Traduce los mensajes de las conversaciones a demanda del usuario.
- Identifica y alerta de los posts que puedan contener texto ofensivo.
- Identifica los tópicos de los posts.

## ¿Cómo está contruido easidiomas?
Este ecosistema nace de una asignatura del Máster en Ingeniería Web de la Universidad de Oviedo. El objetivo es crear un sistema distribuido basado en servicios SOA/MSA donde se exploren los distintos tipos de comunicación entre servicios. Es por ello que se emplean comunicaciones SOAP, REST y gRPC.

![Diagrama de arquitectura general](docs/arc-page-6.png)

En el diagrama anterior se puede ver que el sistema consta de 3 grandes segmentos. Uno donde se ubican los distintos clientes. Otro público que expone servicios y enruta peticiones. Y, un bloque privado que no está expuesto a internet donde residen los servicios que dan respuesta a las peticiones de los clientes. Los sevicios de los que se compone easidiomas son los siguientes:

| Servicio                                              | Lenguaje      | Descripción                                                                                                                       |
| ---------------------------------------------------- | ------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| [webclient](./src/webclient)                           | .NET            | Expone por HTTP el sitio web que pueden usar los administradores del sistema. |
| [mobileclient](./src/mobileclient)                     | Native React            | Cliente móvil que expone la funcionalidad del sistema a los usuarios.                                                           |
| [authenticationservice](./src/authenticationservice) | .NET            | Provee un mecanismo de autenticación para los usuarios registrados y de verificación de identidad en las peticiones a los servicios.                        |
| [userservice](./src/usersservice)             | Java       | Permite crear, actualizar, borrar y buscar usuarios. |
| [postsservice](./src/postsservice)               | Java       | Permite crear, actualizar, borrar y buscar posts.                                     |
| [chatsservice](./src/chatsservice)             | .NET            | Permite crear, actualizar, borrar y buscar conversaciones individuales.                                 |
| [translationsservice](./src/translationsservice)                   | .NET        |A demanda de otros servicios traduce el texto que sea necesario al idioma seleccionado.|
| [texttospeechservice](./src/texttospeechservice)             | Python            | Para una entrada de texto genera un audio que representa la entrada convertida a habla humana en el mismo idioma.                            |
| [statisticsservice](./src/statisticsservice) | Java        | Permite centralizar todas las estadísticas relativas a las entidades del sistema en un mismo sitio. No es un sistema de monitorización del sistema si no más bien una caché de estadística. En este sistema se pueden encontrar datos como el número de mensajes por usuario, el número de usuarios registrados en las últimas 24h, etc.                                                                      |
| [offensivetextdetectionservice](./src/offensivetextdetectionservice)                         | Python          | Continuamente busca texto que pueda ser ofensivo dentro de los posts nuevos.|
| [languageidentificationservice](./src/languageidentificationservice)                 | Java | Para los posts nuevos identifica el lenguaje en el que están escritos y etiqueta dichos posts.                                              |
| [topicmodelingservice](./src/topicmodelingservice)                 | Python | Para los posts nuevos se identifican los posibles tópicos que contenga el post.                                              |
| [imagesservice](./src/imagesservice) | Java | Procesa imagenes (en nuestro caso, los avatares de los nuevos usuarios del sistema) y las sube a un bucket de Google Cloud Storage para poder acceder públicamente a éstas |

## Documentación
La documentación que acompaña a Easidiomas es la siguiente:

|Título Documento|Descripción|
|----------------|-----------|
|[Reparto de tareas](./docs/0100-Reparto-de-tareas.md)||
|[Presentación del problema](./docs/0200-Presentacion-del-problema.md)||
|[Especificación de los servicios](./docs/0300-Especificacion-de-servicios.md)||
|[- Servicio de API pública](./docs/0301-API-publica.md)||
|[- Servicio de autenticación](./docs/0302-Servicio-de-autenticacion.md)||
|[- Servicio de chats](./docs/0303-Servicio-de-chats.md)||
|[- Servicio de imágenes](./docs/0304-Servicio-de-imagenes.md)||
|[- Servicio de identificación de idioma](./docs/0305-Servicio-de-indentificacion-de-idioma.md)||
|[- Generador de tráfico](./docs/0306-Generador-de-trafico.md)||
|[- Cliente móvil](./docs/0307-Cliente-movil.md)||
|[- Sistema de detección de texto ofensivo](./docs/0308-Sistema-de-deteccion-de-texto-ofensivo.md)||
|[- Servicio de posts](./docs/0309-Servicio-de-posts.md)||
|[- Servicio de estadísticas](./docs/0310-Servicio-de-estadisticas.md)||
|[- Servicio de texto a audio](./docs/0311-Servicio-de-texto-a-audio.md)||
|[- Servicio de extracción de tópicos](./docs/0312-Servicio-de-extraccion-de-topicos.md)||
|[- Servicio de traducción](./docs/0313-Servicio-de-traduccion.md)||
|[- Servicio de usuarios](./docs/0314-Servicio-de-usuarios.md)||
|[- Cliente web](./docs/0315-Cliente-Web.md)||
|[Arquitectura](./docs/0400-Arquitectura.md)||
|[Despliegue](./docs/0500-Despliegue.md)||