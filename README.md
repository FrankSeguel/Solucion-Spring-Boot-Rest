# Solucion-Spring-Boot-Rest

En este proyecto encontraras :

1.- Aplicación con la publicacion de servicios rest.
    http://localhost:8080/clientes/
    http://localhost:8080/clientes/abonos/111/3000
    http://localhost:8080/clientes/movimientos/111
    http://localhost:8080/clientes/saldo/111
    http://localhost:8080/clientes/retiros/111/11000
    
2.- El proyecto 

3.- Patrón de diseño reflection. Uno de mis patrones favoritos con la que es posible acceder a atributos de la clases hija.
    En este caso la utilizamos para el método toString. Practico para el debug de los logges
    También en la clase servicio utilice "Static Initialization Blocks"
    https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html

4.- La aplicación se entrega configurada para funcionar con Docker. Para ser mas fácil su utilización. Se crearon 2 shell :
    
    Dockerfile : Archivo de docker.
    docker-build.sh : Shell que permite borrar el contener si existe y compilar la nueva imagen
    docker-run.sh : Permite crear un contenedor previamente compilado.

5.- Shell java-build.sh Permite compilar el proyecto previamente de haver configurado el JAVA_HOME y MAVEN_HOME

6.- Tambien el proyecto genera una archivo "api.raml y api.json" estos descriptores de los servicios 
    Luego nos serviran para realizar una integracion con una Api Manager como por ejemplo wso2 api manager.
    https://wso2.com/api-management/

    El archivo api.json se puede subir a esta pagina para revisar la estructura de los servicios rest.
    https://editor.swagger.io/

7.- La solucion JAR se encuentra en la carpeta "target/solution-1.0-SNAPSHOT-dist" en esta carpeta se entrega una aplicacion 
    100% portable con su respectivo jar y sus lib.

8.- La aplicacion esta configurada para trabajar con la Base de datos postgres y H2 (En memoria o file). 
    H2 esta en una carpeta temporal en la raiz del proyecto tmp.
        http://localhost:8080/h2-console/ Para acceder a la base de datos h2 esta con la consola.

    Postgres el modelo de la base de datos esta en la carpeta :
        /Solucion-Spring-Boot-Rest/src/main/resources/schema.sql Modelo de la base de datos
        /Solucion-Spring-Boot-Rest/src/main/resources/data.sql   Datos de Prueba

9.- En la definicion del properties se utilizo la convencion de Spring
https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/common-application-properties.html

11.- El proyecto lo he dejado publico en github
https://github.com/FrankSeguel/solution-Spring-Boot-Crud-Rest