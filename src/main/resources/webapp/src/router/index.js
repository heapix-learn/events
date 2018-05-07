import Vue from 'vue'
import Router from 'vue-router'

import CreateUser from '../components/profile/CreateUser.vue';
import Users from '../components/profile/Users.vue';
import PendingUsers from '../components/profile/PendingUsers.vue';
import UserEdit from '../components/profile/UserEdit.vue';
import UserView from '../components/profile/UserView.vue';
import UserEditPassword from '../components/profile/UserEditPassword.vue';

import Events from '../components/feed/events/Events.vue';
import CreateEditEvent from '../components/feed/events/CreateEditEvent.vue';
import EventItem from '../components/feed/events/EventItem.vue';
import EventPreview from '../components/feed/events/EventPreview.vue';
import EventView from '../components/feed/events/EventView.vue';

import News from '../components/feed/news/News.vue';
import CreateNews from '../components/feed/news/CreateNews.vue';
import EditNews from '../components/feed/news/EditNews.vue';
import NewsPreview from '../components/feed/news/NewsPreview.vue';

import AuthVuew from '../components/auth/AuthView.vue';
import Login from '../components/auth/Login.vue';
import Signup from '../components/auth/Signup.vue';

import About from '../components/feed/about/About.vue';

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {path: '/', redirect: '/events'},
    {path: '', redirect: '/events'},
    {
      path: '/events',
      component: Events,
    },
    {
      path: '/about',
      component: About,
    },
    {
      path: '/events/create',
      component: CreateEditEvent
    },
    {
      path: '/events/edit/:id',
      component: CreateEditEvent
    },
    {
      path: '/events/preview',
      component: EventPreview
    },
    {
      path: '/events/:id',
      component: EventView,
    },
    {
      path: '/news',
      component: News,
    },
    {
      path: '/news/create',
      component: CreateNews
    },
    {
      path: '/news/edit/:id',
      component: EditNews
    },
    {
      path: '/news/preview',
      component: NewsPreview
    },
    {
      path: '/news/previewedit',
      component: NewsPreview
    },
    {
      path: '/users',
      component: Users,
    },
    {
      path: '/pendingusers',
      component: PendingUsers,
    },
    {
      path: '/users/edit/changepassword',
      component: UserEditPassword,
    },
    {
      path: '/users/edit/:id',
      component: UserEdit,
    },
    {
      path: '/users/:id',
      component: UserView,
    },
    {
      path: '/createuser',
      component: CreateUser,
    },
    {
      path: '/auth/login',
      component: Login
    },
    {
      path: '/auth/signup',
      component: Signup
    },
  ]
})
