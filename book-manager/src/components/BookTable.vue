<template>
<!-- 书籍列表页面 -->
  <div class="book-inner">
    <!-- 书籍列表页面左侧的两个子页签 PAPER 和 BOOK -->
    <!-- <div class="b-r-1 w-120">
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
    </div> -->
    <div class="f-1 padding-8-12" style="height: 100%;">
      <div class="table-header">
         <!-- 高级搜索按钮 -->
        <!-- <el-button @click="drawer = true" type="primary" round style="margin-left: 16px; height: 40px;">
            ADVANCED SEARCH
        </el-button> -->
        <!-- 按名称搜索的搜索框 -->
        <el-input style="width: 200px; padding: 8px 0;" v-model="nameKeyWord" placeholder="SEARCH BY NAME" @change="getTableData"></el-input>
      </div>
<!-- 展示书籍信息的表格 -->
      <el-table
        :data="tableData"
        height="calc(100% - 105px)"
        style="width: 100%;"
        @cell-dblclick="tableCellDbClick">
        <el-table-column
          prop="bookName"
          label="Name"
          width="180">
        </el-table-column>
        <el-table-column
          prop="bookAuthor"
          label="Author"
          width="80">
        </el-table-column>
        <el-table-column
          prop="bookLocation"
          label="Location"
          width="180">
        </el-table-column>
        <el-table-column
          label="Status"
          width="180">
          <template slot-scope="scope">
            <span v-if="scope.row.bookStatus === 0">BOOROW</span>
            <i v-if="scope.row.bookStatus === 1" class="el-icon-success" style="color: #67C23A;"></i>
            <span v-if="scope.row.bookStatus === 1" style="margin-left: 10px">LENDING</span>
            <i v-if="scope.row.bookStatus === 2" class="el-icon-error" style="color: #F56C6C;"></i>
            <span v-if="scope.row.bookStatus === 2" style="margin-left: 10px">LOST</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="bookDesc"
          label="INFO">
          <!-- <template slot="header">
            <p><strong>INFO</strong> <span style="font-size:12px; color:#E6A23C">Double click cell to view book details.</span></p>
          </template> -->
        </el-table-column>
        <el-table-column
          v-if="isLogin"
          label="COMPONENT"
          width="180"
          fixed="right"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="searchComponent(scope.row)" >View comments</el-button>
          </template>
        </el-table-column>
        <el-table-column
          label="OPERATION"
          width="180"
          fixed="right"
        >
          <template slot-scope="scope">
            <el-button type="text" :disabled="!isLogin || !(scope.row.bookStatus === 0)" @click="bookBorrow(scope.row)">BOOROWING</el-button>
          </template>
        </el-table-column>
        <el-table-column
          v-if="userInfo.userType === 1"
          label="BORROW INFO"
          width="125"
          fixed="right"
        >
          <template slot-scope="scope">
            <el-popover
              placement="top"
              width="400"
              trigger="click">
                <el-table :data="gridData" height="300px" v-loading="loading">
                  <el-table-column width="120" property="userName" label="USER NAME"></el-table-column>
                  <el-table-column width="100" label="STATUS" align="center">
                    <template slot-scope="scope">
                      <span style="font-size: 20px;">
                        <i v-if="scope.row.borrowState === 0" class="el-icon-folder-checked" style="color: #67C23A"></i>
                        <i v-if="scope.row.borrowState === 1" class="el-icon-folder-opened" style="color: #E6A23C"></i>
                      </span>
                    </template>
                  </el-table-column>

                  <el-table-column width="150" property="returnDate" label="RETURN TIME"></el-table-column>
                </el-table>
              <!-- <el-descriptions title="BOOROWING INFO" >
                <el-descriptions-item label="用户名">kooriookami</el-descriptions-item>
                <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
                <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
                <el-descriptions-item label="备注">
                  <el-tag size="small">学校</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
              </el-descriptions> -->
              <el-button slot="reference" type="info" round :disabled="scope.row.bookStatus === 0" @click="check(scope.row)">CHECK</el-button>
            </el-popover>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
        <el-pagination
          style="float: right; padding: 8px 20px"
          :current-page.sync="currentPage"
          :page-sizes="[10, 20, 50, 100]"
          :page-size.sync="pageSize"
          layout="total, prev, pager, next, sizes"
          :total="total"
          @size-change="getTableData"
          @current-change="getTableData"
          @prev-click="getTableData"
          @next-click="getTableData"
          >
        </el-pagination>


      <!-- 高级搜索按钮点击后,弹出的抽屉 -->
      <el-drawer
        title="ADVANCED SEARCH"
        ref="advancedSearch"
        :visible.sync="drawer"
        direction="rtl"
        :close-on-press-escape="false"
        :wrapperClosable="false"
        @open="drawOpen"
        >
        <!-- 抽屉中展示的搜索项 -->
        <div class="drawer-body">
          <!-- 是否按名称搜索 -->
          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByName"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY NAME">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByName" v-model="drawerKeyWord.nameKeyWord" placeholder="SEARCH BY NAME"></el-input>
          </div>
    <!-- 是否按作者搜索 -->
          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByAuthor"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY AUTHOR">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByAuthor" v-model="drawerKeyWord.authorKeyWord" placeholder="SEARCH BY AUTHOR"></el-input>
          </div>
