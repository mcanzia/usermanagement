import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import vuetify from './plugins/vuetify'
import router from './router'
import store from '@/store'
import mitt from 'mitt';
const emitter = mitt();

const app = createApp(App).use(router).use(vuetify).use(store)
app.config.globalProperties.emitter = emitter;
app.mount('#app')