#!/bin/bash

count=$(sudo docker ps -a | grep todo-db | wc -l)
echo $count
if [ $count -eq 1 ] 
   then
        echo "Create backup for todo-db"
        pg_dump -h localhost -p 5432 -U postgres > todo-backup.sql
   else
       echo "todo-db container does not exists.  Therefore no need to back up data"
fi
