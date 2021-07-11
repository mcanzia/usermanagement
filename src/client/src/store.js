import {createStore} from 'vuex'
import AuthService from "@/services/AuthService";

// Retrieve user from localStorage
let authUser = JSON.parse(localStorage.getItem('authUser'));
let status = authUser ? true : false;

export default createStore({
    state:{
        // Used for communication between GroupList and UnassignedUserList components
        selectedUnassignedUser: {},
        errors: [],

        // Used for authentication purposes
        authCurrentUser: authUser,
        authLoggedIn: status,
    },
    getters:{},
    mutations: {
        // Used for communication between GroupList and UnassignedUserList components
        UPDATE_SELECTED_UNASSIGNED_USER(state, user){
            state.selectedUnassignedUser = user
        },
        INSERT_ERROR(state, error) {
            state.errors.push(error)
        },

        // Used for authentication purposes
        LOGIN_SUCCESS(state, user) {
            //state.authState.status.loggedIn = true;
            //state.authState.authUser = user;
            state.authLoggedIn = true;
            state.authCurrentUser = user;
        },
        LOGIN_FAILURE(state) {
            //state.authState.status.loggedIn = false;
            //state.authState.authUser = null;
            state.authLoggedIn = false;
            state.authCurrentUser = null;
        },
        LOGOUT(state) {
            //state.authState.status.loggedIn = false;
            //state.authState.authUser = null;
            state.authLoggedIn = false;
            state.authCurrentUser = null;
        },
        REGISTER_SUCCESS(state) {
            //state.authState.status.loggedIn = false;
            state.authLoggedIn = false;
        },
        REGISTER_FAILURE(state) {
            //state.authState.status.loggedIn = false;
            state.authLoggedIn = false;

        }
    },
    actions:{
        // Used for communication between GroupList and UnassignedUserList components
        updateUserSelection({commit}, payload) {
            try{
                commit('UPDATE_SELECTED_UNASSIGNED_USER', payload)
            }catch(error){
                commit('INSERT_ERROR', error)
            }
        },

        // Used for authentication purposes
        register({ commit }, payload) {
            return AuthService.register(payload).then(
                response => {
                    commit('REGISTER_SUCCESS');
                    return Promise.resolve(response.data);
                },
                error => {
                    commit('REGISTER_FAILURE');
                    return Promise.reject(error);
                }
            );
        },

        login({ commit }, payload) {
            return AuthService.login(payload).then(
                user => {
                    commit('LOGIN_SUCCESS', user);
                    console.log(this.state.authLoggedIn);
                    return Promise.resolve(user);
                },
                error => {
                    commit('LOGIN_FAILURE');
                    return Promise.reject(error);
                }
            );
        },

        logout({ commit }) {
            AuthService.logout();
            commit('LOGOUT');
        },
    }
})