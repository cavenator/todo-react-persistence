#!/bin/bash

count=$(sudo docker ps -a | grep todo-mongo | wc -l)
echo $count
if [ $count -eq 1 ] 
   then
        echo "Starting todo-mongo for app"
        sudo docker start todo-mongo
        echo "Note to self .... create data volume for this container and learn how to back it up in case of container failure"
   else
       echo "okay .... Lets make a new one!"
       sudo docker run -d -p 127.0.0.1:27017:27017 --name=todo-mongo dockerfile/mongodb
       echo "Docker container running MongoDB up and going .... going to wait 3 seconds so container can finish loading"
       sleep 3
       echo "Done! Ready to rock and roll!"
fi
