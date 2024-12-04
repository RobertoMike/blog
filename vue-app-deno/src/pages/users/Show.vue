<template>
  <h1>show</h1>
  <p>ID: {{ id }}</p>
</template>
<script setup>
import { useRoute } from 'vue-router'

const route = useRoute()
const { id } = route.params

</script>