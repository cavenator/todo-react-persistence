package org.example;

import org.hibernate.cfg.Configuration;
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

   private static Configuration getDockerDBConfiguration(){
       String dbAddress = System.getenv("DB_PORT_5432_TCP_ADDR");
       String dbPort = System.getenv("DB_PORT_5432_TCP_PORT");
       String dbUrl = "jdbc:postgresql://"+dbAddress+":"+dbPort+"/postgres";
       Configuration configuration = new Configuration();
       configuration.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");
       configuration.setProperty("hibernate.connection.url",dbUrl);
       configuration.setProperty("hibernate.connection.username","postgres");
       configuration.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
       configuration.addAnnotatedClass(org.example.Todo.class);
       return configuration;
   }

   public static void main(String[] args) throws Exception {
       TodoDao todoDao;
       if (args.length > 0 && Boolean.valueOf(args[0])){
          todoDao = new TodoDao(getDockerDBConfiguration());
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
