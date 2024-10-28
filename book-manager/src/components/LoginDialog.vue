<template>
  <div>
    <el-dialog
    title="BOOK MANAGER"
      :visible.sync="dialogVisible"
      width="40%"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
      >
      <div>
        <el-tabs type="border-card"  v-model="activeName">


          <el-tab-pane label="Login" name="login">
            <el-form ref="form" :model="loginForm" label-position="left" label-width="100px">
              <el-form-item label="USER">
                <el-input v-model="loginForm.user"  placeholder="enter the account number"></el-input>
              </el-form-item>
              <el-form-item label="PASSWORD">
                <el-input v-model="loginForm.password" placeholder="enter the password" show-password></el-input>
              </el-form-item>
              <el-form-item label="CODE">
                <div style="display: flex">
                  <el-input v-model="loginForm.code" placeholder="enter the code"></el-input>
                  <img style="width: 100px; height: 40px; margin-left:10px" :src="imageUrl"/>
                </div>
              </el-form-item>
            </el-form>
          </el-tab-pane>


          <el-tab-pane label="Register" name="register">
            <el-form ref="form" :model="registerForm" label-position="left" label-width="100px">
              <el-form-item label="LoginName">
                <el-input v-model="registerForm.userLoginName"  placeholder="enter the account number"></el-input>
              </el-form-item>
              <el-form-item label="Password">
                <el-input v-model="registerForm.userPassword" placeholder="enter the password" show-password></el-input>
              </el-form-item>
              <el-form-item label="UserName">
                <el-input v-model="registerForm.userName" placeholder="enter the user name"></el-input>
              </el-form-item>
              <el-form-item label="Code">
                <div style="display: flex">
                  <el-input v-model="registerForm.code" placeholder="enter the code"></el-input>
                  <img style="width: 100px; height: 40px; margin-left:10px" :src="imageUrl"/>
                </div>
              </el-form-item>
              <el-form-item label="Type">
                <el-radio v-model="registerForm.type" label="0">STUDENT</el-radio>
                <el-radio v-model="registerForm.type" label="1">MANAGER</el-radio>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>

      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">CANCEL</el-button>
        <el-button type="primary" @click="clickSubmitBtn">SUBMIT</el-button>
      </span>
    </el-dialog>
    
  </div>
</template>

<script>

import { getCode, userLogin, registerManager, registerUser } from "../api/User";

export default {
  data() {
    return {
      activeName: 'login',
      dialogVisible: false,
      isRegister: false,
      imageUrl: require('@/assets/19101535287992295316826338.jpg'),
      loginForm: {
        user: '',
        password: '',
        code: ''
      },
      registerForm: {
        userLoginName: '',
        userPassword: '',
        code: '',
        userName: '',
        type: ''
      }
    }
  },

  watch: {
    dialogVisible(value) {
      if (value) {
        this.refreshCode();
        this.loginForm = {
          user: '',
          password: '',
          code: ''
        }
        this.registerForm ={
          userLoginName: '',
          userPassword: '',
          code: '',
          userName: '',
          type: ''
        }
      }
    },
    activeName(value) {
      this.refreshCode();
      if (value === 'login') {
        this.loginForm = {
          user: '',
          password: '',
          code: ''
        }
      }
      if (value === 'register') {
        this.registerForm ={
          userLoginName: '',
          userPassword: '',
          code: '',
          userName: '',
          type: ''
        }
      }
    }
  },
  methods: {

    refreshCode() {
      getCode().then(res => {
        this.imageUrl = URL.createObjectURL(res);
        // FIXME: 暂时用一张图片代替
        // this.imageUrl = require('@/assets/19101535287992295316826338.jpg');
        console.log('刷新验证码');
      }).catch((error) => {
        console.error(error);
        this.$message({
          message: 'CAN NOT GET CODE!',
          type: 'error'
        });
      })
    },
    changeVisibel(value) {
      this.dialogVisible = value;
    },
    clickSubmitBtn() {
      if (this.activeName === 'login') {
        if (Object.values(this.loginForm).some(item => item === '')) {
          this.$message({
            message: 'Please enter the information',
            type: 'warning'
          });
          return;
        }

        userLogin({
          code: this.loginForm.code,
          password: this.loginForm.password,
          userLoginName: this.loginForm.user
        }).then((res) => {
          const data = res.date;
          this.$emit('loginSucess', data);          
        }).catch(() => {
          this.$message({
            message: 'Login failed',
            type: 'error'
          });
        })
      }

      if ( this.activeName === 'register' ) {
        if (Object.values(this.registerForm).some(item => item === '')) {
          this.$message({
            message: 'Please enter the information',
            type: 'warning'
          });
          return;
        }
        if (this.registerForm.type == 0) {
          this.doRegisterUser();
        }
        if (this.registerForm.type == 1) {
          this.doRegisterManager();
        }
      }       
    },

    doRegisterUser() {
      registerUser({
        code: this.registerForm.code,
        userPassword: this.registerForm.userPassword,
        userLoginName: this.registerForm.userLoginName,
        userName: this.registerForm.userName
      }
      ).then((res) => {
        console.log(res);
        this.activeName = 'login';
        this.$message({
          message: 'Register success',
          type: 'success'
        });
      }).catch(error => {
        console.error(error);
        this.refreshCode();
        this.$message({
          message: 'Register failed',
          type: 'error'
        });
      })
    },
    doRegisterManager() {
      registerManager({
        code: this.registerForm.code,
        userPassword: this.registerForm.userPassword,
        userLoginName: this.registerForm.userLoginName,
        userName: this.registerForm.userName
      }
      ).then((res) => {
        console.log(res);
        this.activeName = 'login';
        this.$message({
         message: 'Register success',
        type: 'success'
        })
      }).catch(error => {
        console.error(error);
        this.refreshCode();
        this.$message({
          message: 'Register failed',
          type: 'error'
        });
      })
    },
  }
}
</script>

<style scoped>
.flex-box {
  display: flex !important;
}
::v-deep .el-dialog__header {
  display: flex;
}
::v-deep .el-dialog__body {
  padding-top: 10px;
  padding-bottom: 0px;
}
</style>