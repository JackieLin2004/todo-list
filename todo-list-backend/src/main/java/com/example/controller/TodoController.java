package com.example.controller;

import com.example.entity.TodoItem;
import com.example.service.TodoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "http://localhost:5173")
@RestController
public class TodoController {

    @Resource
    TodoService service;

    @GetMapping("todo/list")
    public List<TodoItem> getTodoItems() {
        return service.showTodoList();
    }


    @PostMapping("todo/item")
    public TodoItem addTodoItem(@RequestBody TodoItem item) {
        service.addTodoItem(item);
        return item;
    }

    @DeleteMapping("todo/item/{id}")
    public void deleteTodoItem(@PathVariable Integer id) {
        service.deleteTodoItem(id);
    }


    @PutMapping("todo/item")
    public void updateTodoItem(@RequestBody TodoItem item) {
        service.updateById(item);
    }

    @PutMapping("/todo/item/isdone")
    public TodoItem updateIsDone(@RequestBody TodoItem item) {
        service.updateIsDone(item);
        return item;
    }

}
