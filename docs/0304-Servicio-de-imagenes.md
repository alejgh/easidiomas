# Servicio de Imágenes

|||
|-|-|
|**Nombre del Servicio:**|Servicio de Imágenes|
|**Responsabilidad:**|Normalizar las imágnes de perfil de los usuarios de base64 a JPG y subirlas a un bucket de Google Cloud Storage.|
|**Lenguaje de Programación**|Java|
|**Servicios que Consume:**|Google Cloud Storage|
|**Servicios que Ofrece:**|Servicio de gestión de imágenes (SOAP).|
|**Bases de datos:**|-|


## Descripción
Este servicio SOAP ofrece a los clientes la posibilidad de procesar una imagen y almacenarla en
un servidor remoto para poder acceder a ella a través de una URL. El procesamiento es bastante sencillo actualmente y consiste en un reajuste de tamaño a unas dimensiones normalizadas (512x512).

### Operaciones ofrecidas
Para acceder al wsdl generado es necesario acceder a la url __http://\<endpoint\>/soapws/images?wsdl__. A continuación resumiremos las operaciones ofrecidas por el servicio:

#### uploadImage(Image data)
Esta es la única operación ofrecida por el servicio. Los datos de la imagen deberán enviarse en un attachment utilizando MTOM (ver https://www.w3.org/TR/soap12-mtom/) en formato binario. Sólo se aceptan tipos mime "image/jpeg" actualmente.

La respuesta es un string con la URL de acceso a la imagen procesada.


### Clientes del servicio
En el servicio de usuarios se puede ver un ejemplo de llamada a este servicio. Una vez que se crea un usuario se manda el avatar al servicio de imágenes para que lo procese y devuelva una URL de acceso a éste.

### Despliegue
Dado que este cliente depende de una cola de Kafka donde enviar los logs, se recomienda desplegar el servicio en conjunción con el resto utilizando el docker-compose proporcionado en la carpeta _release_.
Al desplegar con docker-compose el servicio es accesible a través de http://localhost:5004/soapws/images?wsdl