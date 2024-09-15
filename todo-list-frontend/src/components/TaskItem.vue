<script setup lang="ts">
import {ref} from 'vue'
import type {ITaskItem} from '@/types/ITaskItem'

const props = defineProps(['item'])
const emit = defineEmits(['onDelete'])

let itemText = ref("");

function onEditClick(item: ITaskItem) {
  item.isEdit = true;
  itemText.value = item.text;
}

function onDeleteClick(item: ITaskItem) {
  emit('onDelete', item);
}

function onEditOkClick(item: ITaskItem) {
  item.isEdit = false;
  item.text = itemText.value;
}
</script>

<template>
  <template v-if="!item.isEdit">
    <el-checkbox v-model="item.isDone" size="large">
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
