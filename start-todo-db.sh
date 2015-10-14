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
       sudo docker run -d --name=todo-db -p 127.0.0.1:5432:5432 postgres:9.1.18
       echo "Docker container running postgres db up and going .... going to wait 3 seconds before setting up db"
       sleep 3
       echo "creating todo table...."
       psql -h localhost -p 5432 -U postgres < init_todo_db.sql
       echo "Done! Ready to rock and roll!"
fi
