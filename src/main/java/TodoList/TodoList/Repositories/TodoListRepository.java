package TodoList.TodoList.Repositories;

import TodoList.TodoList.Models.TodoList;
import TodoList.TodoList.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findByUser(User user);
}

