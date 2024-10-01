import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
 
const router = new VueRouter({
  mode: 'hash',
  routes: [
    {
      path: '/book',
      name: 'Book',
      component: () => import('../view/BookList.vue')
    },
    {
      path: '/',
      redirect :'/index'
    },
    {
      path: '/index',
      name: 'Index',
      component: () => import('../view/IndexPage.vue')
    },
    {
      path: '/setting',
      name : 'Setting',
      component: () => import('../view/UserSetting.vue')
    }
  ]
});
 
export default router;