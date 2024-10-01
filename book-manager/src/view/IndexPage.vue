<template>
  <div class="index">
    <div class="box">
      <div>
        <h1>BOOK MANAGER</h1>
      </div>
      <div>
        <el-form ref="form" :model="form" label-position="left" label-width="100px">
          <el-form-item label="USER">
            <el-input v-model="form.user"  placeholder="enter the account number"></el-input>
          </el-form-item>
          <el-form-item label="PASSWORD">
            <el-input v-model="form.password" placeholder="enter the password" show-password></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <el-button type="primary" @click="onSubmit">{{ type }}</el-button>
        <el-button type="text" size="mini" @click="register">{{ textType }}</el-button>
      </div>
    </div>
        <!-- <router-link to="/booklist">BookList</router-link> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        user: '',
        password: ''
      },
      type: 'LOGIN',
      textType: 'REGISTER',
    }
  },
  created() {
    this.userMap = new Map();
    this.userMap.set('user', '123');
  },
  methods: {
    onSubmit() {
      if (this.type === 'SUBMIT') {
        if (this.form.user.length > 0 && this.form.password.length > 0) {
          this.userMap.set(this.form.user, this.form.password);
          this.$message({
            message: 'Registration is successful',
            type: 'success'
          });
          this.type = 'LOGIN',
          this.form = {
            user: '',
            password: ''
          }

          this.textType = 'REGISTER';
        } else {
          this.$message({
            message: 'Check your input',
            type: 'warning'
          });
        }
        return;
      }
      if (this.type === 'LOGIN') {
        if (this.userMap.has(this.form.user) && this.userMap.get(this.form.user) === this.form.password) {
          this.$message({
            message: 'Login is successful',
            type: 'success'
          });
          this.$router.replace({ path: '/book' });
          // this.$router.push('/book');
          this.form = {
            user: '',
            password: ''
          }
        } else {
          this.$message({
            message: 'The user or password is entered incorrectly',
            type: 'warning'
          });
        }
      }
    },
    register() {
      if (this.textType === 'REGISTER') {
        this.form = {
          user: '',
          password: ''
        }
        this.type = 'SUBMIT'
        this.textType = 'QUIT'
      } else {
        this.type = 'LOGIN'
        this.textType = 'REGISTER'
        this.form = {
          user: '',
          password: ''
        }
      }
    }
  }
}
</script>

<style scoped>
  .index{
    width: 100vw;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgb(151, 189, 233);
  }
  .box{
    width: 300px;
    padding: 24px 12px;
    height: auto;
    border-radius: 10px;
    background-color: rgb(224, 234, 236);
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
  }
</style>