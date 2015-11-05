FROM ubuntu:14.04

# Install Java 8
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections 
RUN apt-get install -y software-properties-common git wget
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update
RUN apt-get install -y oracle-java8-installer
RUN rm -rf /var/lib/apt/lists/*
RUN rm -rf /var/cache/oracle-jdk8-installer

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

#install maven
RUN /bin/bash -c -l "wget http://apache.arvixe.com/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz"

RUN /bin/bash -c -l "tar -zxf apache-maven-3.2.5-bin.tar.gz"

RUN /bin/bash -c -l "cp -R apache-maven-3.2.5 /usr/local"

RUN /bin/bash -c -l "ln -s /usr/local/apache-maven-3.2.5/bin/mvn /usr/bin/mvn"

#pull project from GIT
RUN mkdir -p /app/todo-react-persistence

ADD . /app/todo-react-persistence

WORKDIR /app/todo-react-persistence

#package the one-jar
RUN /bin/bash -c -l "mvn package -DskipTests"

#expose the port
EXPOSE 8080

ENTRYPOINT ["java","-jar","target/todo-app-persistence-0.1-SNAPSHOT.one-jar.jar","true"]

