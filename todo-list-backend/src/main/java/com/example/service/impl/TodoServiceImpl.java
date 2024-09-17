package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.TodoItem;
import com.example.mapper.TodoMapper;
import com.example.service.TodoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void updateIsDone(TodoItem item) {
        // 获取当前记录的 is_done 状态
        TodoItem currentItem = this.getById(item.getId());
        if (currentItem != null) {
            // 切换 is_done 的值，如果当前是 true (1)，则改为 false (0)，反之亦然
            boolean newIsDone = !currentItem.isDone();

            // 使用 UpdateWrapper 更新 is_done 字段
            UpdateWrapper<TodoItem> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", item.getId()).set("is_done", newIsDone ? 1 : 0);

            // 执行更新操作
            boolean isUpdated = this.update(updateWrapper);
            System.out.println("Update status: " + isUpdated);
        } else {
            System.out.println("Item with id " + item.getId() + " not found.");
        }
    }

}
