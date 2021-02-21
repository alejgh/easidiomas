import time
import threading

def background(fun):
    """ Decorator to run a function in the background.

    Based on the implementation at https://amalgjose.com/2018/07/18/run-a-background-function-in-python/
    """
    def background_func(*args, **kwargs):
        threading.Thread(target=fun, args=args, kwargs=kwargs).start()
    return background_func
