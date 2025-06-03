# VitalSanity: La Aplicación Web donde el Paciente es el Protagonista

# URL de producción de **VitalSanity**

### Puedes acceder a la **URL** de producción a través este [**<u>enlace</u>**](https://vitalsanity.net:11443/vital-sanity).

## Breve explicación de la puesta en producción

Para la puesta en producción se ha utilizado **AWS**, aprovechando las tecnologías y herramientas **explicadas** en la **memoria** y que ya habían sido **configuradas**
durante el **desarrollo** de la aplicación (**Amazon S3**, **IAM**, **políticas de seguridad**, **región de los buckets de desarrollo y producción**, ...).
Destacar que se ha empleado **Amazon RDS** para la configuración de la base de datos **PostgreSQL** en producción y **AWS Elastic Beanstalk** para desplegar la aplicación de forma **sencilla** y **escalable**.

Asimismo, como en todo despliegue en **producción**, se ha configurado un **dominio** y se ha utilizado un certificado **SSL** de confianza.

Además, como se comentó en la memoria dentro del capítulo de **_'Implementación'_**, en producción se ha prescindido de **Mailtrap**.
En su lugar, se han cambiado las credenciales asociadas al servidor **SMTP** de la aplicación para que los emails sean enviados por una cuenta de **Gmail real** (**vitalsanitysx@gmail.com**).
De esta forma, todos los usuarios de la aplicación pueden verdaderamente recibir y visualizar desde su cuenta de correo los **emails** enviados por la aplicación.

Para reflejar todos estos cambios de forma independiente a la versión de desarrollo, todas estas configuraciones se han establecido dentro de un nuevo perfil de **Spring Boot** denominado '**_prod_**' (fichero **_application-prod.properties_**).

