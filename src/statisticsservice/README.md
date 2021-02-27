# Servicio de estadísticas

El servicio de estadísticas se encarga de guardar eventos del sistema e indexarlos de la forma oportuna.
Por ejemplo, desglosando por idioma o por acción.

## ¿Cómo ejecutarlo?
Se puede ejecutar `docker-compose up` sobre el directorio raíz y se levanta una instancia del sistema y de
la base de datos redis que utiliza. Para conectarnos sólo tendremos que entrar en `http://localhost:5000/soapws/statistics?wsdl`.