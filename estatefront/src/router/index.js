import Vue from 'vue'
import VueRouter from 'vue-router'
import ManageUser from '../views/ManageUser.vue'
import UpdateUser from  '../views/UpdateUser.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/manageuser'
  },
    {
    path: '/manageuser',
    name: 'ManageUser',
    component: ManageUser
  },
  {
    path: '/updateuser',
    name: 'UpdateUser',
    component: UpdateUser
  },
  {
    path: '/adduser',
    name: 'AddUser',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AddUser.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
