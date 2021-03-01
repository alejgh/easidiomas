# Servicio de Autenticación

|||
|-|-|
|**Nombre del Servicio:**|Servicio de API Pública|
|**Responsabilidad:**|Ofrecer la API pública para los clientes. Crear usuarios y gestionar la autenticación de los tokens.|
|**Lenguaje de Programación**|Java|
|**Servicios que Consume:**|Servicio de Usuarios (REST), Servicio de Posts (REST), Servicio de Estadísticas (SOAP), Sercicio de Chats (REST), Servicio de Autenticación (gRPC), Servicio de Texto a Voz (REST) y Servicio de Traducción (REST)|
|**Servicios que Ofrece:**|Una fachada de interfáz pública para consumo de los clientes web y móvil|
|**Bases de datos:**|-|

El servicio de API pública actúa como fachada ante los servicios. Para evitar que los clientes tengan que hacer peticiones a los servicios por separado este servicio ofrece un endpoint único de consumo. Además, también sirve para abstraer la tecnología de comunicación de los servicios de los clientes. Un ejemplo de esto es que el servicio de autenticación se comunica con gRPC y el de estadíscias con SOAP. Pero los clientes realizan toda la comunicación mediante REST.

Una responsabilidad muy importante de este servicio es inyectar en las cabeceras un "pasaporte" para que las peticiones puedan moverse entre servicios sin tener que llamar en todo momento al servicio de autenticación ni tener que descifrar ellos el token. El proceso es el siguiente:

1. El usuario se registra en la API.
   1. El servicio de usuarios registra el usuario en su base de datos.
2. El usuario solicita un token con su usuario y contraseña.
   1. El servicio de autenticación se cominuca con el servicio de usuarios para comprobar que existe un usuario con las credenciales solicitadas.
   2. El servicio de autenticación genera un pasaporte para el usuario y lo cifra, formando el token.
3. La api le devuelve un token con una caducidad y unos permisos acordes a su rol.
4. El usuario realiza una petición con ese token.
   1. El servicio de autenticación descrifra el token y obtiene el pasaporte original.
   2. Valida que no haya expirado.
   3. Valida que los datos del usuario existan en el servicio de usuarios.
   4. El servicio de API pública inyecta en las cabeceras de la petición del usuario el pasaporte y propaga la petición al serivio adecuado.
5. El servicio adecuado puede acceder a todos los datos del usuario con el pasaporte de la cabecera sin tener que hacer ningún cómputo ni comunicación con otros serivicios.
6. Se le envía la respuesta al usuario sin el pasaporte en la cabecera.

## HTTPS
Este servicio se ofrece como HTTPS para que los clientes lo consuman de una forma segura. Sin embargo, debido a que había algun problema con el cliente móvil para conectarse por HTTPS con un certificado autofirmado, fue necesario tambien ofrecer acceso por HTTP al servicio.
Por lo tanto, al arrancar el servicio se ofrece a través de HTTPS (opción recomandad para los clientes si es posible), pero también se ofrece a HTTP en otro puerto con el fin de soportar legacy applications.