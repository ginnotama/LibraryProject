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
        <el-button @click="drawer = true" type="primary" round style="margin-left: 16px; height: 40px;">
            ADVANCED SEARCH
        </el-button>
        <el-input style="width: 200px; padding: 8px 0;" v-model="nameKeyWord" placeholder="SEARCH BY NAME"></el-input>
      </div>

      <el-table
        :data="tableData"
        height="100%"
        style="width: 100%;"
        @cell-dblclick="tableCellDbClick">
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
          <template slot="header">
            <p><strong>INFO</strong> <span style="font-size:12px; color:#E6A23C">Double click cell to view book details.</span></p>
          </template>
        </el-table-column>
      </el-table>
      <el-drawer
        title="ADVANCED SEARCH"
        ref="advancedSearch"
        :visible.sync="drawer"
        direction="rtl"
        :close-on-press-escape="false"
        :wrapperClosable="false"
        @open="drawOpen"
        >
        <div class="drawer-body">
          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByName"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY NAME">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByName" v-model="drawerKeyWord.nameKeyWord" placeholder="SEARCH BY NAME"></el-input>
          </div>

          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByAuthor"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY AUTHOR">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByAuthor" v-model="drawerKeyWord.authorKeyWord" placeholder="SEARCH BY AUTHOR"></el-input>
          </div>

          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByClassify"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY CLASSIFY">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByClassify" v-model="drawerKeyWord.classifyKeyWord" placeholder="SEARCH BY ClASSIFY"></el-input>
          </div>

          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByInfo"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY INFO">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByInfo" v-model="drawerKeyWord.infoKeyWord" placeholder="SEARCH BY INFO"></el-input>
          </div>

          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByHobby"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY HOBBY">
            </el-switch>
            <div class="padding-t-12" v-show="isSearchByHobby">
              <p style="margin: 0; color: #C0C4CC;">The preferences in the user information have been read. Please continue to select if you want to add them.</p>
              <el-checkbox-group class="check-box-group" v-model="drawerKeyWord.selectedHobbys">
                <el-checkbox v-for="hobby in hobbys" :label="hobby" :disabled="userHobbys.includes(hobby)" :key="hobby">{{hobby}}</el-checkbox>
              </el-checkbox-group>
            </div>
          </div>

          <div>
            <el-button type="primary" round @click="hightLevelSearch">SUMBIT AND SEAECH</el-button>
          </div>
        </div>
      </el-drawer>
    </div>
  </div>
</template>

<script>
import { mock1, mock2 } from '../mock/mockData';
import { hobby } from '../mock/hobbyMock';
export default {
  data() {
    return {
      activeIndex: '3',
      tableData: [],
      nameKeyWord: '',
      drawer: false,
      drawerKeyWord: {
        nameKeyWord: '',
        authorKeyWord: '',
        classifyKeyWord: '',
        infoKeyWord: '',
        selectedHobbys: []
      },
      isSearchByName: true,
      isSearchByAuthor: true,
      isSearchByClassify: true,
      isSearchByInfo: true,
      isSearchByHobby: true,
      hobbys: [],
      userHobbys: ['Philosophy', 'Art', 'Safety science', 'Natural sciences', 'History'],

    }
  },

  created() {
    this.activeIndex = '3';
    this.tableData = mock1;
    this.hobbys = hobby;
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
    },
    drawOpen() {
      this.drawerKeyWord.selectedHobbys = structuredClone(this.userHobbys);
    },
    hightLevelSearch() {
      const searchInfo = this.getAduancedSearchInfo();
      console.log('search info 🧐:', searchInfo);
      this.$refs.advancedSearch.closeDrawer();
      this.drawerKeyWord = {
        nameKeyWord: '',
        authorKeyWord: '',
        classifyKeyWord: '',
        infoKeyWord: '',
        selectedHobbys: []
      };
    },

    getAduancedSearchInfo() {
      const searchInfo = {};
      if (this.isSearchByName) {
        searchInfo.nameKeyWord = this.drawerKeyWord.nameKeyWord;
      }
      if (this.isSearchByAuthor) {
        searchInfo.authorKeyWord = this.drawerKeyWord.authorKeyWord;
      }
      if (this.isSearchByClassify) {
        searchInfo.classifyKeyWord = this.drawerKeyWord.classifyKeyWord;
      }
      if (this.isSearchByInfo) {
        searchInfo.infoKeyWord = this.drawerKeyWord.infoKeyWord;
      }
      if (this.isSearchByHobby) {
        searchInfo.selectedHobbys = this.drawerKeyWord.selectedHobbys;
      }
      return searchInfo;
    },
    tableCellDbClick(row, column) {
      if (column.property === 'info') {
        this.$router.replace({ name: 'details', params: { row: structuredClone(row)} });        
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
  align-items: center;
}
.drawer-body {
  display: flex;
  flex-direction: column;
  padding: 0 12px 24px 12px;
}
.drawer-body-item {
  display: flex;
  flex-direction: column;
}
.padding-b-24 {
  padding-bottom: 24px;
}
.padding-t-12 {
  padding-top: 12px;
}
.check-box-group {
  display: flex;
  flex-direction: column;
  justify-content: flex-start !important;
  align-items: flex-start !important;
}
</style>
