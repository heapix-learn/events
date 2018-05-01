import Vue from 'vue'
import Router from 'vue-router'

import CreateUser from '../components/profile/CreateUser.vue';
import Users from '../components/profile/Users.vue';
import User from '../components/profile/User.vue';

import Events from '../components/feed/events/Events.vue';
import CreateEditEvent from '../components/feed/events/CreateEditEvent.vue';
import EventItem from '../components/feed/events/EventItem.vue';
import EventPreview from '../components/feed/events/EventPreview.vue';

import News from '../components/feed/news/News.vue';
import CreateNews from '../components/feed/news/CreateNews.vue';
import NewsPreview from '../components/feed/news/NewsPreview.vue';

import AuthVuew from '../components/auth/AuthView.vue';
import Login from '../components/auth/Login.vue';
import Signup from '../components/auth/Signup.vue';

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {path: '/', redirect: '/events'},
    {
      path: '/events',
      component: Events,
    },
    {
      path: '/events/create',
      component: CreateEditEvent
    },
    {
      path: '/events/preview',
      component: EventPreview
    },
    {
      path: '/events/:id',
      component: EventItem,
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
      component: CreateNews
    },
    {
      path: '/news/preview',
      component: NewsPreview
    },
    {
      path: '/users',
      component: Users,
    },
    {
      path: '/users/:id',
      component: User
    },
    {
      path: '/users/:id?edit',
      component: User
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
    {
      path: '/kek',
      component: EventItem
    }

  ]
})
