<script setup lang="ts">
import {ref, onMounted} from 'vue'
import TaskInput from '@/components/TaskInput.vue'
import TaskItem from '@/components/TaskItem.vue'
import axios from 'axios'
import type {ITaskItem} from '@/types/ITaskItem'

let taskList = ref<ITaskItem[]>([]);

// 获取任务列表
const fetchTasks = async () => {
  try {
    const response = await axios.get('http://localhost:8080/todo/list');  // 访问后端接口
    taskList.value = response.data;
  } catch (error) {
    console.error('Error fetching tasks:', error);
  }
}

// 添加任务
const onAddTask = (newTask: ITaskItem) => {
  taskList.value.push(newTask);  // 将新任务推入任务列表
};

// 删除任务
async function onDelete(item: ITaskItem) {
  try {
    await axios.delete(`http://localhost:8080/todo/item/${item.id}`);
    taskList.value = taskList.value.filter(it => it !== item);
  } catch (error) {
    console.error('Error deleting task:', error);
  }
}

onMounted(() => {
  fetchTasks();  // 页面挂载时获取任务列表
});
</script>


<template>
  <div class="container">
    <h1>Todo List</h1>

    <TaskInput @addTask="onAddTask"></TaskInput>

    <div class="task-list">
      <div v-for="item of taskList" class="task-item">
        <TaskItem :item="item" @onDelete="onDelete(item)"></TaskItem>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 80%;
  margin: 0 auto;
  background-color: #f3f3f3;
}

h1 {
  text-align: center;
  margin-top: 20px;
}

.task-list {
  padding: 0 2em;
}

.task-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ccc;
}
</style>
