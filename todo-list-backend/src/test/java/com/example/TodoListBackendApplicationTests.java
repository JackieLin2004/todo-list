package com.example;

import com.example.service.TodoService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoListBackendApplicationTests {

    @Resource
    TodoService service;

    @Test
    void contextLoads() {
        System.out.println(service.showTodoList());

    }

}
