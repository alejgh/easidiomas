# Sistema de Detección de Texto Ofensivo

|||
|-|-|
|**Nombre del Servicio:**|Sistema de Detección de Texto Ofensivo|
|**Responsabilidad:**|Escucha eventos de creación de posts de una cola y analiza el contenido de los posts para avisar de si un posts contiene texto ofensivo o no.|
|**Lenguaje de Programación**|Python|
|**Servicios que Consume:**|Es consumidor de un tópico en una cola de kafka donde el servicio de Posts publica los posts nuevos.|
|**Servicios que Ofrece:**|Es productor de un tópico en una cola de kafka donde informa sobre si un post es ofensivo o no.|
|**Bases de datos:**|-|

## Descripción
Este servicio se encarga de detectar que textos pueden resultar ofensivos o no. Para ello hace uso de una librería externa que nos ayuda a concluir si un posts incluye contenido ofensivo o no. La comunicación con el resto del sistema se realiza de forma totalmente asincrona. Esto significa que consume una cola de kafka donde se publican los posts nuevos. Y cuando termina de procesar el posts envia una notificación con el resultado a otro tópico de la misma cola. La idea es que en ese tópico esté escuchando el servicio de posts y cuando se publique un resultado se actualice la persistencia del post implicado.