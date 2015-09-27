package org.example;
 
import junit.framework.TestCase;
 
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.List;
 
/**
 * Unit test for simple App.
 */
public class TodoTest extends TestCase {
    TodoDao dao;

    private Configuration configureHSQL(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(org.example.Todo.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        configuration.setProperty("hibernate.connection.url","jdbc:hsqldb:mem/todo");
        configuration.setProperty("hibernate.connection.username","sa");
        configuration.setProperty("hibernate.connection.password","");
        configuration.setProperty("hibernate.order_updates", "true");
        return configuration;
    }

    @Override
    public void setUp(){
        dao = new TodoDao(configureHSQL());
        Session session = dao.sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("create sequence todo_seq start with 1;").executeUpdate();
        session.createSQLQuery("create table todo(id integer generated by default as identity, title varchar(40), description varchar(200))").executeUpdate();
        session.getTransaction().commit();
        session.flush();

        session.close();
    }
 
    public void testApp() {
        Todo todo = new Todo("Clean room", "You know you better do it before Mom gets home");

        Integer id = (Integer) dao.save(todo);

        Todo fetchedTodo = dao.get(id);

        assertEquals(fetchedTodo.getTitle(), todo.getTitle());

        dao.delete(fetchedTodo.getId());

        Todo nullTodo = dao.get(id);

        assertNull(nullTodo);

    }

    @Override
    public void tearDown(){
        Session session = dao.sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP SEQUENCE TODO_SEQ").executeUpdate();
        session.createSQLQuery("DROP TABLE TODO").executeUpdate();
        session.getTransaction().commit();
        session.flush();
        session.close();

        dao.sessionFactory.close();
    }
}
