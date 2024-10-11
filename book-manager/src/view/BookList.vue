<template>
<!-- 书籍列表页面 -->
  <div class="book-list">
    <div class="book-list-header">
      <div class="header-left">
        <!-- 定义了三个头部导航栏  BOOK SEARCH BORROW和USER-->
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="book">BOOK SEARCH</el-menu-item>
          <el-menu-item index="brrow">BORROW</el-menu-item>
          <el-menu-item index="user">USER</el-menu-item>
        </el-menu>
      </div>
      <div class="header-right">
        <!-- 显示用户的头像 -->
        <el-dropdown @command="handleCommand" placement="bottom">
          <span class="el-dropdown-link">
            USER <i class="el-icon-user-solid"></i>
          </span>
          <!-- 鼠标悬浮到用户头像上时，展示的两个下拉选项 SETTING和QUIT -->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-setting" command="setting">SETTING</el-dropdown-item>
            <el-dropdown-item icon="el-icon-back" command="quit">QUIT</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <div class="book-list-body">
      <!-- 页面中间部分 根据头部导航栏选中的activeIndex的值来决定显示哪个页面 -->
      <div v-show="activeIndex==='book'" class="w-h-100">
        <BookTable/>
      </div>
      <div v-show="activeIndex==='user'">
        USER
      </div>
      <div v-show="activeIndex==='brrow'">
        BORROW
      </div>
    </div>

    <!-- 页面底部区域 -->
    <div class="book-list-footer">
      Book Manager System By AAA
    </div>
  </div>
</template>

<script>

import BookTable from '../components/BookTable';

export default {
  data() {
    return {
      activeIndex: 'book'
    }
  },

  components: {
    BookTable
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
      
      // 选中quit 则跳转到登录页面
      if (command === 'quit') {
        this.$router.replace({ path: '/' });
      }
      // 选中setting 跳转到设置页面
      if (command === 'setting') {
        this.$router.replace({ path: '/setting' });
      }
      // this.$message('click on item ' + command);
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
 }
 .book-list-footer {
  height: 60px;
 }

 .w-h-100 {
  width: 100%;
  height: 100%;
 }
</style>