Destacar que el objetivo principal de la puesta en producción ha sido, por un lado, el ir un paso más en el **ciclo del desarrollo de Software** para **VitalSanity** y, por otro lado,
el facilitar la comprobación de las funcionalidades de la aplicación a cualquier persona que desee acceder a ella. Asimismo, mencionar que la [**<u>URL</u>**](https://vitalsanity.net:11443/vital-sanity) de **producción** se utilizará el día de la **defensa** del **TFG** para poder mostrar
las funcionalidades de la aplicación de forma **sencilla** y **natural**.

También conviene precisar que, en términos estrictos, el despliegue que se ha realizado se corresponde en realidad con un entorno de **staging**.
Esto es así puesto que no se están utilizando datos de usuarios reales,
sino datos ficticios que son usados para crear una "**_zona de pruebas_**" que permita **replicar fielmente** el entorno de **producción** real.
De este modo, se puede verificar el **correcto funcionamiento** de todas las funcionalidades de la aplicación de una forma **sencilla** y **realista**.

Por último, mencionar que **la autenticación con certificado digital** admite los dos siguientes tipos de certificado:

- Certificado **válido** y **oficial** emitido por la **Fábrica Nacional de Moneda y Timbre-Real Casa de la Moneda** **(FNMT-RCM)**.
- Certificado **válido** y **oficial** emitido por la **Agencia de Tecnología y Certificación Electrónica** **(ACCV)**.

## Datos para probar la aplicación

Para probar la aplicación, se han creado una serie de **usuarios de prueba** junto con otros datos de ejemplo (como documentos, informes médicos, solicitudes de autorización, ...).

Estos son los usuarios de prueba (la contraseña para todos ellos es **vitalsanity123** y se puede iniciar sesión con **email** y **contraseña**. Además, para cada usuario se incluye también su **NIF/NIE**):

- **sergio-castillo-blanco@gmail.com** (**Tipo de usuario:** Paciente) (**NIF:** 60704841K)
- **carmen-ruiz-herrera@gmail.com** (**Tipo de usuario:** Paciente) (**NIF:** 87952828T)
- **cacs2@alu.ua.es** (**Tipo de usuario:** Profesional médico) (**NIF:** 05988721G)
- **juan.perez@example.com** (**Tipo de usuario:** Profesional médico) (**NIF:** 63297648S)
- **maria.lopez@example.com** (**Tipo de usuario:** Profesional médico) (**NIF:** 87654321X)
- **carlos.garcia@example.com** (**Tipo de usuario:** Profesional médico) (**NIF:** 11223344Y)
- **monica.garcia@example.com** (**Tipo de usuario:** Profesional médico) (**NIF:** 12345678Z)
- **manuel.gimenez@example.com** (**Tipo de usuario:** Profesional médico) (**NIE:** X0655490J)
- **laura.hernandez@example.com** (**Tipo de usuario:** Profesional médico) (**NIF:** 48733805W)
- **pablo.rodriguez@example.com** (**Tipo de usuario:** Profesional médico) (**NIF:** 45908922W)
- **admin@gmail.com** (**Tipo de usuario:** Administrador) (**NIF:** 54083179J)
- **quiron-puertollano@example.es** (**Tipo de usuario:** Centro médico) (**NIF:** B99877292)
- **adeslas-alicante@example.es** (**Tipo de usuario:** Centro médico) (**NIF:** A37843505)

Destacar que arriba se han mencionado únicamente los **datos principales** para cada usuario
(como es evidente, existen **más** datos asociados a un usuario: el número de teléfono, la provincia, la localidad, ...).

Se vuelve a hacer énfasis en que todos estos datos son **ficticios** (salvo "cacs2@alu.ua.es" que soy yo mismo, usuario que he creado para poder probar el inicio de sesión con mis propios certificados digitales).

En caso de querer probar la aplicación en local, se pueden insertar manualmente todos estos datos de prueba ejecutando el fichero de **seeders** **_resources/sql/database-script/seed_develop_db.sql_** (esto se explica a continuación).

# Instrucciones para ejecutar el proyecto en local

Cabe destacar que es altamente recomendable probar la aplicación **directamente** desde la [**<u>URL</u>**](https://vitalsanity.net:11443/vital-sanity) de **producción**.
Aparte de que es mucho más cómodo, en el código del proyecto se han ocultado (por razones obvias) todas aquellas **credenciales sensibles** (como las credenciales de **AWS**).

Por esta razón, si se quisiera probar la aplicación en local en su totalidad, se debería de modificar manualmente los ficheros de configuración (los ficheros **_.properties_**) para introducir **credenciales propias**.

Aun así, mencionar que se puede probar en local (y sin introducir ningunas credenciales de forma manual) todas las funcionalidades (incluyendo las que involucran a **Autofirma** y/o **Cliente móvil @firma**)
salvo visualizar la recepción de correos en **Mailtrap** y aquellas que involucren subida o descarga de ficheros de **Amazon S3**.

Hechas estas aclaraciones, a continuación se explican las instrucciones para ejecutar el proyecto en local.

## OPCIÓN 1: Levantar el Proyecto usando Docker Compose

Gracias la configuración establecida con **Docker Compose**, la aplicación puede ejecutarse
desde cualquier ordenador mediante los siguientes comandos:

```sh
./mvnw clean package
```

```sh
docker compose up
```

El único requisito necesario es tener instalado **Docker**. Esta es la opción más cómoda y rápida. Asimismo, con esta opción se ejecuta automáticamente el fichero de **seeders**.

## OPCIÓN 2: Ejecutar la aplicación cargando el perfil de Postgres

En este caso, es necesario tener instalado lo siguiente:

- **JDK 21**
- **Docker (cualquier versión moderna)**

Teniendo esos requisitos instalados, la aplicación también puede ser ejecutada mediante los siguientes comandos:

En primer lugar, ejecutar este comando para levantar la base de datos de ‘**PostgreSQL**‘
en un contenedor de ‘**Docker**‘.

```sh
docker run --name postgres-vitalsanity-develop -e POSTGRES_USER=vital -e POSTGRES_PASSWORD=vital -e POSTGRES_DB=vital -p 5058:5432 -d postgres:13
```

A continuación, ejecutar el siguiente comando para arrancar la aplicación cargando el
perfil de **postgres**:

```sh
mvn spring-boot:run -D spring-boot.run.profiles=postgres
```

Por último, en este caso es necesario ejecutar manualmente el fichero de seeders **_resources/sql/database-script/seed_develop_db.sql_**

En efecto, observamos como es mucho más cómodo y rápido usar directamente la [**<u>URL</u>**](https://vitalsanity.net:11443/vital-sanity) de **producción**.
