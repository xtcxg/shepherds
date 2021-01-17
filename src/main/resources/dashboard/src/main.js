import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import VueResource from 'vue-resource'

Vue.config.productionTip = false
Vue.use(VueResource)

new Vue({
  render: h => h(App),
}).$mount('#app')
