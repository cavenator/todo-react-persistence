package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Todo {
   
   @SequenceGenerator(name="todo_seq",sequenceName="todo_seq")
   @Id
   @GeneratedValue(generator="todo_seq")
   private Integer id;
   private String title;
   private String description;

   public Todo(){}

   public Todo(String title, String description){
      this.title = title;
      this.description = description;
   }

   public Integer getId(){
       return id;
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
