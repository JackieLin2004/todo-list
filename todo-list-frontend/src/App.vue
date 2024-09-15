<script setup lang="ts">
import {ref, watch} from 'vue'
import TaskInput from '@/components/TaskInput.vue'
import TaskItem from '@/components/TaskItem.vue'
import type {ITaskItem} from '@/types/ITaskItem'

let taskList = ref<ITaskItem[]>([]);

taskList.value = JSON.parse(
    window.localStorage.getItem('taskList') ?? "[]"
);  // 反串行化

watch(taskList, onSave, {deep: true});

function onAddTask(taskText: string) {
  taskList.value.push({
    id: taskList.value.length + 1,
    text: taskText,
    isDone: false,
    isEdit: false
  });
}

function onSave() {
  window.localStorage
      .setItem('taskList', JSON.stringify(taskList.value));
}

function onDelete(item: ITaskItem) {
  taskList.value = taskList.value.filter(it => it !== item);
}

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
