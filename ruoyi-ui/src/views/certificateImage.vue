

<template>
  <div style="margin: 5px">
    <div style="display: flex ;align-items: center;">
      <el-button type="success" size="mini" style="font-size: 10px;font-weight: bold;padding: 7px 5px;">
        上一页
      </el-button>
      <div style="margin: 0 10px">1/1</div>
      <el-button type="success" size="mini"  style="font-size: 10px;font-weight: bold;padding: 7px 5px;">
        下一页
      </el-button>
      <el-button type="success" size="mini"  plain @click="back" style="background-color: unset;font-size: 10px;font-weight: bold;padding: 7px 5px;">
        返回
      </el-button>
    </div>
    <img :src="path" style="width: 100%">
  </div>
</template>
<script setup>
export default {
  name: "CertificateImage",
  data() {
    return {
      path: '',
      reportId:'',
    }
  },
  created() {
    this.path=process.env.VUE_APP_BASE_API +this.$route.query.certificateImg
    this.reportId=this.$route.query.reportId
  },
  mounted() {
  },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: '中检在线'
    }
  },
  methods: {
    back() {
      this.$router.push({
        path: '/certificateView',
        query: {
          reportId: this.reportId,
        }
      })
    }
  }
}
</script>
<style scoped lang="scss">

</style>
