# Cliente Web

|||
|-|-|
|**Nombre del Servicio:**|webclient|
|**Responsabilidad:**|Ofrecer una interfaz web a los administradores para consultar estadísticas del sistema y hacer crud de posts y usuarios|
|**Lenguaje de Programación**|.Net|
|**Servicios que Consume:**|Servicio de usuarios, Servicio de posts y Servicio de chats|
|**Servicios que Ofrece:**|Ninguno|
|**Bases de datos:**|Ninguna|

## Funcionamiento
Para acceder a la funcionalidad del cliente web es necesario tener una cuenta de administrador. Al acceder a la web, el cliente nos solicitará la introducción de nombre de usuario y contraseña. Una vez que nos hayamos logeado correctamente, podremos acceder a la propia funcionalidad del sistema. Entre esta funcionalidad se encuentra la consulta de estadísticas, la gestión de usuarios y la gestión de posts.

## Comentarios
El cliente web se lanza tanto en http en el puerto 80, como en https a través del puerto 443.

En el docker-compose ofrecido se lanza automáticamente todo y es posible acceder a este cliente a través de localhost.