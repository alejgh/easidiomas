# Cliente Móvil

|||
|-|-|
|**Nombre del Servicio:**|Cliente Móvil|
|**Responsabilidad:**|Cliente móvil que expone la funcionaldiad a los usuarios de la aplicación.|
|**Lenguaje de Programación**|JavaScript (React-Native)|
|**Servicios que Consume:**|Servicio de Usuarios (REST), Servicio de Posts (REST) y Servicio de Chats (REST), Servicio de Texto a Voz (REST) y el Servicio de Traducción (REST).|
|**Servicios que Ofrece:**|-|
|**Bases de datos:**|-|

# Funcionamiento
Para poder acceder al cliente movil en primer lugar es necesario tener instalada la aplicación ["Expo Go"](https://play.google.com/store/apps/details?id=host.exp.exponent&hl=es&gl=US) en nuestro dispositivo movil (en Iphone debería poderse tamibén aunque no se ha probado). A continuación, realizar un "npm install" sobre la raiz del directorio [mobileclient](../src/mobileclient). Una vez se hayan instalado todas las dependencias, ejecutar el comando "npm start".

Cuando se encuntre deplegado podremos observar un código QR en nuestra consola como se muestra abajo.
![](imgs/npm-start.png )

Este código  QR deberá ser escaneado desde nuestra aplicación Expo Go. IMPORTANTE! Es necesario que el dispositivo movil y el ordenador se encuentren dentro de la misma red.
Para escanear el código ejecutamos la aplicación Expor go y vamos a la pestaña inferior de proyectos. En la parte superior hay una opción que nos permite escanear nuestro código QR.

<img src="imgs/expo.png" alt="drawing" width="300"/>
