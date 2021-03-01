# Especificación de Servicios
A continuación se hace un desglose de los servicios que componen esta aplicación. Para obtener información detallada sobre cada uno de ellos se recomienda acceder a su documento markdown correspondiente de esta misma carpeta.

## Servicios Web Externos Utilizados

|Proveedor|Servicio|Justificación|Utilización|Problemas|
|---------|--------|-------------|-----------|---------|
|Google Cloud|Traducción|Se emplea para traducir texto de un idioma de origen a uno de destino.|A través de un cliente para .Net que ofrece Google a través de NuGet.|-|
|Google Cloud|Detección de Idioma|Se emplea para predecir el idioma de un post, de esta forma podemos etiquetarlo para posteriormente ofrecer traducción.|A través de un cliente para .Net que ofrece Google a través de NuGet.|-|
|Google Cloud|Subida de archivos|Se emplea para predecir el idioma de un post, de esta forma podemos etiquetarlo para posteriormente ofrecer traducción.|A través de un cliente para .Net que ofrece Google a través de NuGet.|-|

## Servicios Web de Consumo Interno

|Servicio|Justificación|Utilización|Problemas|Dónde se usa|
|--------|-------------|-----------|---------|------------|
|StatisticsService|Se emplea para gestionar las estadísticas del sistema y a nivel de usuarios (número de posts creados, usuarios registrados por idioma...)|Ofrece un endpoint SOAP que es consumido por los clientes del servicio|La generación de código con el script wsimport.cmd dio muchos problemas en los proyectos desarrollados con Spring debido a que cambia el layout de paquetes (esto lo podemos explicar con calma si es necesario en una llamada o por correo)|TextToSpeechService (Cliente Python), UsersService (Java), TranslationService (.NET), PostsService(.NET), APIGateway(Java) - cliente web|
| UsersService | Se emplea para gestionar los usuarios (creación, búsqueda...) | Ofrece un endpoint REST que es consumido por los clientes del servicio | - | APIGateway (Java) - clientes móvil y web |
| PostsService | Se emplea para gestionar los posts. | Ofrece un endpoint rest utilizado por el api gateway. Internamente hace uso de una cola de Kafka para comunicarse con los servicios de procesamiento de texto (offensive text detection, topic modeling y language identification) | - | Offensive Text Detection, Topic Modeling, Language Identification y APIGateway - clientes móvil y web |
| ChatsService | Se emplea para gestionar los chats y mensajes de la aplicación | Ofrece un endpoint rest utilizado por el api gateway | - | APIGateway - cliente móvil |
| TextToSpeechService | Permite a los clientes obtener audio a partir de un texto dado | Ofrece un endpoint REST a los clientes | - | APIGateway - cliente móviñ |
| TranslationService | Permite a los clientes traducir un texto de un idioma a otro | Ofrece un endpoint REST a los clientes | - | APIGateway - cliente móvil |
| AuthenticationService | Gestiona los tokens de los usuarios para poder acceder a las operaciones del sistema | Comunicación a través de gRPC con el API Gateway | - | APIGateway |
| ImagesService | Procesa las imágenes con el avatar de cada usuario y las almacena en un bucket de Google Cloud Storage para poder ser consumidas públicamente por los clientes. | Ofrece un endpoint soap | El uso de attachments y MTOM para transmitir la imagen hizo que no se pudiera implementar en .NET Core ya que SoapCore no los soporta actualmente. La implementación se llevó a cabo en Java | UsersService (al crear un nuevo usuario llama a este servicio para procesar su avatar) |
| TopicModelingService | Genera una lista de tópicos para los posts creados en la aplicación | Se comunica a través de una cola de Kafka con el servicio de posts para recibir actualizaciones de cuándo se crea un post nuevo | - | PostsService (cuando se detectan los tópicos se llama al servicio de posts por la cola para avisarle) |
| OffensiveTextDetectionService | Procesa un texto para detectar si es potencialmente ofensivo o no | Se comunica a través de una cola de Kafka con el servicio de posts para recibir actualizaciones de cuándo se crea un post nuevo | - | PostsService (cuando se detectan si un texto es ofensivo se llama al servicio de posts por la cola para avisarle) |
| LanguageIdentificationService | Identifica el idioma de un texto | Se comunica a través de una cola de Kafka con el servicio de posts para recibir actualizaciones de cuándo se crea un post nuevo | - | PostsService (cuando se detecta el idioma del texto se llama al servicio de posts por la cola para avisarle) |


## Servicios Web Ofrecidos para Consumo Externo

|Servicio|Justificación|Utilización|Problemas|Para quién se ofrece|
|--------|-------------|-----------|---------|--------------------|
| API Gateway | Ofrece un punto de entrada a los clientes del sistema. Permite una gestión centralizada de la autenticación y un mayor control sobre las peticiones entrantes en el sistema | Ofrece un enpoint REST para que utilicen los usuarios | - | Clientes del sistema (cliente web y móvil) |