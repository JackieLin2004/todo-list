<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';

let emit = defineEmits(['addTask'])

let todoText = ref('');

// 添加任务方法
async function onAddTask() {
  if (!todoText.value.trim()) return;

  try {
    const response = await axios.post('http://localhost:8080/todo/item', {
      text: todoText.value,
      isDone: false,
      isEdit: false
    });

    todoText.value = '';  // 清空输入框
    emit('addTask', response.data);  // 将新任务传递给父组件
  } catch (error) {
    console.error('Failed to add task:', error);
  }
}
</script>

<template>

  <div class="input-row">
    <el-input style="width: 20em" placeholder="请输入一个待办事项..." v-model="todoText"/>
    <el-button type="success" @click="onAddTask">添加</el-button>
  </div>

</template>

<style scoped>

.input-row {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  gap: 0.5em;
}
</style>
