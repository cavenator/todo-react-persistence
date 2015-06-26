#!/bin/bash

count=$(sudo docker ps -a | grep todo-db | wc -l)
echo $count
if [ $count -eq 1 ] 
   then
        echo "Starting todo-db for app"
        sudo docker start todo-db
        echo "Note to self .... create data volume for this container and learn how to back it up in case of container failure"
   else
       echo "okay .... Lets make a new one!"
       /bin/bash ./create-db-container.sh
       sudo docker run -d --name=todo-db -p 127.0.0.1:5432:5432 postgres:9.1.18
       echo "Docker container running postgres db up and going .... going to wait 3 seconds before setting up db"
       sleep 3
       echo "creating todo table...."
       psql -h localhost -p 5432 -U postgres -c "create table todo(id integer, title varchar(40), description varchar(200));"
       echo "creating sequence for table ...."
       psql -h localhost -p 5432 -U postgres -c "create sequence todo_seq start 1;"
       echo "Done! Ready to rock and roll!"
fi
