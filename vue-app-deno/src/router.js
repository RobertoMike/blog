import {createRouter, createWebHistory} from 'vue-router'
import {handleHotUpdate, routes} from 'vue-router/auto-routes'

const router = createRouter({
    // If you don't want that the url change, you can use createMemoryHistory
    history: createWebHistory(),
    routes,
})

// This will update routes at runtime without reloading the page
if (import.meta.hot) {
    handleHotUpdate(router)
}

// We export this to be used in main.js
export default router