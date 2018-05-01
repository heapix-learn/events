import router from '../../../router'
const loggedUser =     {
    id: 1,
    role: 'administrator',
    firstName: 'Stephen',
    lastName: 'Cock',
    firstPhone: '+375291965627',
    lastPhone: '',
    email: 'stephencock@gmail.com',
}
export default {
    state: {
        isLogged: false,
        loggedUser
    },
    getters: {
        isLogged: state => state.isLogged,
        loggedUserId: state => state.loggedUser.id,
        loggedUserRole: state => state.loggedUser.role
    },
    mutations: {
        setIsLogged(state, isLogged) {
            state.isLogged = isLogged
        }
    },
    actions: {
        signIn({commit}, payload) {
            commit('setIsLogged', true)
            router.push({path: '/'})
        },
        signOut({commit}, payload) {
            commit('setIsLogged', false)
            router.replace({path: '/'})
        }
    }
}