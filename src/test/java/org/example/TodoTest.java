package org.example;
 
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import junit.framework.TestCase;
import org.bson.types.CodeWScope;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class TodoTest extends TestCase {
    TodoDao dao;
    final MongoClient mongoClient = new MongoClient("localhost",27017);
    final Morphia morphia = new Morphia().mapPackage("org.example");
    Datastore dataSource;

    @Override
    public void setUp(){
        dao = new TodoDao(morphia,mongoClient,"TestTodoDB");
    }
 
    public void testApp() {
        Todo todo = new Todo("Clean room", "You know you better do it before Mom gets home");

        Key<Todo> key = dao.save(todo);

        Todo fetchedTodo = dao.get((ObjectId) key.getId());

        assertEquals(fetchedTodo.getTitle(), todo.getTitle());

        dao.delete(fetchedTodo.getId());

        long howMany = dao.count();

        assertEquals(howMany, 0);

    }

    @Override
    public void tearDown(){
        dao.deleteAll();
    }
}
