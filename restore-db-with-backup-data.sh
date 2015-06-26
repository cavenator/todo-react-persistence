#!/bin/bash

count=$(ls | grep todo-backup.sql  | wc -l)
if [ $count -eq 1 ] 
   then
        read -r -p "Warning:  This will overwrite all data in your postgres. Are you sure? [y/N] " response
        case $response in
                [yY][eE][sS]|[yY]) 
                    container_count=$(sudo docker ps -a | grep todo-db | wc -l)
                    if [ $container_count -eq 0 ]
                        then
                            echo "DB doesn't exist .... spinning empty container now"
                            sudo docker run -d --name=todo-db -p 127.0.0.1:5432:5432 postgres:9.1.18
                            sleep 3
                    fi
                    psql -h localhost -p 5432 -U postgres < todo-backup.sql
                    sleep 1
                    echo "data should be restored. Executing count command to verify ...."
                    psql -h localhost -p 5432 -U postgres -c "select count(*) from todo;"
                                    ;;
                *)
                  echo "aborting ....."
                                   ;;
        esac
   else
       echo "No backup data available. execute create-backup-of-db script to create a backup and look for the todo-backup.sql file afterwards"
fi
