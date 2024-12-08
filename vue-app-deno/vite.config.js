import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import VueRouter from 'unplugin-vue-router/vite'

// https://vite.dev/config/
export default defineConfig({
    plugins: [
        // Is super important that you follow this order
        // First the vue router
        VueRouter(),
        vue()
    ],
})
