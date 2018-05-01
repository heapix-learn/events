import router from '../../../router'

const users = [
    {
        id: 1,
        role: 'administrator',
        firstName: 'Stephen',
        lastName: 'Cock',
        firstPhone: '+375291965627',
        lastPhone: '',
        email: 'stephencock@gmail.com',
    },
    {
        id: 2,
        role: 'moderator',
        firstName: 'Josh',
        lastName: 'Grinder',
        firstPhone: '+375253402121',
        lastPhone: '375253402122',
        email: 'joshgrinder@gmail.com',
    },
    {
        id: 3,
        role: 'member',
        firstName: 'Helen',
        lastName: 'Kuraw',
        firstPhone: '+375291110404',
        lastPhone: '',
        email: 'helenkuraw@gmail.com',
    }
]

export default {
    state: {
        users,
        role: 'administrator'
    },
    getters: {
        role: state => state.role,
        users: state => state.users,
        userById: state => id => state.users.find(user => user.id === id)
    },
    mutations: {

    },
    actions: {

    }
}