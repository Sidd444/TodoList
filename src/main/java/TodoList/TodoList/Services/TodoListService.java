package TodoList.TodoList.Services;


import TodoList.TodoList.Models.TodoList;
import TodoList.TodoList.Models.User;
import TodoList.TodoList.Repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;

    @Autowired
    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoList createTodoList(String name) {
        // Implement creating a new TodoList with the given name
        TodoList newTodoList = new TodoList(name);
        return todoListRepository.save(newTodoList);
    }

    public TodoList getTodoListById(Long id) {
        // Implement getting a TodoList by its ID
        return todoListRepository.findById(id).orElse(null);
    }

    public List<TodoList> getAllTodoLists(User user) {
        // Implement getting all TodoLists for the given user
        return todoListRepository.findByUser(user);
    }

    public TodoList updateTodoListName(Long id, String newName) {
        // Implement updating the name of a TodoList by its ID
        TodoList todoList = todoListRepository.findById(id).orElse(null);
        if (todoList != null) {
            todoList.setName(newName);
            return todoListRepository.save(todoList);
        }
        return null;
    }

    public void deleteTodoList(Long id) {
        // Implement deleting a TodoList by its ID
        todoListRepository.deleteById(id);
    }

}

