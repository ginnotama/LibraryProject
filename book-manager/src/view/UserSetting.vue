<template>
  <div class="user-setting">
    <div class="user-header"> 
      <el-button icon="el-icon-back" type="text" @click="goBook"> BACK</el-button>
    </div>
    <div class="user-body">
      <el-menu :default-active="activeIndex" class="el-menu-demo" @select="handleSelect">
        <el-menu-item index="personalInformation">USER</el-menu-item>
        <el-menu-item index="preferences">HOBBY</el-menu-item>
      </el-menu>

      <div v-show="activeIndex==='personalInformation'" class="user-body-form">
        <el-form ref="form" :model="userFrom" label-position="left" label-width="100px">
          <el-form-item label="USER NAME">
            <el-input v-model="userFrom.name"></el-input>
          </el-form-item>
          <el-form-item label="PASSWORD">
            <el-input v-model="userFrom.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="GENDER">
              <el-radio v-model="userFrom.gender" label="1">MAN</el-radio>
              <el-radio v-model="userFrom.gender" label="2">WOMAN</el-radio>
              <el-radio v-model="userFrom.gender" label="3">UNKNWM</el-radio>
          </el-form-item>
          <el-form-item label="AGE">
            <el-input v-model="userFrom.age" type="number"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="save">SAVE</el-button>
            <!-- <el-button>取消</el-button> -->
          </el-form-item>
        </el-form>
      </div>

      <div v-show="activeIndex==='preferences'" class="user-body-form">
        <el-form ref="form" :model="hobbyFrom" label-position="left" label-width="120px">
          <el-form-item label="BOOK PREFERENCES">
            <el-checkbox-group v-model="hobbyFrom.checkedHobby" @change="handleCheckedCitiesChange">
              <el-checkbox v-for="hobby in hobbys" :label="hobby" :key="hobby">{{hobby}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveHobby">SAVE</el-button>
            <!-- <el-button>取消</el-button> -->
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { hobby } from '../mock/hobbyMock'


export default {

  data: function () {
    return {
      activeIndex: 'personalInformation',
      userFrom: {
        name: '',
        password: '',
        age: '',
        gender: ''
      },
      hobbyFrom: {
        checkedHobby: ['Philosophy', 'Art', 'Safety science', 'Natural sciences', 'History'],
      },
      hobbys: []
    }
  },
  created() {
    this.hobbys = hobby;    
  },
  methods: {
    goBook() {
      this.$router.replace({ path: '/book' });
    },
    save() {
      console.log(123);
      
    },
    handleSelect(key) {
      this.activeIndex = key;
      // console.log(key,s keyPath);
    },
    handleCheckedCitiesChange(value) {
      console.log(value);
    },
    saveHobby() {
      // TODO: save hobby
      console.log('save Hobby', this.hobbyFrom);
      
    }
  }
}
</script>

<style scoped>
  .user-setting{
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    display: flex;
    flex-direction: column;
  }
  .user-header {
    padding: 12px 24px;
    display: flex;
    border-bottom: solid 1px #e6e6e6;
  }
  .user-body {
    flex: 1;
    display: flex;
    flex-direction: row;
  }

  .user-body-form {
    height: 100%;
    padding: 12px;
  }
</style>