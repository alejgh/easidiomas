# Servicio de traducción

|||
|-|-|
|**Nombre del Servicio:**|translationsservice|
|**Responsabilidad:**|Ofrece a los clientes la posibilidad de traducir un texto de un idioma a otro.|
|**Lenguaje de Programación**|.NET|
|**Servicios que Consume:**|Google Cloud Translate|
|**Servicios que Ofrece:**|Traducir un texto|
|**Bases de datos:**|N/A|

## Enpoint ofrecido
### 1.- Realizar una traducción
__POST: /api/translations__

#### Ejemplo de entrada (body)
```
{
	"sourceLanguage": "es",
	"targetLanguage": "en",
	"text": "texto a traducir"
}
```

#### Respuesta válida
```
{
    "sourceLanguage": "es",
    "targetLanguage": "en",
    "text": "texto a traducir",
    "translation": "text to translate"
}
```

#### Respuesta inválida
__429 - Too Many Requests__:
En caso de que se hagan demasiadas peticiones simultáneas o se alcancen los límites de cuota para la traducción.
