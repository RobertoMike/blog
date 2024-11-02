<template>
  <article class="card">
    <img :src="image" :alt="title">
    <h2>{{ title }}</h2>

    <div>
      <button type="button" @click="count++">Count is {{ count }}</button>
    </div>
  </article>
</template>
<script setup>
import { ref } from 'vue'

defineProps({
  image: String,
  title: String,
})

const count = ref(0)
</script>