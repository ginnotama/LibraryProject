<template>
  <div style="padding: 20px">
    <div style="width: 60%; height: 100%;">
      <el-form ref="form" :model="form" label-position="left" :rules="rules" label-width="150px">
        <el-form-item label="BOOK NAME" prop="bookName">
          <el-input v-model="form.bookName"></el-input>
        </el-form-item>
        <el-form-item label="BOOK NUM" prop="bookNum">
          <el-input v-model="form.bookNum"></el-input>
        </el-form-item>
        <el-form-item label="BOOK LOCATION" prop="bookLocation">
          <el-input v-model="form.bookLocation"></el-input>
        </el-form-item>
        <el-form-item label="BOOK INFO" prop="bookDesc">
          <el-input v-model="form.bookDesc"></el-input>
        </el-form-item>
        <el-form-item label="BOOK AUTHOR" prop="bookAuthor">
          <el-input v-model="form.bookAuthor"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('form')">ADD</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { addBook } from "../api/Book";
export default {
  data() {
    return {
      form: {
        bookName: '',
        bookNum: '',
        typeId: 1,
        bookLocation: '',
        bookDesc: '',
        bookAuthor: '',
      },
      rules: {

        bookName: [
          { required: true, message: 'please enter bookName', trigger: 'blur' }
        ],
                bookNum: [
          { required: true, message: 'please enter bookNum', trigger: 'blur' }
        ],
                bookLocation: [
          { required: true, message: 'please enter bookLocation', trigger: 'blur' }
        ],
                bookDesc: [
          { required: true, message: 'please enter bookDesc', trigger: 'blur' }
        ],
                bookAuthor: [
          { required: true, message: 'please enter bookAuthor', trigger: 'blur' }
        ],
      }
    }
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addBook(this.form).then(res => {
            if (res.code == 200) {
              this.$message({
                message: 'Add success',
                type: 'success'
              });
              this.form = {
                bookName: '',
                bookNum: '',
                typeId: 1,
                bookLocation: '',
                bookDesc: '',
                bookAuthor: '',
              };
              // this.$emit('addBookSuccess');
            }
          })
        } else {
          return false;
        }
      })
    }
  }
}
</script>

<style>

</style>