import Vue from "vue";
import VueRouter from "vue-router";
// Vue框架使用VueRouter生成含路由对象 
Vue.use(VueRouter);
// 页面路由 
const router = new VueRouter({
  mode: 'hash',
  routes: [
  // 书籍列表页面
    {
      path: '/book',
      name: 'Book',
      // 动态导入书籍列表页面
      component: () => import('../view/BookList.vue')
    },
    // 访问url为 / 时，重定向到 /index
    {
      path: '/',
      redirect :'/index'
    },
    // 登录页面
    {
      path: '/index',
      name: 'Index',
      component: () => import('../view/IndexPage.vue')
    },
    // 用户设置页面
    {
      path: '/setting',
      name : 'Setting',
      component: () => import('../view/UserSetting.vue')
    },
    // 书籍详情页面
    {
      path: '/bookDetails',
      name : 'details',
      component: () => import('../view/BookDetail.vue')
    }
  ]
});
 
export default router;