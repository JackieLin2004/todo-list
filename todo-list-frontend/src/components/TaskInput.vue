<script setup lang="ts">
import {ref} from 'vue'

let emit = defineEmits(['addTask'])

let todoText = ref('');

function onAddClick() {
  emit("addTask", todoText.value);
  todoText.value = '';
}
</script>

<template>

  <div class="input-row">
    <el-input style="width: 20em" placeholder="请输入一个待办事项..." v-model="todoText"/>
    <el-button type="success" @click="onAddClick">添加</el-button>
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
