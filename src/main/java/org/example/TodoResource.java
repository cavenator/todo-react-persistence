package org.example;

import org.bson.types.ObjectId;
import org.httpobjects.HttpObject;
import org.httpobjects.Request;
import org.httpobjects.Response;

import java.util.HashMap;
import java.util.Map;

public class TodoResource extends HttpObject {
    private TodoDao todoDao;

    public TodoResource(String pathPattern, TodoDao todoDao){
        super(pathPattern);
        this.todoDao = todoDao;
    }

    @Override
    public Response delete(Request req) {
        String id = req.path().valueFor("id");
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (Exception e){
            return BAD_REQUEST(Json(JsonUtil.toJson(new ErrorDto("invalid id: "+id))));
        }

        todoDao.delete(objectId);
        return NO_CONTENT();
    }

    @Override
    public Response get(Request req) {
        String id = req.path().valueFor("id");
        ObjectId objectId;
        try {
            objectId = new ObjectId(id);
        } catch (Exception e){
            return BAD_REQUEST(Json(JsonUtil.toJson(new ErrorDto("invalid id: "+id))));
        }

        Todo todo = todoDao.get(objectId);
        if (todo != null)
            return OK(Json(JsonUtil.toJson(todo.toDto())));

        return BAD_REQUEST(Json(JsonUtil.toJson(new ErrorDto("todo does not exist with id: "+id))));
    }
}
