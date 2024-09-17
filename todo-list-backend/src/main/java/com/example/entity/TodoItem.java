package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("`todo-item`")
@AllArgsConstructor
public class TodoItem {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String text;
    @TableField("is_done")
    private boolean isDone;
    private boolean isEdit;
}
