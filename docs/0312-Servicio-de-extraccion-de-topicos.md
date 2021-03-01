# Servicio de extracción de tópicos

|||
|-|-|
|**Nombre del Servicio:**|topicextractionservice|
|**Responsabilidad:**|Detectar los tópicos de los posts que se van creando en la aplicación|
|**Lenguaje de Programación**|Python|
|**Servicios que Consume:**|Es consumidor de una cola de kafka en la que el servicio de posts informa cuando se crea un nuevo post|
|**Servicios que Ofrece:**|Es productor en una cola de kafka para informar al servicio de posts de que los tópicos de un post han sido inferidos|
|**Bases de datos:**|N/A|

# Descripción
Este servicio se encarga de detectar los tópicos de los posts que se van creando en la aplicación. Para ello, se comunica con el servicio de posts a través de Kafka. Cuando un nuevo post se crea en el sistema, el servicio de posts manda un mensaje a través de la cola de Kafka que es recibido por este servicio. Cuando los tópicos de Kafka se extraen, se vuelven a enviar a la cola de kafka para que sean recibidos por el servicio de posts y actualice el post con estos tópicos.