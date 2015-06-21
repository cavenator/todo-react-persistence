package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.io.Serializable;
import org.hibernate.Query;

public class TodoDao {
   private SessionFactory sessionFactory;

   public TodoDao(){
       sessionFactory = new Configuration().configure().buildSessionFactory();
   }

   public Integer save(Todo todo){
       Session session = sessionFactory.openSession();
       Transaction t = session.beginTransaction();

       Serializable id = session.save(todo);
       t.commit();
       session.flush();
       session.close();
       return (Integer) id;
   }

   public void deleteAll(){
       Session session = sessionFactory.openSession();
       session.beginTransaction();

       Query query = session.createQuery("delete Todo");
       query.executeUpdate();
       session.getTransaction().commit();
       session.flush();
       session.close();
   }
   
   public void delete(Integer id){
       Session session = sessionFactory.openSession();
       session.beginTransaction();

       Query query = session.createQuery("delete Todo where id = ?");
       query.setParameter(0, id);
       query.executeUpdate();
       session.getTransaction().commit();
       session.flush();
       session.close();
   }

   public List<Todo> get(){
       Session session = sessionFactory.openSession();
       session.beginTransaction();

       Query query = session.createQuery("from Todo");
       List<Todo> todos = query.list();
       session.getTransaction().commit();
       session.flush();
       session.close();
       return todos;
   }

   public Todo get(Integer id){
       Session session = sessionFactory.openSession();
       session.beginTransaction();

       Todo todo = (Todo) session.get(Todo.class, id);
       session.getTransaction().commit();
       session.flush();
       session.close();
       return todo;
   }
}
