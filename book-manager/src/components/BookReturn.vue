<template>
  <div style="height: 100%">
      <el-table
        :data="bookList"
        height="calc(100% - 42px)"
        style="width: 100%;"
        >
        <el-table-column
          prop="bookName"
          label="BOOK NAME"
          width="180">
        </el-table-column>
        <el-table-column
          prop="bookId"
          label="BOOK ID"
          width="180">
        </el-table-column>
        <el-table-column
          prop="borrowDate"
          label="BORROW DATE"
          width="180">
        </el-table-column>
        <el-table-column
          prop="returnDate"
          label="RETURN DATE"
          width="180">
        </el-table-column>
        <el-table-column
          label="Status"
          width="180">
          <template slot-scope="scope">
            <!-- 借阅状态 1 借阅完成 0 借阅中 -->
            <span v-if="scope.row.borrowState === 0">Borrowing</span>
            <span v-if="scope.row.borrowState === 1">Borrowing completed</span>
          </template>
        </el-table-column>
        <el-table-column
          label="COMPONENT"
          width="180"
          fixed="right"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="searchComponent(scope.row)" >View comments</el-button>
          </template>
        </el-table-column>

        <el-table-column 
          label="OPERATE"
          fixed="right">
          <template slot-scope="scope">
            <el-button type="text" :disabled="!(scope.row.borrowState === 0)" @click="openReturnDialog(scope.row)">RETURN BOOK</el-button>
          </template>
        </el-table-column>

        <!-- <el-table-column
          label="OPERATION"
          width="180"
          fixed="right"
        >
          <template slot-scope="scope">
            <el-button type="text" :disabled="!(scope.row.bookStatus === 0) || !isLogin" @click="bookBorrow(scope.row)">BOOROWING</el-button>
          </template>
        </el-table-column> -->
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
    <BookComponentVue ref="bookComponent" :currentBookInfo=currentBookInfo />

    <el-dialog
      title="RETURN"
      :visible.sync="dialogVisible"
      width="40%"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      @open="returnDialogOpen"
      >
        <el-input
          type="textarea"
          :rows="8"
          placeholder="Please fill in a comment for this book"
          v-model="textarea">
        </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">CANCEL</el-button>
        <el-button type="primary" @click="returnBook">RETURN BOOK</el-button>
      </span>
    </el-dialog>

  </div>

</template>

<script>
import { Loading } from 'element-ui';
import { getBorrows, returnBook } from '../api/Borrow';
import { addComment } from '../api/Comment';
import BookComponentVue from './BookComponent.vue';
import { formatDate } from "../Utils/util";
export default {
  props: {
    userInfo: {
      type: Object,
      default: () => ({})
    }
  },
  components: {
    BookComponentVue
  },


  data() {
    return {
      bookList: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      currentBookInfo: {},
      dialogVisible: false,
      textarea: ''
    }
  },

  mounted() {
    console.log('00000:----------：', this.userInfo);
    this.getTableData();
  },

  methods: {
    returnDialogOpen() {
      this.textarea = '';
    },


    openReturnDialog(row) {
      this.dialogVisible = true;
      this.currentBookInfo = row;

    },

    searchComponent(row) {
      this.currentBookInfo = row;
      this.$refs.bookComponent.changeVisible(true);
    },
    getTableData() {
      const loadingInstance = Loading.service({ 
        fullscreen: true,
        background: 'rgba(0, 0, 0, 0.5)',
        text: 'Loading...',
        spinner: 'el-icon-loading'
        });
      getBorrows({
        pageSize: this.pageSize,
        pageNum: this.currentPage,
        userId: this.userInfo.userId
      }).then(res => {
        if (res.code == 200) {
          res.date.records.forEach(element => {
            element.borrowDate = formatDate(element.borrowDate);
            element.returnDate = formatDate(element.returnDate);
          });
          this.bookList = res.date.records;
          // this.bookList = [{
          //   bookId: 4,
          //   borrowState: 0
          // }]
          this.total = res.date.total;
        } else {
          this.bookList = [];
          this.total = 0;
        }
      }).catch(error => {
        this.bookList = [];
        this.total = 0;
        console.error(error);
      }).finally(() => {
        loadingInstance.close();
      })
    },

    returnBook() {
      if (this.textarea) {
        addComment({
          bookId: this.currentBookInfo.bookId,
          commentDesc: this.textarea,
          userId: this.userInfo.userId
        });
      }
      returnBook({
        bookId: this.currentBookInfo.bookId,
        borrowId: this.currentBookInfo.borrowId,
        userId: this.currentBookInfo.userId,
      }).then(res => {
        if (res.code == 200) {
          this.$message({
            type: 'success',
            message: 'Return success !'
          });
          this.dialogVisible = false;
          this.getTableData();
        } else {
          this.$message({
            type: 'error',
            message: 'Return failed !'
          });
        }
      }).catch(error => {
        console.error(error);
        this.$message({
          type: 'error',
          message: 'Return failed !'
        });
      });
    }
  }
}
</script>

<style scoped>
::v-deep .el-dialog__header {
  display: flex;
}
</style>