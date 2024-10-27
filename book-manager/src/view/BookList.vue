<template>
<!-- 书籍列表页面 -->
  <div class="book-list">
    <div class="book-list-header">
      <div class="header-left">
        <!-- 定义了三个头部导航栏  BOOK SEARCH BORROW和USER-->
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="book">Book List</el-menu-item>
          <el-menu-item v-show="isLogin" index="returnBook">My Borrowing Information</el-menu-item>
          <el-menu-item v-show="isLogin && userType === 1" index="user">MANAGER</el-menu-item>
        </el-menu>
      </div>
      <div class="header-right">
        <!-- 显示用户的头像 -->
        <el-dropdown @command="handleCommand" placement="bottom">
          <span class="el-dropdown-link">
            {{ nickName }} <i class="el-icon-user-solid"></i>
          </span>
          <!-- 鼠标悬浮到用户头像上时，展示的两个下拉选项 SETTING和QUIT -->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-show="!isLogin" icon="el-icon-s-home" command="login">LOGIN</el-dropdown-item>
            <el-dropdown-item icon="el-icon-setting" command="setting" :disabled="!isLogin">SETTING</el-dropdown-item>
            <el-dropdown-item v-show="isLogin" icon="el-icon-back" command="quit">QUIT</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <div class="book-list-body">
      <!-- 页面中间部分 根据头部导航栏选中的activeIndex的值来决定显示哪个页面 -->
      <div v-if="activeIndex==='book'" class="w-h-100">
        <BookTable :isLogin="isLogin"/>
      </div>
      <div v-if="activeIndex==='user'">
        USER
      </div>
      <div v-if="activeIndex==='returnBook'" style="height:100%">
        <BookReturnVue :userInfo="userInfo"/>
      </div>
    </div>

    <!-- 页面底部区域 -->
    <div class="book-list-footer" :class="{'red-text' :userType === undefined ,'manager-text' :userType === 1 , 'student-text': userType === 0}">
      {{ userTypeMsg }}
    </div>
    <LoginDialog ref="loginDialog" @loginSucess="loginSucess"/>
  </div>
</template>

<script>
// import BookReturnVue from '../components/BookReturn.vue';

// import BookTable from '../components/BookTable';
import LoginDialog from "../components/LoginDialog.vue";
export default {
  data() {
    return {
      activeIndex: 'book',
      nickName: 'USER',
      isLogin: true,
      userType: undefined,
      userTypeMsg: 'Please login',
      userInfo: {}
    }
  },

  components: {
    BookTable: () => import('../components/BookTable'),
    LoginDialog,
    BookReturnVue: () => import('../components/BookReturn.vue'),
  },

  created() {

  },

  watch: {
    userType(value) {
      if (value === 0) {
        this.userTypeMsg = 'STUDENT';
      } else if (value === 1) {
        this.userTypeMsg = 'MANAGER';
      } else {
        this.userTypeMsg = 'Please login';
      }
    }
  },

  methods: {
    // 头部导航栏选中状态变化时触发的方法 更改activeIndex的值 动态渲染页面中间的区域
    handleSelect(key, keyPath) {
      this.activeIndex = key;
      console.log(key, keyPath);
    },
    // 鼠标悬浮到用户头像上时，下拉框中按钮点击后触发的方法
    // 跳转到设置页面
    handleCommand(command) {

      if (command === 'login') {
        this.$refs.loginDialog.changeVisibel(true);
        return;
      }
      
      // 选中quit 则跳转到登录页面
      if (command === 'quit') {
        this.isLogin = false;
        this.userType = undefined;
        this.nickName = 'USER';
         this.userInfo = {};
        this.$message({
          message: 'Logout success',
          type: 'success'
        });
      }
      // 选中setting 跳转到设置页面
      if (command === 'setting') {
        // this.$router.replace({ path: '/setting' });
      }
      // this.$message('click on item ' + command);
    },

    loginSucess(data) {
      console.log(data);
      this.nickName = data.userName;
      this.userType = data.userType;
      this.userInfo = data;
      this.isLogin = true;
      this.$refs.loginDialog.changeVisibel(false);
      this.$message({
        message: 'Login success',
        type: 'success'
      });      
    }
  }
}
</script>

<style scoped>
 .book-list {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
 }

 .book-list-header {
  height: 60px;
  background-color: #ccc;
  display: flex;
  flex-direction: row;
  border-bottom: solid 1px #e6e6e6;
 }

.header-left {
  width: auto;
}

.header-right {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  background-color: white;
  height: 60px;
  box-sizing: content-box;
  /* border-bottom: solid 2px transparent; */
  padding-right: 32px;
}

 .book-list-body {
  width: 100%;
  height: calc(100% - 120px);
    /* box-shadow: 2px 4px 0 rgba(0,0,0,.05); */
 }
 .book-list-footer {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
 }

 .w-h-100 {
  width: 100%;
  height: 100%;
 }

 .red-text {
  color: red;
  font-weight: bold;
  font-size: xx-large;
 }
 .manager-text {
  color: orange;
 }
 .student-text {
  color: green;
 }
</style>