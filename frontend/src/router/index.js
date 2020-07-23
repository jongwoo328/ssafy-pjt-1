import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Review from '../views/Review/Review.vue'
import SignUp from '../views/accounts/SignUp.vue'
import ServiceDetail from '@/views/services/ServiceDetail.vue'
import ProfileAdd from '@/views/profile/ProfileAdd.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
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
    path: '/services/:service_id',
    name: 'ServiceDetail',
    component: ServiceDetail
  },
  {
    path: '/profile/add',
    name: 'ProfileAdd',
    component: ProfileAdd
  },
  

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
