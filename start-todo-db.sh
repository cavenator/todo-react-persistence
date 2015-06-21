#!/bin/bash

count=$(sudo docker ps -a | grep todo-db | wc -l)
echo $count
if [ $count -eq 1 ] 
   then
        echo "Starting todo-db for app"
        sudo docker start todo-db
   else
       echo "okay .... this is fucked up!"
fi
