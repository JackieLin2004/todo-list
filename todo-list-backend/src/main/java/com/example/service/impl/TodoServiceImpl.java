package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.TodoItem;
import com.example.mapper.TodoMapper;
import com.example.service.TodoService;

public class TodoServiceImpl extends ServiceImpl<TodoMapper, TodoItem> implements TodoService {
}
