# Servicio de estadísticas

|||
|-|-|
|**Nombre del Servicio:**|statisticsservice|
|**Responsabilidad:**|Gestión de estadísticas, tanto a nivel del sistema como de usuarios|
|**Lenguaje de Programación**|Java|
|**Servicios que Consume:**||
|**Servicios que Ofrece:**|Gestión de Estadísticas|
|**Bases de datos:**|Redis|

## Descripción
El servicio de estadísticas se encarga de guardar eventos del sistema e indexarlos de la forma oportuna.
Por ejemplo, desglosando por idioma o por acción.

## Endpoints
### 1.- Añadir un post
/soapws/addPost

### 2.- Añadir un 'registro' de usuario
/soapws/addRegisteredUser

### 3.- Añadir un chat creado
/soap/addCreatedChat

### 4.- Añadir un follow de usuario
/soap/addFollow

### 5.- Nueva traducción de un usuario
/soap/addTranslation

### 6.- Nuevo tts de un usuario
/soap/addTextToSpeech

### 7.- Obtener estadísticas del sistema
/soap/getSystemStatistics

### 8.- Obtener estadísticas de un usuario
/soap/getUserStatistics

## ¿Cómo ejecutarlo?
Se puede ejecutar `docker-compose up` sobre el directorio raíz y se levanta una instancia del sistema y de
la base de datos redis que utiliza. Para conectarnos sólo tendremos que entrar en `http://localhost:5000/soapws/statistics?wsdl`.