import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import VueResource from 'vue-resource'
import store from './store'

Vue.config.productionTip = false
Vue.use(VueResource)

new Vue({
  store,
  render: h => h(App)
}).$mount('#app')
