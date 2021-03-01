# Servicio de Identificación de Idioma

|||
|-|-|
|**Nombre del Servicio:**|Servicio de Identificación de Idioma|
|**Responsabilidad:**|Actuar en caso de que se cree un post para identificar su idioma y publicar el resultado para que otros servicios consuman este evento.|
|**Lenguaje de Programación**|.Net|
|**Servicios que Consume:**|Google Cloud Translate|
|**Servicios que Ofrece:**|Identificación de Idioma (Escucha y Publica en Kafka).|
|**Bases de datos:**|-|

Este servicio tiene la responsabilidad de identificar el idioma de un post. Para llevar a cabo esa tarea, lo que hace es escuchar un tópico de una cola de kafka donde se publican los posts nuevos que se crean. A continuación los consume, prediciendo el lenguaje de cada uno y volviendo a publicar a otro tópico de kafka que para el post con id X ha identificado el idioma Y. Esto a su vez es escuchado por el servicio de posts que se encarga de actualizar el estado del post en la persistencia.