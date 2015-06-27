FROM acave/java8

RUN /bin/bash -l -c "mkdir -p /app/todo-react-persistence"

ADD target/todo-app-persistence-0.1-SNAPSHOT.one-jar.jar /app/todo-react-persistence/todo-app.one-jar.jar

ENV PATH $PATH:$JAVA_HOME/bin

WORKDIR /app/todo-react-persistence

EXPOSE 8080

CMD ["java","-jar","todo-app.one-jar.jar"]
