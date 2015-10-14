#todo-react-persistence
An extension to the [https://github.com/cavenator/todo-react](todo-react) project by adding a persistence layer.  The persistence layer is composed of a PosgresSQL DB wrapped inside a docker container.  This project does utilize the following:

* Docker (for creating images of this project as well as creating the DB)
* Docker Compose (leverage Docker for easy app startup, shutdown)
* Maven (Build tool)
* Hibernate (ORM)
* Java 8

**To start up the app with Docker Compose (easiest but you _must_ have Docker + Compose installed):**

* from project directory, "sudo docker-compose up" (Ignore "sudo" if Docker and Compose have sudo rights)
* using Posgres client, use the init-todo-db.sql to build the database
* enter http://localhost:8080 into a browser and have fun.

**To start up the app with just Docker (more involved but still easy)**

* from project directory, execute "start-todo-db.sh" to create the Docker container and build the db within it.
* Build the project by executing "mvn clean package"
* start the app by executing java -jar target/todo-app-persistence-0.1-SNAPSHOT.one-jar.jar
* enter http://localhost:8080 in your browser and have fun.

**If you do not wish to use Docker or Compose, you can start up the app by doing the following:**

* setup your own PostgresDB. Once complete, You can use the init-todo-db.sql to initialize the database.
* Build the project by executing "mvn clean package"
* start the app by executing java -jar target/todo-app-persistence-0.1-SNAPSHOT.one-jar.jar
* enter http://localhost:8080 in your browser and have fun.
