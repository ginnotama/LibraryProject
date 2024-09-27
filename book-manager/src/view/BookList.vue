<template>
  <div class="book-list">
    <div class="book-list-header">
      <div class="header-left">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="book">BOOK SEARCH</el-menu-item>
          <el-menu-item index="brrow">BORROW</el-menu-item>
          <el-menu-item index="user">USER</el-menu-item>
        </el-menu>
      </div>
      <div class="header-right">
        <el-dropdown @command="handleCommand" placement="bottom">
          <span class="el-dropdown-link">
            USER <i class="el-icon-user-solid"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-back" command="quit">QUIT</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <div class="book-list-body">
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
    handleSelect(key, keyPath) {
      this.activeIndex = key;
      console.log(key, keyPath);
    },
    handleCommand(command) {
      if (command === 'quit') {
        this.$router.replace({ path: '/' });
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
  height: 58px;
  box-sizing: content-box;
  border-bottom: solid 2px transparent;
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