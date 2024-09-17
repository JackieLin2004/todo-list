package com.example.controller;

import com.example.entity.TodoItem;
import com.example.service.TodoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        service.save(item);
        return item;
    }

    @DeleteMapping("todo/item/{id}")
    public void deleteTodoItem(@PathVariable Integer id) {
        service.removeById(id);
    }

    @PutMapping("todo/item")
    public TodoItem updateTodoItem(@RequestBody TodoItem item) {
        service.updateById(item);
        return item;
    }

}
