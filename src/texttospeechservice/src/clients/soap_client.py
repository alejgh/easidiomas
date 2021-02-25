import logging
import zeep

from flask import current_app as app


logger = logging.getLogger(app.config['SERVICE_KEY'])
logger.setLevel(logging.DEBUG)


class SoapResult:
    def __init__(self, successful, result=None):
        self.successful = successful
        self.result = result


class SoapClient:
    """ Allows making request to a given SOAP endpoint.

    This class uses the ZEEP Python library (https://docs.python-zeep.org/en/master/index.html#)
    to allow users making requests to a SOAP endpoint.

    When the zeep client is created, it parses the WSDL given as an argument and creates the
    appropiate functions to call the service in the client class. This wrapper allows users
    to access those functions with introspection.

    Examples
    --------
    # this sample service provides a see_cars method that returns a list of cars
    >>> client = SoapClient('http://localhost:8080/mysoapws/cars.wsdl')
    >>> cars = client.see_cars() # cars will be a list of dicts (car 'objects')
    """

    def __init__(self, wsdl_endpoint):
        settings = zeep.Settings(strict=False, xml_huge_tree=True)
        self.client = zeep.Client(wsdl=wsdl_endpoint, settings=settings)
    
    def __getattribute__(self, attr_name, *args, **kwargs):
        logger.debug(f"Calling method of SOAP Client: {attr_name}")
        logger.debug(f"Args: {args} - Kwargs: {kwargs}")
        try:
            res = getattr(client.service, attr_name)(*args, **kwargs)
            logger.info(f"SOAP request was successful. Result: {res}")
            return SoapResult(True, result)
        except Exception as e:
            logger.error(f"There was an error calling the SOAP client: {e}")
            logger.debug("Returning unsuccessful SOAP result")
            return SoapResult(False)
