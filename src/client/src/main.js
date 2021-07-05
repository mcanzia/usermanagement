import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import vuetify from './plugins/vuetify'
import router from './router'

createApp(App).use(router).use(vuetify).mount('#app')