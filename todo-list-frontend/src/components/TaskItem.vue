<script setup lang="ts">
import {ref} from 'vue';
import axios from 'axios';
import type {ITaskItem} from '@/types/ITaskItem';
import {ElMessage} from "element-plus";

defineProps(['item']);
const emit = defineEmits(['onDelete', 'fetchTasks']);

let itemText = ref("");

// 进入编辑模式
function onEditClick(item: ITaskItem) {
  item.isEdit = true;
  itemText.value = item.text;
}

// 删除任务，调用后端 API
function onDeleteClick(item: ITaskItem) {
  axios.delete(`http://localhost:8080/todo/item/${item.id}`)
      .then(() => {
        ElMessage.success('Task deleted successfully');
        emit('onDelete', item);  // 通知父组件删除任务成功
      })
      .catch(error => {
        ElMessage.error('Failed to delete task');
        console.error('Error deleting task:', error);
      });
}

// 确认编辑，调用后端 API 更新任务
function onEditOkClick(item: ITaskItem) {
  item.isEdit = false;
  item.text = itemText.value;

  axios.put('http://localhost:8080/todo/item', {
    ...item,
    text: itemText.value  // 更新后的文本内容
  })
      .catch(error => {
        ElMessage.error('Error updating task');
        console.error('Error updating task:', error);
      });
}

// 处理任务完成状态变更
const onIsDoneChange = (item: ITaskItem) => {
  axios.put('http://localhost:8080/todo/item/isdone', {
    ...item,  // 传递整个任务对象
    isDone: Boolean(item.isDone)
  })
      .then(() => {
        ElMessage.success('Task isDone status updated successfully');
        console.log('Task isDone status updated successfully');
      })
      .catch(error => {
        ElMessage.error('Error updating isDone status');
        console.error('Error updating isDone status:', error);
      });
};
</script>

<template>
  <template v-if="!item.isEdit">
    <el-checkbox v-model="item.isDone" size="large" @change="onIsDoneChange(item)">
      {{ item.text }}
    </el-checkbox>
    <div>
      <el-button type="primary" @click="onEditClick(item)">修改</el-button>
      <el-button type="danger" @click="onDeleteClick(item)">删除</el-button>
    </div>
  </template>
  <template v-else>
    <el-checkbox v-model="item.isDone" size="large">
      <el-input v-model="itemText"></el-input>
      <el-button @click="onEditOkClick(item)">确定</el-button>
      <el-button @click="item.isEdit=false">取消</el-button>
    </el-checkbox>
  </template>
</template>

<style scoped>

</style>
