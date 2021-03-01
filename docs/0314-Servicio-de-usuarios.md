# Servicio de Usuarios

|||
|-|-|
|**Nombre del Servicio:**|usersservice|
|**Responsabilidad:**|Ofrecer un CRUD de usuarios a otros servicios.|
|**Lenguaje de Programación**|Java|
|**Servicios que Consume:**|Servicio de Estadísticas (actualizar usuarios creados) y Servicio de Imágenes (procesamiento de avatar del usuario)|
|**Servicios que Ofrece:**|CRUD de usuarios (ver información debajo)|
|**Bases de datos:**|Neo4j|

## Descripción


## Endpoints ofrecidos
A continuación se mustran los endpoints ofrecidos por este servicio.

### 1.- Listado de usuarios con paginación + filtros
__GET: /api/users__

#### Query params:
* Limit (int): Numero de resultados por consulta (default=10)
* Offset (int): A partir de qué post se devuelve resultado (default=0)
* MinAge (int): Edad mínima de los usuarios a buscar 
* MaxAge (int): Edad máxima de los usuarios a buscar
* WantsToLearn (string[]): Lista de idiomas (ISO) que está aprendiendo los usuarios (or)
* Speaks (string[]): Lista de idiomas (ISO) que hablan los usuario (búsqueda OR)

#### Resultado válido:
```
{
    "links": {
        "self": "",
        "first": "",
        "prev": "",
        "next": "",
        "last": ""
    },
    "hits": 2,
    "total": 2,
    "users": [
        {
            "id": 1,
            "username": "pedro",
            "name": "pedro",
            "surname": "suarez",
            "learning": [
                "es",
                "en"
            ],
            "speaks": "",
            "birthDate": "1970-01-02T10:17:36.789+00:00",
            "avatar": ""
        },
        {
            "id": 3,
            "username": "pedro3",
            "name": "pedro",
            "surname": "suarez",
            "learning": [
                "es",
                "en",
                "ru"
            ],
            "speaks": "cat",
            "birthDate": "1614416676",
            "avatar": ""
        }
    ]
}
```

200 OK

#### Resultados inválidos
N/A

### 2.- Obtener usuario específico
__GET: /api/users/\<id\>__
#### Resultado válido:
```
{
            "id": 1,
            "username": "pedro",
            "name": "pedro",
            "surname": "suarez",
            "learning": [
                "es",
                "en"
            ],
            "speaks": [],
            "birthDate": 1233254568,
            "avatar": "<url_a_imagen>"
        }
```
***

#### Resultados inválidos
__404: Not found__

### 3.- Crear un usuario
__POST: /api/users/__

#### Ejemplo de entrada
```
{
     "username": "pepiiii",
     "password": "admin123",
     "email": "pepi@gmail.com",
     "name": "Pepito",
     "surname": "Sanchez Perez"
     "learning": [“en”, "ru"],
     "speaks": "es",
     "birthDate": "<long_time_since_epoch>",
     "avatar": "imagenCodificadaEnBase64"
}
```

#### Resultado válido
201 - Created
En la cabecera -> link al recurso
```
{
     "name": "Pepito",
     "surname": "Sanchez Perez"
     "learning": [“en”, "ru"],
     "speaks": "es",
     "birthDate": "<long_time_since_epoch>",
     "avatar": "imagenCodificadaEnBase64"
}
```

#### Resultado inválido
__409 - Conflict__: Si el email o el username ya existen.

### 4.- Editar un usuario
__PUT: /api/users/\<id\>__

#### Ejemplo de entrada
```
{
     "learning": [“en”, "ru"],
     "speaks": "es",
     "avatar": "imagenCodificadaEnBase64"
}
```

#### Resultado válido
__200 - Ok (actualización completada)__
__204 - No Content (no enviaste nada, no hubo nada que actualizar)__

#### Resultado inválido
__404 - Not Found__


### 5.- Eliminar un usuario
__DELETE: /api/users/\<id\>__

#### Resultado válido
__200 - Ok__

#### Resultado inválido
__404 - Not Found__

## Otros Comentarios
La elección de una base de datos en grafo como Neo4j se debe principalmente a que en una fase inicial del trabajo teníamos pensado ofrecer un sistema de recomendación de usuarios a los que seguir basándonos en la información de un usuario. Una base de datos en grafo encajaba bastante bien con este tipo de sistema. Finalmente, debido al tiempo disponible, no fue posible implementar esta funcionalidad. Sin embargo, en un futuro se podría añadir y encajaría bastante bien con Neo4J.