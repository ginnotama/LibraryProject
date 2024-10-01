<template>
  <div>
    <el-button icon="el-icon-back" type="text" @click="goBook"> BACK</el-button>
    userSetting
  </div>
</template>

<script>
export default {
  methods: {
    goBook() {
      this.$router.replace({ path: '/book' });
    }
  }
}
</script>

<style>

</style>