# Presentación del Problema

Easidiomas es una ecosistema para aprender idiomas de forma social. Esto significa que aprendes idiomas al mismo tiempo que conoces gente. El ecosistema está compuesto por una aplicación móvil para los usuarios y una aplicaciones de administración web.

## Alcance
Es importante tener en cuenta que esta aplicación ha sido diseñada e implementada con fines docentes. Es decir, la mayoría de tecnilogías o implementaciones son más complejas de lo que deberían precisamente para aprender esa tecnología o implementación. Un ejemplo es el uso de Neo4j como base de datos de usuarios, no aporta nada. Es más, seguramente hasta empeore el rendimiento. Pero, lo usamos precisamente para probar a usar distintas tecnologías de base de datos e incluso distintos tipos de bases de datos (SQL, noSQL).

Para definir el alcalce del sistema definiremos las acciones que pueden realizar los actores de la aplicación. En concreto tenemos dos, el usuario y el administrador.

### ¿Qué puedo hacer en easidiomas?
Actualmente estamos trabajando en conseguir que easidiomas ofrezca las siguientes funcionalidades:

**Como usuario, dentro de easidiomas, podrás:**
* Registrarte.
* Autenticarte.
* Buscar a otras personas que estén registradas, filtrando por:
  * Nombre de usuario.
  * Edad.
  * Idioma/s hablado.
  * Idioma/s que quieren aprender.
* Ver los posts públicos de otras personas registradas.
* Publicar posts.
* Establecer conversaciones privadas con otros usuarios.

**Como administrador, dentro de easidiomas, podras:**
* Ver las estadísticas del ecosistema:
  * Número de usuarios registrados en el sistema, desglosado por la característica de cada usuario.
  * Número de posts realizados en el sistema, desglosados por las características de cada post.
  * Número de conversaciones nuevas.
  * Ver los usuarios registrados y sus datos.
  * Borrar usuarios.
  * Ver los posts realizados y sus datos.
  * Borrar posts.
  * Ver y filtrar los logs de la mayoría de servicios de forma centralizada.

**Además de todo esto, el sistema, automaticamente, realiza las siguientes acciones:**
* Identifica el idioma de los posts.
* Traducir los mensajes de los posts a demanda del usuario.
* Identificar y alerta de los posts que pueden contener texto ofensivo.
* Identifica los tópicos de los posts.
* Normalizar las imágenes de los usuarios.

## Elección de Tecnologías