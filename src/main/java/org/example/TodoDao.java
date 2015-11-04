package org.example;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class TodoDao extends BasicDAO<Todo, ObjectId>{
   public TodoDao(){
      super(new MongoClient("localhost", 27017), new Morphia().mapPackage("org.example"), "TodoDB");
   }

   public TodoDao(Morphia morphia, MongoClient mongo, String DbName ){
      super(mongo, morphia, DbName);
   }

   public void delete(ObjectId id){
      Query<Todo> deleteQuery = getDatastore().createQuery(Todo.class).field("id").equal(id);
      deleteByQuery(deleteQuery);
   }

   public void deleteAll(){
      Query<Todo> deleteAllQuery = getDatastore().createQuery(Todo.class);
      deleteByQuery(deleteAllQuery);
   }

   public List<Todo> get(){
      return getDatastore().find(Todo.class).asList();
   }

   public Todo get(ObjectId id){
      return getDatastore().get(Todo.class, id);
   }
}
