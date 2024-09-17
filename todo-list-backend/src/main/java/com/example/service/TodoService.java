package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.TodoItem;

import java.util.List;

public interface TodoService extends IService<TodoItem> {
    List<TodoItem> showTodoList();

    void addTodoItem(TodoItem item);

    void deleteTodoItem(Integer id);

    void updateTodoItem(TodoItem item);

    void updateIsDone(TodoItem item);
}
