package org.example;

import com.mongodb.MongoClient;
import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;
import org.httpobjects.jetty.HttpObjectsJettyHandler;
import org.httpobjects.util.ClasspathResourceObject;
import org.httpobjects.util.ClasspathResourcesObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

   public static void main(String[] args) throws Exception {
       TodoDao todoDao;
       if (args.length > 0 && Boolean.valueOf(args[0])){
           String dbAddress = System.getenv("DB_PORT_27017_TCP_ADDR");
           String dbPort = System.getenv("DB_PORT_27017_TCP_PORT");
           todoDao = new TodoDao(new MongoClient(dbAddress, Integer.valueOf(dbPort)));
       } else {
           todoDao = new TodoDao();
       }

       final String RESOURCES_PATH = "/";

       ClasspathResourceObject rootResource = new ClasspathResourceObject(RESOURCES_PATH,"/index.html", Main.class);
       TodosResource todosResource = new TodosResource("/todo", todoDao);
       TodoResource todoResource = new TodoResource("/todo/{id}", todoDao);
       ClasspathResourcesObject staticResources = new ClasspathResourcesObject("/{resource*}", Main.class, RESOURCES_PATH);

       HttpObjectsJettyHandler.launchServer(8080, rootResource, todosResource, todoResource, staticResources);
   }

}
