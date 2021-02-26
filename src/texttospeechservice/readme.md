# Text To Speech Service
Este servicio ofrece una forma de convertir textos a audio. Soporta una amplia serie de idiomas y de locales para reproducir el texto.

## Endpoints ofrecidos

### POST: /api/TextToSpeechs
Crea un nuevo audio a partir del texto dado.

#### Ejemplo de llamada (body)
Para crear un nuevo audio es necesario incluir el texto e idioma:
```
{
    "language": "es",
    "text": "Easidiomas es una aplicación social orientada al intercambio de idiomas creada por miembros del grupo Web Semántica Oviedo. Os esperamos a todos el día lunes, cuando sacaremos al público nuestra aplicación móvil. Diviértete aprendiendo con Easidiomas."
}
```
El servicio procesará el idioma y, en caso de que ofrezca varios locales (p.ej. 'en' tiene locales como 'en-US' o 'en-UK'), escogerá un locale por defecto. En caso de que el usuario ya suministre un locale en el campo lenguaje, se utilizará el locale suministrado.

#### Ejemplo de respuesta
En la respuesta se devuelve un código __201 Created__, junto con el cuerpo completo del objeto creado:
```
{
    "locale": "es-ES",
    "audio": "<mp3_codificado_base64>"
}
```
Este objeto contiene el locale identificado por el servicio (en caso de que haya sido necesario), junto con el audio en __formato mp3__ codificado como un string en base64. Para reproducirlo será necesario decodificar este string previamente a una cadena de bytes.

#### Respuestas inválidas
__400 Bad request__: Si no se proporciona alguno de los campos requeridos en el body.

__429 Too Many Requests__: Si se realizan demasiadas peticiones al servicio o se consumen los créditos disponibles internamente.

__500 Internal Server Error__: Si se produce un error interno del servidor al procesar la petición.

## Ejecución
Este servicio se comunica con una cola de Kafka, por lo que es necesario lanzar todos estos elementos para su correcto funcionamiento.
En el docker-compose se lanza todo y se conecta mediante variables de entorno. 

También es posible lanzar el contenedor incluido aquí de forma aislada. En ese caso, el servicio espera tener un servidor de kafka en localhost:9092:
```shell
docker build -t easidiomas/texttospeechservice:latest .
docker run -p 5000:5000 easidiomas/texttospeechservice:latest
```