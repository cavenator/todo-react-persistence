package org.example;

import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class TodosResource extends HttpObject {
    TodoDao todoDao;
    public TodosResource(String pathPattern, TodoDao todoDao){
        super(pathPattern);
        this.todoDao = todoDao;
    }

    @Override
    public Response get(Request req) {
        List<Todo> todos = todoDao.get();
        List<TodoDto> todoDtos = todos.stream().map(Todo::toDto).collect(Collectors.toList());
        return OK(Json(JsonUtil.toJson(todoDtos)));
    }

    @Override
    public Response post(Request req) {
        OutputStream outputStream = new ByteArrayOutputStream();
        req.representation().write(outputStream);
        String out = outputStream.toString();

        TodoDto dto = JsonUtil.fromJson(out, TodoDto.class);
        Todo todo = new Todo(dto.title, dto.description);
        Integer id = (Integer) todoDao.save(todo);
        Todo fetchedTodo = todoDao.get(id);
        return OK(Json(JsonUtil.toJson(fetchedTodo.toDto())));
    }

    @Override
    public Response delete(Request req) {
        todoDao.deleteAll();
        return OK(null);
    }
}
