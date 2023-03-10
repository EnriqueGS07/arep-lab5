# TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER Y A AWS
- El taller consiste en crear una aplicación web pequeña usando el micro-framework de Spark java (http://sparkjava.com/). Una vez tengamos esta aplicación procederemos a construir un container para docker para la aplicación y los desplegaremos y configuraremos en nuestra máquina local. Luego, cerremos un repositorio en DockerHub y subiremos la imagen al repositorio. Finalmente, crearemos una máquina virtual de en AWS, instalaremos Docker , y desplegaremos el contenedor que acabamos de crear.

## Iniciando


### Construcción:
- Docker images
- Amazon web services
- Spring-boot y spark
- [Maven](https://maven.apache.org/) - Dependency Management

### Arquitectura de la aplicación
![image](https://user-images.githubusercontent.com/98104282/224224272-0a43419b-be1d-4806-b946-0f703e285c54.png)

- El servicio MongoDB es una instancia de MongoDB corriendo en un container de docker en una máquina virtual de EC2
- LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde en un objeto JSON con las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.
- La aplicación web APP-LB-RoundRobin está compuesta por un cliente web y al menos un servicio REST. El cliente web tiene un campo y un botón y cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información que este le regresa en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de balanceo de cargas de Round Robin, delegando el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.

### Pruebas 
Despues de desplegar el servicio se realizaron pruebas
- Primero se accede al servidor web

![image](https://user-images.githubusercontent.com/98104282/224224454-4d632392-95b6-4561-84d1-8e7ea9db4c26.png)

- Luego se creo un log y se pidio obtener todos los logs para ver si se agrego

![image](https://user-images.githubusercontent.com/98104282/224224602-30f460fa-61e6-4165-9d9a-79ae5ed04d0a.png)

- Se realizo el mismo proceso con un uevo log 

![image](https://user-images.githubusercontent.com/98104282/224224701-a366db29-a550-44d5-ad1c-15debec85da4.png)



### Versionamiento
- Version 1.0.0
### Autores
- Enrique González Suárez



