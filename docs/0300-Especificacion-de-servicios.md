# Especificación de Servicios
A continuación se hace un desglose de los servicios que componen esta aplicación. Para obtener información detallada sobre cada uno de ellos se recomienda acceder a su documento markdown correspondiente pinchando sobre él.

## Servicios Web Externos Utilizados

|Proveedor|Servicio|Justificación|Utilización|Problemas|
|---------|--------|-------------|-----------|---------|
|Google Cloud|Traducción|Se emplea para traducir texto de un idioma de origen a uno de destino.|A través de un cliente para .Net que ofrece Google a través de NuGet.|-|
|Google Cloud|Detección de Idioma|Se emplea para predecir el idioma de un post, de esta forma podemos etiquetarlo para posteriormente ofrecer traducción.|A través de un cliente para .Net que ofrece Google a través de NuGet.|-|
|Google Cloud|Subida de archivos|Se emplea para predecir el idioma de un post, de esta forma podemos etiquetarlo para posteriormente ofrecer traducción.|A través de un cliente para .Net que ofrece Google a través de NuGet.|-|

## Servicios Web de Consumo Interno

|Servicio|Justificación|Utilización|Problemas|Dónde se usa|
|--------|-------------|-----------|---------|------------|
|[StatisticsService](./0310-Servicio-de-estadisticas.md)|Se emplea para gestionar las estadísticas del sistema y a nivel de usuarios (número de posts creados, usuarios registrados por idioma...)|Ofrece un endpoint SOAP que es consumido por los clientes del servicio|La generación de código con el script wsimport.cmd dio muchos problemas en los proyectos desarrollados con Spring debido a que cambia el layout de paquetes (esto lo podemos explicar con calma si es necesario en una llamada o por correo)|TextToSpeechService (Cliente Python), UsersService (Java), TranslationService (.NET), PostsService(.NET), APIGateway(Java) - cliente web|
| [UsersService](./0314-Servicio-de-usuarios.md) | Se emplea para gestionar los usuarios (creación, búsqueda...) | Ofrece un endpoint REST que es consumido por los clientes del servicio | - | APIGateway (Java) - clientes móvil y web |
| [PostsService](./0309-Servicio-de-posts.md) | Se emplea para gestionar los posts. | Ofrece un endpoint rest utilizado por el api gateway. Internamente hace uso de una cola de Kafka para comunicarse con los servicios de procesamiento de texto (offensive text detection, topic modeling y language identification) | - | Offensive Text Detection, Topic Modeling, Language Identification y APIGateway - clientes móvil y web |
| [ChatsService](./0303-Servicio-de-chats.md) | Se emplea para gestionar los chats y mensajes de la aplicación | Ofrece un endpoint rest utilizado por el api gateway | - | APIGateway - cliente móvil |
| [TextToSpeechService](./0311-Servicio-de-texto-a-audio.md) | Permite a los clientes obtener audio a partir de un texto dado | Ofrece un endpoint REST a los clientes | - | APIGateway - cliente móviñ |
| [TranslationService](./0313-Servicio-de-traduccion.md) | Permite a los clientes traducir un texto de un idioma a otro | Ofrece un endpoint REST a los clientes | - | APIGateway - cliente móvil |
| [AuthenticationService](./0302-Servicio-de-autenticacion.md) | Gestiona los tokens de los usuarios para poder acceder a las operaciones del sistema | Comunicación a través de gRPC con el API Gateway | - | APIGateway |
| [ImagesService](./0304-Servicio-de-imagenes.md) | Procesa las imágenes con el avatar de cada usuario y las almacena en un bucket de Google Cloud Storage para poder ser consumidas públicamente por los clientes. | Ofrece un endpoint soap | El uso de attachments y MTOM para transmitir la imagen hizo que no se pudiera implementar en .NET Core ya que SoapCore no los soporta actualmente. La implementación se llevó a cabo en Java | UsersService (al crear un nuevo usuario llama a este servicio para procesar su avatar) |
| [TopicModelingService](./0312-Servicio-de-extraccion-de-topicos.md) | Genera una lista de tópicos para los posts creados en la aplicación | Se comunica a través de una cola de Kafka con el servicio de posts para recibir actualizaciones de cuándo se crea un post nuevo | - | PostsService (cuando se detectan los tópicos se llama al servicio de posts por la cola para avisarle) |
| [OffensiveTextDetectionService](./0308-Sistema-de-deteccion-de-texto-ofensivo.md) | Procesa un texto para detectar si es potencialmente ofensivo o no | Se comunica a través de una cola de Kafka con el servicio de posts para recibir actualizaciones de cuándo se crea un post nuevo | - | PostsService (cuando se detectan si un texto es ofensivo se llama al servicio de posts por la cola para avisarle) |
| [LanguageIdentificationService](./0305-Servicio-de-identificacion-de-idioma.md) | Identifica el idioma de un texto | Se comunica a través de una cola de Kafka con el servicio de posts para recibir actualizaciones de cuándo se crea un post nuevo | - | PostsService (cuando se detecta el idioma del texto se llama al servicio de posts por la cola para avisarle) |


## Servicios Web Ofrecidos para Consumo Externo

|Servicio|Justificación|Utilización|Problemas|Para quién se ofrece|
|--------|-------------|-----------|---------|--------------------|
| [API Gateway](./0301-API-publica.md) | Ofrece un punto de entrada a los clientes del sistema. Permite una gestión centralizada de la autenticación y un mayor control sobre las peticiones entrantes en el sistema | Ofrece un enpoint REST para que utilicen los usuarios | Uso de https desde el cliente móvil (ver [documentación de API Gateway](./0301-API-publica.md) para más información | Clientes del sistema (cliente web y móvil) |

## Descripción de la API Pública
| Endpoint | Método |Descripción|
|----------|--------|-----------|
|`/api/auth/tokens`|POST|Generación de tokens.|
|||||||
|`/api/users?filters`|GET|Listar usuarios.|
|`/api/users`|POST|Crear usuarios.|
|`/api/users/{id}`|GET|Acceder a un usuario específico.|
|`/api/users/{id}`|PUT|Actualizar los datos de un usuario.|
|`/api/users/{id}`|DELETE|Eliminar un usuario.|
|||||||
|`/api/chats`|GET|Muestra los chats del usuario que realizó la petición.|
|`/api/chats`|POST|Crea un chat.|
|`/api/chats/{id}`|GET|Muestra un chat en concreto|
|`/api/chats/{id}/messages`|GET|Devuelve los mensajes de un chat.|
|`/api/chats/{id}/messages`|POST|Crea un mensaje en un chat.|
|||||||
|`/api/statistics`|GET|Devulve las estadísticas del sistema.|
|||||||
|`/api/translations`|POST|Traduce un texto al idioma deseado.|
|||||||
|`/api/textstospeechs`|POST|Pasa unb texto a voz.|
|`/api/health`|GET|Indica si la aplicación está activa.|
