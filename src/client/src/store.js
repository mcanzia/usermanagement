import {createStore} from 'vuex'

export default createStore({
    state:{
        selectedUnassignedUser: {},
        errors: []
    },
    getters:{},
    mutations: {
        UPDATE_SELECTED_UNASSIGNED_USER(state, user){
            state.selectedUnassignedUser = user
        },
        INSERT_ERROR(state, error) {
            state.errors.push(error)
        }
    },
    actions:{
        async updateUserSelection({commit}, payload) {
            try{
                commit('UPDATE_SELECTED_UNASSIGNED_USER', payload)
            }catch(error){
                commit('INSERT_ERROR', error)
            }
        }
    }
})