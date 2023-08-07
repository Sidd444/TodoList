package TodoList.TodoList.Controllers;


import TodoList.TodoList.Models.TodoList;
import TodoList.TodoList.Models.User;
import TodoList.TodoList.Services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo-lists")
public class TodoListController {

    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping
    public ResponseEntity<TodoList> createTodoList(@RequestBody String name) {
        TodoList newTodoList = todoListService.createTodoList(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoList> getTodoListById(@PathVariable Long id) {
        TodoList todoList = todoListService.getTodoListById(id);
        if (todoList != null) {
            return ResponseEntity.ok(todoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoList>> getAllTodoLists(@RequestParam(name = "userId") Long userId) {
        User user = new User(); // Fetch user from the authentication context or database based on the userId
        List<TodoList> todoLists = todoListService.getAllTodoLists(user);
        return ResponseEntity.ok(todoLists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoList> updateTodoListName(@PathVariable Long id, @RequestBody String newName) {
        TodoList updatedTodoList = todoListService.updateTodoListName(id, newName);
        if (updatedTodoList != null) {
            return ResponseEntity.ok(updatedTodoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
        return ResponseEntity.noContent().build();
    }
}

