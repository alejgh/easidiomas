# Authentication Service

Este servicio se encarga de **generar tokens** cuando un usuario se autentica. Y, por otro lado, se encarga de **validar los tokens** que se incluyen en las peticiones. Para ello expone dos métodos gRPC. Estos métodos validan que el usuario que se está autenticando exista en el sistema. Entonces, genera un pasaporte que se cifra y forma el token que tiene el usuario. Cada token tiene una validad predeterminada, en principio de 10 minutos.

## Variables de entorno
|Variable|Descipción|Valor por defecto|
|--------|----------|-----------------|
|SERVER_PORT|Puerto en el que se va a levantar el servidor.|5000|
|ENCRYPTION_KEY|Clave de encriptación AES.|aesEncryptionKey|
|ENCRYPTION_VECTOR|Semilla de inicialización del vector de encriptación AES.|encryptionIntVec|
|TOKEN_LIVE_TIME_MS|Tiempo en milisegundos que tiene de validez un token.|600000|

## Ejecución
Para levantar el servidor de este componente de forma aislada, lo más sencillo es ejecutar:
```shell
docker build -t easidiomas/authservice:latest .
docker run -p 5000:5000 easidiomas/authservice:latest
```