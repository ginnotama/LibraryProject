<template>
  <div style="height: 100%">
      <el-table
        :data="bookList"
        height="calc(100% - 42px)"
        style="width: 100%;"
        >
        <el-table-column
          prop="bookId"
          label="BOOK ID"
          width="180">
        </el-table-column>
        <el-table-column
          prop="borrowDate"
          label="BORROW DATE"
          width="80">
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
          label="OPERATE"
          fixed="right">
          <template slot-scope="scope">
            <el-button type="text" :disabled="!(scope.row.borrowState === 0)" @click="returnBook(scope.row)">RETURN BOOK</el-button>
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
  </div>

</template>

<script>
import { Loading } from 'element-ui';
import { getBorrows, returnBook } from '../api/Borrow';
export default {
  props: {
    userInfo: {
      type: Object,
      default: () => ({})
    }
  },

  data() {
    return {
      bookList: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
    }
  },

  mounted() {
    console.log('00000:----------：', this.userInfo);
    this.getTableData();
  },

  methods: {
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
          this.bookList = res.date.records;
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

    returnBook(row) {
      returnBook({
        bookId: row.bookId,
        borrowId: row.borrowId,
        userId: row.userId,
      }).then(res => {
        if (res.code == 200) {
          this.$message({
            type: 'success',
            message: 'Return success !'
          });
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

<style>

</style>