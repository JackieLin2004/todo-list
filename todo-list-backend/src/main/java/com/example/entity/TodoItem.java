package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("todo-item")
@AllArgsConstructor
public class TodoItem {
    @TableId(type = IdType.AUTO)
    Integer id;
    String text;
    boolean isDone;
    boolean isEdit;
}
