package TodoList.TodoList.Services;

import TodoList.TodoList.Models.TodoItem;
import TodoList.TodoList.Models.TodoList;
import TodoList.TodoList.Repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;
    private final TodoListService todoListService;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository, TodoListService todoListService) {
        this.todoItemRepository = todoItemRepository;
        this.todoListService = todoListService;
    }

    public TodoItem createTodoItem(String title, String description, boolean completed, LocalDate dueDate, Long todoListId) {
        TodoList todoList = todoListService.getTodoListById(todoListId);
        if (todoList != null) {
            TodoItem newTodoItem = new TodoItem(title, description, completed, dueDate);
            newTodoItem.setTodoList(todoList);
            return todoItemRepository.save(newTodoItem);
        }
        return null; // Or you can throw an exception if the todoList is not found.
    }

    public TodoItem getTodoItemById(Long id) {
        return todoItemRepository.findById(id).orElse(null);
    }

    public List<TodoItem> getAllTodoItemsForTodoList(Long todoListId) {
        TodoList todoList = todoListService.getTodoListById(todoListId);
        if (todoList != null) {
            return todoItemRepository.findByTodoList(todoList);
        }
        return Collections.emptyList();
    }

    public TodoItem updateTodoItem(Long id, String title, String description, boolean completed, LocalDate dueDate) {
        TodoItem todoItem = todoItemRepository.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setTitle(title);
            todoItem.setDescription(description);
            todoItem.setCompleted(completed);
            todoItem.setDueDate(dueDate);
            return todoItemRepository.save(todoItem);
        }
        return null;
    }

    public void markTodoItemAsCompleted(Long id) {
        TodoItem todoItem = todoItemRepository.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setCompleted(true);
            todoItemRepository.save(todoItem);
        }
    }

    public void deleteTodoItem(Long id) {
        todoItemRepository.deleteById(id);
    }
}
