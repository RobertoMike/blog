<template>
  <div>
    <h1>home</h1>
    <RouterLink to="/about">About</RouterLink>
    <!-- moved from App.vue -->
    <div class="card-flex">
      <Article v-for="article in articles" :key="article.id" :image="article.image" :title="article.title"/>
    </div>
  </div>
</template>
<script setup>
import Article from '../components/Article.vue'

const articles = [
  {
    id: 1,
    image: 'https://picsum.photos/310/250',
    title: 'Crud with Spring boot'
  }, {
    id: 2,
    image: 'https://picsum.photos/320/250',
    title: 'Frontend Frameworks and Vue'
  }
]
</script>