<!-- 是否按类别搜索 -->
          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByClassify"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY CLASSIFY">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByClassify" v-model="drawerKeyWord.classifyKeyWord" placeholder="SEARCH BY ClASSIFY"></el-input>
          </div>
<!-- 是否按书籍信息搜索 -->
          <div class="drawer-body-item padding-b-24">
            <el-switch
              v-model="isSearchByInfo"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="SEARCH BY INFO">
            </el-switch>
            <el-input class="padding-t-12" v-show="isSearchByInfo" v-model="drawerKeyWord.infoKeyWord" placeholder="SEARCH BY INFO"></el-input>
          </div>
<!-- 是否按兴趣爱好搜索 -->
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
    <BookComponentVue ref="bookComponent" :currentBookInfo=currentBookInfo />
  </div>
</template>

<script>
// import { mock1, mock2 } from '../mock/mockData';
import { hobby } from '../mock/hobbyMock';
import { getBooks } from "../api/Book";
import { Loading } from 'element-ui';
import { BOOK_STATUS } from "../const/Const";
import BookComponentVue from './BookComponent.vue';
import { addBorrow, getBorrows } from '../api/Borrow';
import { formatDate } from "../Utils/util";

export default {
  components: {
    BookComponentVue
  },

  props: {
    isLogin: {
      type: Boolean,
      default: false
    },
    userInfo: {
      type: Object,
      default: () => ({})
    }
  },
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
      currentPage: 1,
      pageSize: 10,
      total: 100,
      currentBookInfo: {},
      loading: false,
      gridData: []
    }
  },

  created() {
    this.activeIndex = '3';
    this.tableData = this.getTableData();
    this.hobbys = hobby;
  },

  watch: {
    // nameKeyWord(newVal) {
      // console.log(newVal);
      
      // const data = this.activeIndex == 3 ? mock1 : mock2;
      // const value = newVal.toUpperCase();
      // if (value) {
      //   this.tableData = data.filter(item => item.name.toUpperCase().indexOf(value) > -1);
      // } else {
      //   this.tableData = data;
      // }
    // }
  },

  methods: {
    // handleSelect(key, keyPath) {
    // handleSelect(key) {
    //   this.activeIndex = key;
    //   if (key == 3) {
    //     this.tableData = mock1;
    //     this.nameKeyWord = '';
    //   } else {
    //     this.tableData = mock2;
    //     this.nameKeyWord = '';
    //   }
    // },
    drawOpen() {
      this.drawerKeyWord.selectedHobbys = structuredClone(this.userHobbys);
    },
    // 高级搜索抽屉中 提交搜索 通过getAduancedSearchInfo获取搜索条件 然后关闭高级搜索的抽屉
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

    // 获取高级搜索的搜索条件 只有当对应的搜索条件是打开状态时，才返回对应的搜索条件
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
    // 双击INFO单元格的时候触发的方法 会跳转到书籍详情页面
    tableCellDbClick(row, column) {
      if (column.property === 'info') {
        // 跳转时 使用structuredClone方法 deep clone 当前行的数据
        this.$router.replace({ name: 'details', params: { row: structuredClone(row)} });        
      }
      
    },

    getTableData() {
      const loadingInstance = Loading.service({ 
        fullscreen: true,
        background: 'rgba(0, 0, 0, 0.5)',
        text: 'Loading...',
        spinner: 'el-icon-loading'
        });

      const params = this.getSearchParams();
      getBooks(params).then(res => {
        const resData = res.date;
        this.tableData = resData.records;
        this.total = resData.total
      }).catch(error => {
        this.tableData = [];
        console.error(error);
      }).finally(() => {
        loadingInstance.close();
      });
    },
    getSearchParams(){
      const params = {
        pageNum: this.currentPage,
        pageSize: this.pageSize,
      };
      this.nameKeyWord !== '' && (params.name = this.nameKeyWord);
      return params;
    },

    bookBorrow(currentBook) {
      this.$confirm(`Are you sure to check out books: ${currentBook.bookName}`, 'Confirmation information', {
          distinguishCancelAndClose: true,
          confirmButtonText: 'OK',
          cancelButtonText: 'CANCEL'
        })
        .then(() => {
          // TODO: 借书逻辑
          const deepClonedCurrentBook = structuredClone(currentBook);
          deepClonedCurrentBook.bookStatus = BOOK_STATUS.BORROWED;
          addBorrow({
            bookId: deepClonedCurrentBook.bookId,
            userId: this.userInfo.userId,
            endDate: this.getDate()
          }).then(res => {
            console.log(res);
            this.getTableData();
            this.$message({
              type: 'success',
              message: 'Borrowing success !'
            });
            currentBook.bookStatus = BOOK_STATUS.BORROWED;
          }).catch(error => {
            console.error(error);
            this.$message({
              type: 'error',
              message: 'Borrowing failed !'
            });
          });
        })
        .catch(action => {
          console.log(action);          
          // this.$message({
          //   type: 'info',
          //   message: action === 'cancel'
          //     ? '11'
          //     : '22'
          // })
        });
      console.log(currentBook);
      
    },

    getDate() {
      // 解析日期字符串
      let date = new Date();
      
      // 获取当前日期的年份、月份、日、时、分、秒
      let year = date.getFullYear();
      let month = date.getMonth(); // 注意：月份是从0开始计数的，0代表1月
      let day = date.getDate();
      let hours = date.getHours();
      let minutes = date.getMinutes();
      let seconds = date.getSeconds();

      // 设置日期为当前日期加上一个月
      date.setMonth(month + 1);

      // 如果设置的日期超过了目标月份的最大天数，则调整为该月的最大天数
      let maxDay = new Date(year, month + 1, 0).getDate(); // 获取下个月的最大天数
      if (day > maxDay) {
          day = maxDay;
          date.setDate(day);
      }

      // 设置时间部分
      date.setHours(hours);
      date.setMinutes(minutes);
      date.setSeconds(seconds);

      // 返回格式化后的日期字符串
      return date.toISOString().slice(0, -1); // 使用toISOString()方法格式化日期，并去掉最后的Z
    },

    searchComponent(row) {
      this.currentBookInfo = row;
      this.$refs.bookComponent.changeVisible(true);
    },

    check(row) {
      this.loading = true;
      getBorrows({
        pageSize: 1,
        pageNum: 10000,
        bookId: row.bookId
      }).then(res => {
        if (res.code == 200) {
           res.date.records.forEach(element => {
            element.borrowDate && (element.borrowDate = formatDate(element.borrowDate));
            element.returnDate && (element.returnDate = formatDate(element.returnDate));
          });
          this.gridData = res.date.records;
        } else {
          this.gridData = [];
        }
        this.loading = false;
      }).catch(e => {
        this.gridData = [];
        console.error(e);
        this.loading = false;
        this.$message({
          type: 'error',
          message: 'Checking failed !'
        });
      })
      
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
  width:100%;
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
