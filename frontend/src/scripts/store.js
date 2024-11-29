import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
  state () {
    return {
        member: {
            id: 0
        }
    }
  },
  mutations: {
    setMember(state, payload){
        state.member.id = payload;
    }
  }
})

export default store;
