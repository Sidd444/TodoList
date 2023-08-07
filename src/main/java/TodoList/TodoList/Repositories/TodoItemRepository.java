package TodoList.TodoList.Repositories;

import TodoList.TodoList.Models.TodoItem;
import TodoList.TodoList.Models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByTodoList(TodoList todoList);
}

