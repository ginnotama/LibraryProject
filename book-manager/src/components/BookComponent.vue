<template>
  <div>
    <el-dialog
      title="COMPONENT"
      :visible.sync="dialogVisible"
      width="40%"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      @open="componentDialogOpen"
      >
      <div v-show="componentList.length !== 0" v-for="(value, key) in componentList" :key="key" class="component-item flex-box">
        <div class="padding-8" style="padding-bottom: 0px; display: flex; line-height: 1.3;">
          {{value.commentDesc}}
        </div>
        <div class="padding-8 flex-end" >userName: {{value.userName}}  comment time: {{value.commentCreateTime}}</div>
      </div>
      <div v-show="componentList.length === 0" >
        No Comments
      </div>
      <!-- <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span> -->
    </el-dialog>
  </div>
</template>

<script>
import { getComments } from "../api/Comment";
import { formatDate } from "../Utils/util";

export default {
  props:{
    currentBookInfo: {
      type: Object,
      default: () => ({})
    }
  },
  data() {
    return {
      dialogVisible: false,
      componentList: []
    }
  },

  methods: {
    changeVisible(value) {
      this.dialogVisible = value;
    },
    getCommentList() {
      getComments({
        bookId: this.currentBookInfo.bookId,
        pageNum: 1,
        pageSize: 10000
      }).then(res => {
        if (res.code == 200) {
          res.date.records.forEach(element => {
            element.commentCreateTime = formatDate(element.commentCreateTime);
          });
          this.componentList = res.date.records;
        }
      }).catch(error => {
        this.componentList = [];
        console.error(error);
      })
    },
    componentDialogOpen() {
      this.getCommentList();
      console.log('打开弹窗', this.currentBookInfo);
      
    }
  }
}
</script>

<style scoped>
::v-deep .el-dialog__header {
  display: flex;
}
::v-deep .el-dialog__body {
  padding: 8px 12px 12px 12px;
  max-height: 500px;
  overflow-x: auto;
}
.component-item {
  margin-bottom: 12px;
  border: 1px solid #50bfff;
  border-radius: 5px;
  transition: .3s;
}
.component-item:hover {
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.2);
  background-color: rgba(0,0,0,.1);

}
.flex-box {
  display: flex;
  justify-content: center;
  flex-direction: column;
}
.padding-8 {

  padding: 8px
}

.flex-end {
  display: flex;
  justify-content: flex-end;
}
</style>