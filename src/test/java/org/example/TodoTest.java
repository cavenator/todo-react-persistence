package org.example;
 
import junit.framework.TestCase;
 
import org.example.Todo;
import org.example.TodoDao;
import java.util.List;
 
/**
 * Unit test for simple App.
 */
public class TodoTest extends TestCase {
 
    public void testApp() {
        TodoDao dao = new TodoDao();

        Todo todo = new Todo("Clean room", "You know you better do it before Mom gets home");

        Integer id = (Integer) dao.save(todo);

        Todo fetchedTodo = dao.get(id);

        assertEquals(fetchedTodo.getTitle(), todo.getTitle());

        dao.delete(fetchedTodo);

        List<Todo> todos = dao.get();

        assertEquals(todos.size(), 0);
    }
}
