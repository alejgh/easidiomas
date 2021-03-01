# Servicio de Chats

|||
|-|-|
|**Nombre del Servicio:**|Servicio de Chats|
|**Responsabilidad:**|CRUD de chats y mensajes privados entre usuarios.|
|**Lenguaje de Programación**|Java|
|**Servicios que Consume:**|Servicio de Estadísticas (SOAP)|
|**Servicios que Ofrece:**|CRUD de chats y mensajes.|
|**Bases de datos:**|MongoDB|

## Endpoints que ofrece.
A continuación se especifican los endpoints de este servicio. Estos endpoints son de consumo externo, propagados en el servicio de API pública.

### 1.- Consultar los chats de un usuario
__GET: /api/chats__

En la cabecera viene el usuario del que sacar los chats.

#### Resultado válido
```
[
   {
       "id": 1,
       "lastUpdated": <long_time_since_epoch>,
       "messages": "<url_get_messages>" (/api/chats/1/messages),
       "user1": "<id_user1>",
       "user2": "<id_user2>"
   },
   ...
]
```
200 ok

#### Resultado inválido
N/A

### 2.- Consultar los mensajes de un chat
__GET: /api/chats/\<id\>/messages__

Importante comprobar que en el pasaporte el usuario tenga permisos para ver los chats (p.ej. si es un chat entre menganito y fulanito, que paco no lo pueda ver).
```
[
   {
       "id": 1,
       "createdAt": <long_time_since_epoch>,
       "text": "Hola, Buenas",
       "sender": "<id_user>"
   },
   {
       "id": 2,
       "createdAt": <long_time_since_epoch>,
       "text": "respuesta",
       "sender": "<id_user>"
   },
   ...
]
```

#### Resultado inválido
404 - Not Found: El chat no existe
401 - Unauthorized: Si un usuario intenta ver mensajes en un chat en el que no participa.

### 3.- Añadir mensaje a un chat
__POST: /api/chats/\<id\>/messages__

#### Ejemplo de entrada (body)
El id del usuario autor del mensaje viene en el header. Habría que cogerlo y añadirlo al mensaje.
```
{
    "text": "Hola, Buenas"
}
```

#### Resultado válido
201 - Created
En la cabecera -> link al recurso
```
   {
       "id": 1,
       "createdAt": <long_time_since_epoch>,
       "text": "Hola, Buenas",
       "sender": "<id_user>"
   }
```

#### Resultado inválido
404 - Not Found: El chat no existe
401 - Unauthorized: Si un usuario intenta crear un mensaje en un chat en el que no participa.