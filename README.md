# Laboratorio 3 - Taller de Base de Datos/Bases de datos Avanzadas
## Este laboratorio fue desarrollado por el grupo n°5.
### Integrantes de grupo 5
* Jaime Riquelme
* Fabián Lizama
* Fernando Solís
* Cristóbal Torres
* Camilo Campos

# Herramientas de desarrollo

## Base de datos
* MongoDB
* MongoDBCompass
* Visual Studio Code

## Backend
* IntelliJ IDEA Ultimate
* JDK versión 17
* Postman

## Frontend
* VUE 3
* Nuxt 3 


# Guía de Instalación y Ejecución
Esta guía te ayudará a configurar tu entorno de desarrollo para trabajar con IntelliJ IDEA y MongoDB.

## 1. Clonar el repositorio

git clone https://github.com/FabianLizama/tbd-lab/tree/Lab3

## 2. Instalación de IntelliJ IDEA

1. Descarga IntelliJ IDEA desde el sitio web oficial: [Descargar IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

2. Instala IntelliJ IDEA siguiendo las instrucciones específicas para tu sistema operativo.

## 3. Instalación de MongoDB

1. Descarga MongoDB desde el sitio web oficial: [Descargar MongoDB](https://www.mongodb.com/try/download/community).

2. Sigue las instrucciones de instalación para tu sistema operativo.

3. Para verificar la instalación, abre una terminal y ejecuta `mongod --version` y `mongosh`.

## 4. Crear base de datos y cargar información

1. Abre una terminal, ve a la dirección /lab3 y ejecuta: 
```bash
mongosh < DataLab3.js
```
2. Así ya tendrás creada la base de datos y se cargarán los datos de prueba.
   
## 5. Inicialización del proyecto por IntelliJ IDEA

1. Abre IntelliJ IDEA y abre el proyecto ubicado en tdb-lab/Backend y presiona el botón de "Play" para ejecutar el proyecto.

## 6. Ejecución Frontend por vue

Asumiendo que ya tienes un proyecto de Vue.js en tu carpeta de frontend, sigue estos pasos para ejecutarlo:

1. Abre una terminal en la carpeta de tu proyecto Vue.js.

2. Asegúrate de haber instalado Node.js y npm. Si no los tienes instalados, puedes descargarlos desde [https://nodejs.org/](https://nodejs.org/).

3. Instala las dependencias del proyecto ejecutando el siguiente comando:

   ```bash
   npm install
   ```
   
4. Ejecuta el comando
   ```bash
   npm run dev
   ```

## 6. Accede a la aplicacion

1. Dirigete a la URL desplegada por el front, [http://localhost:3000/](http://localhost:3000/).

2. Listo.

Ahora puedes realizar ejecuciones dentro de la aplicación mediante la vista del navegador.