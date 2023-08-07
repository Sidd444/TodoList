package TodoList.TodoList.Controllers;


import TodoList.TodoList.Models.TodoItem;
import TodoList.TodoList.Services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/todo-items")
public class TodoItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping("/create/todo-items")
    public ResponseEntity<TodoItem> createTodoItem(@RequestParam String title, @RequestParam String description,
                                                   @RequestParam boolean completed, @RequestParam LocalDate dueDate,
                                                   @RequestParam Long todoListId) {
        TodoItem newTodoItem = todoItemService.createTodoItem(title, description, completed, dueDate, todoListId);
        if (newTodoItem != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newTodoItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long id) {
        TodoItem todoItem = todoItemService.getTodoItemById(id);
        if (todoItem != null) {
            return ResponseEntity.ok(todoItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllTodoItems(@RequestParam Long todoListId) {
        List<TodoItem> todoItems = todoItemService.getAllTodoItemsForTodoList(todoListId);
        return ResponseEntity.ok(todoItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id, @RequestParam String title,
                                                   @RequestParam String description, @RequestParam boolean completed,
                                                   @RequestParam LocalDate dueDate) {
        TodoItem updatedTodoItem = todoItemService.updateTodoItem(id, title, description, completed, dueDate);
        if (updatedTodoItem != null) {
            return ResponseEntity.ok(updatedTodoItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> markTodoItemAsCompleted(@PathVariable Long id) {
        todoItemService.markTodoItemAsCompleted(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
        return ResponseEntity.noContent().build();
    }
}
