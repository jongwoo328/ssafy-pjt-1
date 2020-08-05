import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Review from '../views/Review/Review.vue'
import SignUp from '../views/accounts/SignUp.vue'
import UserInfo from '../views/accounts/UserInfo.vue'
import ServiceDetail from '@/views/services/ServiceDetail.vue'
import ProfileAdd from '@/views/accounts/ProfileAdd.vue'
import Profile from '@/views/accounts/Profile.vue'
import Service from '@/views/services/MyService.vue'
import Qna from '@/views/qna/Qna.vue'
import ServiceAdd from '@/views/services/ServiceAdd.vue'
import QnaDetail from '@/views/qna/QnaDetail.vue'
import QnaCreate from '@/views/qna/QnaCreate.vue'
import MessageList from '@/views/message/MessageList.vue'
import ServiceModify from '@/views/services/ServiceModify.vue'
import Follow from '@/views/accounts/Follow.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/myservice',
    name: 'Service',
    component : Service
  },
  {
    path: '/services/:service_id/review/create',
    name: 'Review',
    component: Review
  },
  {
    path: '/accounts/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/accounts/userinfo',
    name: 'UserInfo',
    component: UserInfo
  },
  {
    path: '/services/:service_id/modify',
    name: 'ServiceModify',
    component: ServiceModify
  },
  {
    path: '/services/:service_id',
    name: 'ServiceDetail',
    component: ServiceDetail
  },
  {
    path: '/accounts/:username/follow',
    name: 'Follow',
    component: Follow
  },
  {
    path: '/accounts/:username/:type',
    name: 'ProfileAdd',
    component: ProfileAdd
  },
  {
    path:'/accounts/:username',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/qna',
    name: 'Qna',
    component: Qna
  },
  {
    path: '/service/create',
    name: 'ServiceAdd',
    component : ServiceAdd
  },
  {
    path: '/qna/create',
    name: 'QnaCreate',
    component: QnaCreate
  },
  {
    path: '/qna/:q_no',
    name: 'QnaDetail',
    component: QnaDetail
  },
  {
    path: '/message',
    name: 'MessageList',
    component: MessageList
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
