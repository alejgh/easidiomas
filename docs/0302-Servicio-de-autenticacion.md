# Servicio de Autenticación

|||
|-|-|
|**Nombre del Servicio:**|Servicio de Autenticación|
|**Responsabilidad:**|Gestionar los tokens y los pasaportes de los usuarios registrados.|
|**Lenguaje de Programación**|Java|
|**Servicios que Consume:**|Servicio de Usuarios (REST)|
|**Servicios que Ofrece:**|Generación de Tokens, Validación de Tokens, Generación de Pasaportes|
|**Bases de datos:**|-|

Este servicio está implementado haciendo uso de gRPC, consume un servicio REST que es el de usuarios y genera token y pasaportes. El contenido de un pasaporte es:

```proto
message Passport {
  string userId = 3;
  string username = 4;
  int32 role = 5;
  string name = 6;
  string surname = 7;
  string email = 8;
  string avatarUrl = 9;
  string expirationDate = 10;
}
```

Y, la descripción completa de la comunicación de este servicio se puede obtener en `easidiomas/src/apigateway/src/main/proto/authservice.proto`. Este fichero contiene la especificación que genera el código Java sobre el que luego se realiza la implementación de la lógica del servicio.