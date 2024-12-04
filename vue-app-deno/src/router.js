import Home from './pages/Home.vue'
import {createRouter, createWebHistory} from 'vue-router'


const routes = [
    { path: '/', component: Home },
    {
        path: '/about',
        component: () => import('./pages/About.vue')
    },
    {
        path: '/users/:id(\\d+)',
        component: () => import('./pages/users/Show.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        component: () => import('./pages/errors/NotFound.vue')
    },
]

export default createRouter({
    // If you don't want that the url change, you can use createMemoryHistory
    history: createWebHistory(),
    routes,
})