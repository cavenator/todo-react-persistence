package org.example;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Todo {
   
   @Id
   private ObjectId id;
   private String title;
   private String description;

   public Todo(){}

   public Todo(String title, String description){
      this.title = title;
      this.description = description;
   }

   public ObjectId getId(){
       return this.id;
   }

   public void setDescription(String description){
       this.description=description;
   }

   public String getDescription(){
       return this.description;
   }

   public void setTitle(String title){
       this.title = title;
   }

   public String getTitle(){
       return this.title;
   }

   public TodoDto toDto(){
        return new TodoDto(this.id, this.title, this.description);
   }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (description != null ? !description.equals(todo.description) : todo.description != null) return false;
        if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
        if (title != null ? !title.equals(todo.title) : todo.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
