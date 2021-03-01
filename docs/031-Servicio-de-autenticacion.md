# Servicio de Autenticación

|||
|-|-|
|**Nombre del Servicio:**|Servicio de Autenticación|
|**Responsabilidad:**|Gestionar los tokens y los pasaportes de los usuarios registrados.|
|**Lenguaje de Programación**|Java|
|**Servicios que Consume:**|gRPC: `generateToken(String user, String pass) -> Token`, gRPC: `generatePassport(Token token) -> Passport`|
|**Servicios que Ofrece:**|REST: `GET:/users?filters`|
|**Bases de datos:**|-|