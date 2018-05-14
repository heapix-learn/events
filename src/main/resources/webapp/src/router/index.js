import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

import CreateUser from '../components/profile/CreateUser.vue';
import Users from '../components/profile/Users.vue';
import PendingUsers from '../components/profile/PendingUsers.vue';
import UserEdit from '../components/profile/UserEdit.vue';
import UserView from '../components/profile/UserView.vue';
import UserEditPassword from '../components/profile/UserEditPassword.vue';
import RegisteredForEventUsers from '../components/profile/RegisteredForEventUsers.vue';

import Events from '../components/feed/events/Events.vue';
import CreateEditEvent from '../components/feed/events/CreateEditEvent.vue';
import EventCreate from '../components/feed/events/EventCreate.vue';
import EventCreatePreview from '../components/feed/events/EventCreatePreview.vue';
import EventItem from '../components/feed/events/EventItem.vue';
import EventPreview from '../components/feed/events/EventPreview.vue';
import EventView from '../components/feed/events/EventView.vue';
import FormGenerator from '../components/feed/events/FormGenerator.vue';

import News from '../components/feed/news/News.vue';
import CreateNews from '../components/feed/news/CreateNews.vue';
import EditNews from '../components/feed/news/EditNews.vue';
import NewsPreview from '../components/feed/news/NewsPreview.vue';

import AuthVuew from '../components/auth/AuthView.vue';
import Login from '../components/auth/Login.vue';
import Signup from '../components/auth/Signup.vue';

import About from '../components/feed/about/About.vue';
import AboutEdit from '../components/feed/about/AboutEdit.vue';
import AboutPreview from '../components/feed/about/AboutPreview.vue';

const ROUTE_ROLES = {
  'sadm': 1,
  'adm': 2,
  'mod': 3,
  'mem': 4,
  'all': 5
}

Vue.use(Router)
const router = new Router({
  mode: 'history',
  routes: [
    {path: '/', redirect: '/news'},
    {path: '', redirect: '/news'},
    {
      path: '/events',
      component: Events,
      name: 'all-events'
    },
    {
      path: '/about/edit',
      component: AboutEdit,
      name: 'adm-about-edit',
    },
    {
      path: '/about/preview',
      component: AboutPreview,
      name: 'adm-about-preview',
    },
    {
      path: '/about',
      component: About,
      name: 'all-about',
    },
    {
      path: '/events/create',
      component: EventCreate,
      name: 'mod-events-create'
    },
    {
      path: '/events/create/preview',
      component: EventCreatePreview,
      name: 'mod-events-create-preview'
    },
    {
      path: '/events/edit/:id',
      component: EventCreate,
      name: 'mod-events-edit'
    },
    {
      path: '/events/:id',
      component: EventView,
      name: 'all-events-view'
    },
    {
      path: '/events/:id/users',
      component: RegisteredForEventUsers,
      name: 'mod-events-view'
    },
    {
      path: '/news',
      component: News,
      name: 'all-news',
    },
    {
      path: '/news/create',
      component: CreateNews,
      name: 'mod-news-create'
    },
    {
      path: '/news/edit/:id',
      component: EditNews,
      name: 'mod-news-edit',
    },
    {
      path: '/news/preview',
      component: NewsPreview,
      name: 'mod-news-preview'
    },
    {
      path: '/news/previewedit',
      component: NewsPreview,
      name: 'mod-news-edit-preview'
    },
    {
      path: '/users',
      component: Users,
      name: 'adm-users'
    },
    {
      path: '/pendingusers',
      component: PendingUsers,
      name: 'adm-users-pending'
    },
    {
      path: '/users/edit/changepassword',
      component: UserEditPassword,
      name: 'mem-users-change-password'
    },
    {
      path: '/users/edit/:id',
      component: UserEdit,
      name: 'mem-users-edit'
    },
    {
      path: '/users/:id',
      component: UserView,
      name: 'mem-users-view'
    },
    {
      path: '/createuser',
      component: CreateUser,
      name: 'adm-users-create'
    },
    {
      path: '/auth/login',
      component: Login,
      name: 'all-login'
    },
    {
      path: '/auth/signup',
      component: Signup,
      name: 'all-signup'
    },
    {
      path: '/formgenerator',
      component: FormGenerator,
      name: 'all-form-generator'
    }
  ]
})

router.beforeEach((to, from, next) => {
  const role = store.getters.loggedUserRole
  const toPathRoleGate = ROUTE_ROLES[to.name.split('-')[0]]
  console.log(`role${role} and toRole${toPathRoleGate}`)
  if (role <= toPathRoleGate) {
    next()
  } else {
    next('/')
  }
})

export default router