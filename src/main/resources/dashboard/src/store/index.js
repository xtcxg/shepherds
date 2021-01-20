import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    url: {
      addWorker: "",
      workers: "",
      jobs: "",
    },
  },
  mutations: {
    init(state,val){
      state.url =  {
        addWorker: val + "worker/add/",
        workers: val + "workers",
        jobs: val + "jobs/",
      }
    },
  },
  actions: {
  },
  modules: {
  }
})
