# Despliegue del Sistema
Para realizar el despliegue del sistema se emplean contenedores para albergar todos los componentes del sistema. A excepción del cliente móvil. Para realizar la orquestación de contenedores se emplean dos tecnologías. Por una parte, para el despliegue en un único servidor, [Docker Compose](https://docs.docker.com/compose/). Y, para el despliegue en un clúster, [Docker Swarm](https://docs.docker.com/engine/swarm/). En la carpeta de `release`, desde la raíz del proyecto, se pueden encontrar los ficheros de especificación de los despliegues.

## Plataformas y herramientas utilizadas
Como plataforma de despliegue principal se emplean contendores. En concreto, de Docker. Por lo tanto, pese a que cada conteder emplé para el despliegue tecnologías como Flask, Springboot u similares su despliegue, por decirlo de alguna forma, es homogéneo. Además, para evitar tener que desplegar cada contenedor de forma individual se proveen ficheros de especificación. Estos ficheros se encargan de especificar el estado deseado del entorno de despliegue. Por ejemplo, el nombre de un contenedor, el volúmen que se le conecta o las variables de entorno a inyectar.

## Configuraciones para el despliegue y puesta en producción
En nuestro caso, toda la configuración se realiza desde el fichero `docker-compose.yml` y `docker-swarm.yml`.  Para poder hacerlo dinámico y adaptarnos a distintas eventualizades cada servicio recive un conjunto de variables de entorno a través de los ficheros `docker-xxxxx.yml` que configuran los puertos y las direcciones de los servicios de los que dependen. De esta forma si cambiamos de dirección o de puerto un contenedor, el impacto de la acción es menor.

Para desplegar, si sólo contamos con un ordenador o nodo, necesitaremos tener Docker instalado. Desde las versionas más recientes Docker viene con `docker-compose` istalado. Sin embargo, puedes verificar si lo tienes instalado con `docker-compose --version`.

Una vez instalado, sitúate en la carpeta `release`. Desde aquí, sólo tendrás que ejecutar `docker-compose build --parallel` para construir todas las imágenes. Y, posteriormente `docker-compose up -d`. Este último comando iniciará todos los contenedores. Son muchos contenedores, entre los que se incluyen los de ELK, Neo4J, Redis, Postgress y otros cuantos que consumen muchos recursos. Así que, es posible que tarde un poco en desplegarse entero.

Para parar todos los contenedores, desde la propia carpeta de `release`, tendremos que realizar `docker-compose down`. Esto nos para y borra los contenedores y redes creadas duarante el despligue. Pero no los volúmenes. Para eliminar los volúmenes asociados y volver al estado en el que empezamos podemos hacer `docker volume ls` para ver la lista de volúmenes. Y, `docker volume rm <volume_id>` para eliminar el volúmen deseado. Si queremos eliminarlos todos `docker volume prune`. 

## URL y datos acceso al cliente web implementado

El cliente web implementado ofrece la funcionalidad de los administradores. Siendo el cliente móvil el único que permite el acceso a las funcionaldiades de usuario.

Para acceder al cliente web hay que acceder a [https://admin.easidiomas.wcr.es](https://admin.easidiomas.wcr.es). Con las credenciales `usuario:admin1, password:admin1`.

## Datos acceso al cliente móvil implementado
A continuacion se muestran algunos de los usuarios creados por defecto con los que se puede acceder al cliente móvil (formato "username" - "password"):
* alejgh - 1234
* carmenpl - 4321
* thewillyhuman - 56789
* mrmboy - 1234

Para más información sobre como arrancar el cliente móvil, se puede consultar [la documentación de la app]("./0307-Cliente-movil.md")

## Posibles problemas y soluciones aportadas
Un problema muy comun que podemos encontrarnos es que alguno de los puertos empledos esté ocupado. En este caso nos dirá que cuando lanzamos el contenedor X el puerto estaba ocupado y no se pudo realizar la acción. Esto afectaría a los contenedores que enlazan sus puertos internos con los públicos de la máquina en la que se despliega. En concreto son los contenedores de la API pública, del cliente web o de kafka. El resto de puertos están cerrados desde el exterior. Para solucionarlo tenemos dos opciones.
   1. Eliminamos el servicio que está ocupando el puerto.
   2. Cambiamos de puerto el contenedor.
En cualquiera de los casos se recomienda volver a dejar el entorno de Docker como al principio. Es decir, borrar contenedores y volúmenes.

Otro error que nos puede pasar es que en una ejecución previa algún contenedor se nos haya quedado lanzado. En este caso nos dirá que el nombre del contenedor ya está reservado. Para evitar esto hay que asegurarse de hacer `docker-compose down` desde la carpeta de `release` cada vez que terminamos una ejecución.

También existe la posibilidad que los créditos de las APIs externas empleadas se terminen o que los provedores dejen de dar servicio. En este caso habría que configurar unas nuevas credenciales dentro de cada uno de los servicios que empleen esa integración. O directamente emplear otra API.

Finalmente, otro error que podemos tener es hayamos cambiado el nombre de algún servicio en el fichero `docker-xxxxx.yml` y por tanto el nombre del servicio en el DNS interno de Docker sea distinto. Esto haría que las conexiones entre servisios no funcionasen. Para solucionarlo tenemos que asegurarnos que cuando cambiamos un servicio de nombre tenemos que actualziar también todas las referencias al nombre del servicio que se hacen desde el resto del fichero de configuración.
