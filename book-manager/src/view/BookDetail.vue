<template>
    <div class="detail-setting">
      <div class="detail-header"> 
        <el-button icon="el-icon-back" type="text" @click="goBook"> BACK</el-button>
      </div>
      <div class="detail-body">
        {{ params }}
      </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      params: ''
    }
  },

  created() {
    console.log('this.$route', this.$route);
    this.params = this.$route.params;
  },

  methods: {
    goBook() {
      this.$router.replace({ path: '/book' });
    }
  }
}
</script>

<style scoped>
  .detail-setting{
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    display: flex;
    flex-direction: column;
  }
  .detail-header {
    padding: 12px 24px;
    display: flex;
    border-bottom: solid 1px #e6e6e6;
  }
  .detail-body {
    flex: 1;
    display: flex;
    flex-direction: row;
  }

  .detail-body-form {
    height: 100%;
    padding: 12px;
  }
</style>