<template>
  <div class="book-inner">
    <div class="b-r-1 w-120">
      <el-menu
        default-active="3"
        class="el-menu-vertical-demo"
        @select="handleSelect"
        >
        <el-menu-item index="3">
          <i class="el-icon-notebook-1"></i>
          <span slot="title">PAPER</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-notebook-2"></i>
          <span slot="title">BOOK</span>
        </el-menu-item>
      </el-menu>
    </div>
    <div class="f-1 padding-8-12">
      <div class="table-header">
        <el-input style="width: 200px; padding: 8px 0;" v-model="nameKeyWord" placeholder="SEARCH BY NAME"></el-input>
      </div>

      <el-table
        :data="tableData"
        height="100%"
        style="width: 100%;">
         <template slot="empty">
            <p>NOT FOUND DATA</p>
          </template>
        <el-table-column
          prop="name"
          label="Name"
          width="180">
        </el-table-column>
        <el-table-column
          prop="author"
          label="Author"
          width="80">
        </el-table-column>
        <el-table-column
          prop="spec"
          label="CLASSIFY"
          width="180">
        </el-table-column>
        <el-table-column
          prop="info"
          label="INFO">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { mock1, mock2 } from '../mock/mockData';
export default {
  data() {
    return {
      activeIndex: '3',
      tableData: [],
      nameKeyWord: ''
    }
  },

  created() {
    this.activeIndex = '3';
    this.tableData = mock1;
  },

  watch: {
    nameKeyWord(newVal) {
      const data = this.activeIndex == 3 ? mock1 : mock2;
      const value = newVal.toUpperCase();
      if (value) {
        this.tableData = data.filter(item => item.name.toUpperCase().indexOf(value) > -1);
      } else {
        this.tableData = data;
      }
    }
  },

  methods: {
    // handleSelect(key, keyPath) {
    handleSelect(key) {
      this.activeIndex = key;
      if (key == 3) {
        this.tableData = mock1;
        this.nameKeyWord = '';
      } else {
        this.tableData = mock2;
        this.nameKeyWord = '';
      }
    }
  }
}
</script>

<style scoped>
.book-inner {
  width: 100%;
  height: 100%;
  background-color: #fff;
  overflow: hidden;
  display: flex;
  flex-direction: row;
  box-sizing:border-box;
  padding-top: 8px;
}
.b-r-1 {
  border-right:1px solid #e6e6e6;
}
.w-120 {
  width: 120px;
}
.el-menu {
  border-right: none;
}
.f-1 {
  width: calc(100% - 120px);
}
.padding-8-12 {
  padding: 8px 12px;
}
.table-header {
  display: flex;
  flex-direction: row-reverse;
  padding: 0 12px;
  border-bottom: solid 1px #e6e6e6;
}
</style>
