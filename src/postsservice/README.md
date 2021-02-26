# Posts Service
Este servicio se encarga de __gestionar el CRUD de posts__ por parte de los usuarios
de la aplicación de easidiomas.

## Endpoints ofrecidos
### GET: /api/posts
Obtiene la lista de posts del sistema de forma paginada.

#### Parámetros
|Nombre|Descripción|Posibles valores|Valor por defecto |Ejemplos|
|------|-----------|------|:-----:|--------|
|limit|Número de resultados a devolver por página|Numero > 0|5|/api/posts?limit=10|
|offset|Número de resultados que saltar en la búsqueda| Número >= 0 |0|/api/posts?offset=0|
|user|Id del usuario autor de los posts | Id de usuario| Todos los usuarios | /api/posts?user=4 |
| language | Idioma o lista de idiomas (serparadas por '\|') de los posts| Codigo ISO del idioma | 'en', 'en\|ko' | /api/posts?language=en|Todos los idiomas|ko |
| sortBy | Nombre de la propiedad sobre la que ordenar los resultados | CreatedDate, AuthorId... | Id | /api/posts?sortBy=CreatedDate |
| order | Whether to order the results in ascending or descending order | 'asc' or 'desc' | 'asc' | /api/posts?sortBy=CreatedDate&order=desc |

#### Ejemplo de salida válida
```
{
  "links": {
    "self": "api/posts/?offset=0&limit=2",
    "first": "api/posts/?offset=0&limit=2",
    "prev": null,
    "next": "api/posts/?offset=2&limit=2",
    "last": "api/posts/?offset=2&limit=2"
  },
  "count": 2,
  "total": 4,
  "data": [
    {
      "id": 1,
      "authorId": 1,
      "createdDate": "2021-02-12T15:59:27",
      "content": "English vocabulary: POOR\n broke\n needy\n bad off \n in need \n hard up \n dirt poor\n destitute\n bankrupt \n flat broke \n insolvent \n penniless\n moneyless\n stone broke\n impecunious\n impoverished\n down-and-out\n poverty - stricken\n strapped for cash",
      "isOffensive": false,
      "language": "en",
      "likes": 7,
      "topics": [
        "poor",
        "vocabulary",
        "broke"
      ]
    },
    {
      "id": 2,
      "authorId": 2,
      "createdDate": "2021-02-23T14:30:57",
      "content": "26일 그랜드오픈하는 여의도 더현대서울 백화점 내부설계가 최강이네.\n공중정원같은 식당층과 대형 분수, 층고 높고 조경 좋고..기분좋은 공간을 만들었다.\n금돼지 몽탄 에그슬럿 뜨락 신상맛집 다 입점했고 인스타스타 OVN과 블루보틀 백화점 첫 입성.\n현백 자체 스타 브랜드 와인웍스 3호점도!",
      "isOffensive": false,
      "language": "ko",
      "likes": 15,
      "topics": [
        "더현대서울",
        "백화점"
      ]
    }
  ]
}
```

### GET: /api/posts/{id}
Obtiene un post con el id dado.

#### Ejemplo de salida válida
```
{
  "id": 3,
  "authorId": 1,
  "createdDate": "2021-02-02T07:20:05",
  "content": "English Vocabulary: MAGIC\nhex\nspell\ncharm\ncurse\ndevilry\nvoodoo\nsorcery\nwitching\nwitchery\nwizardry\nincantation\nspellworking\nenchantment",
  "isOffensive": false,
  "language": "en",
  "likes": 25,
  "topics": [
    "magic",
    "vocabulary",
    "enchantment"
  ]
}
```

#### Posibles resultados inválidos
__404 Not Found:__ Si el post con el id dado no existe.

### POST: /api/posts
Crea un nuevo post en el sistema.

#### Ejemplo de llamada (body)
Para crear un nuevo post tan solo es necesario incluir el contenido de éste:
```
{
    "content": "26일 그랜드오픈하는 여의도 더현대서울 백화점 내부설계가 최강이네.\n공중정원같은 식당층과 대형 분수, 층고 높고 조경 좋고..기분좋은 공간을 만들었다.\n금돼지 몽탄 에그슬럿 뜨락 신상맛집 다 입점했고 인스타스타 OVN과 블루보틀 백화점 첫 입성.\n현백 자체 스타 브랜드 와인웍스 3호점도!"
}
```
El servicio procesará el autor a partir del pasaporte introducido en la cabecera, y computará el resto de campos del post automáticamente.

#### Ejemplo de respuesta
En la respuesta se devuelve un código __201 Created__, junto con el cuerpo completo del objeto creado:
```
{
    "id": 5,
    "authorId": 69,
    "createdDate": "2021-02-25T15:50:03.3417142+00:00",
    "content": "26일 그랜드오픈하는 여의도 더현대서울 백화점 내부설계가 최강이네.\n공중정원같은 식당층과 대형 분수, 층고 높고 조경 좋고..기분좋은 공간을 만들었다.\n금돼지 몽탄 에그슬럿 뜨락 신상맛집 다 입점했고 인스타스타 OVN과 블루보틀 백화점 첫 입성.\n현백 자체 스타 브랜드 와인웍스 3호점도!",
    "isOffensive": false,
    "language": null,
    "likes": 0,
    "topics": null
}
```

Adicionalmente, los headers contendrán una url bajo la clave __Location__ con el enlace del recurso.

#### Respuestas inválidas
__400 Bad request__: Si no se puede acceder al id del usuario a través del pasaporte del header.

### PUT: /api/posts/{id}
Actualiza un post del sistema con los datos dados:
```
{
    "id": 5,
    "content": "Cambio de contenido",
    "likes": 15
}
```

#### Respuesta válida
__204 No content__

#### Respuestas inválidas
* __501 Unauthorized:__ Si se intenta modificar un post que no hayas creado.
* __400 Bad Request:__ Si no se puede obtener el id del usuario a través del pasaporte.
* __404 Not Found:__ Si no se encuentra el post con el id dado en el sistema.

### DELETE: /api/posts/{id}
Elimina un post del sistema.

#### Respuesta válida
__204 No content__

#### Respuestas inválidas
* __501 Unauthorized:__ Si un usuario sin rol de admin intenta eliminar un post.
* __400 Bad Request:__ Si no se puede obtener el rol del usuario a través del pasaporte.
* __404 Not Found:__ Si no se encuentra el post con el id dado en el sistema.

## Ejecución
Este servicio se comunica con una cola de Kafka y una base de datos Postgres, por lo que es necesario lanzar todos estos elementos para su correcto funcionamiento.
En el docker-compose se lanza todo y se conecta mediante variables de entorno. 

También es posible lanzar el contenedor incluido aquí de forma aislada. En ese caso, el servicio espera tener un servidor de kafka en localhost:9092 y uno de Postgres en el puerto 5432:
```shell
docker build -t easidiomas/postsservice:latest .
docker run -p 80:80 easidiomas/postsservice:latest
```