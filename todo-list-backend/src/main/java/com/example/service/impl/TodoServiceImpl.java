package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.TodoItem;
import com.example.mapper.TodoMapper;
import com.example.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, TodoItem> implements TodoService {

    @Override
    public List<TodoItem> showTodoList() {
        return this.list();
    }

    @Override
    public void addTodoItem(TodoItem item) {
        this.save(item);
    }

    @Override
    public void deleteTodoItem(Integer id) {
        this.removeById(id);
    }

    @Override
    public void updateTodoItem(TodoItem item) {
        this.updateById(item);
    }

}
