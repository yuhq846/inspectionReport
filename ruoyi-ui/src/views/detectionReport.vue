

<template>
  <div class="weui-msg" >
    <div class="weui-msg__text-area">
      <p class="weui-msg__desc">
        如要验证真伪，关注“力鸿集团”微信公众号，使用公众号的“<a :href="url">证书扫码</a>”功能扫描二维码。
      </p>
    </div>
    <div class="weui-msg__opr-area">
      <img :src="path" style="width: 100%">
    </div>
    <div class="weui-msg__opr-area">
      <img src="../assets/images/qywx_code.jpg">
    </div>
    <div class="weui-msg__extra-area">
      <div class="weui-footer">
        <p class="weui-footer__links">
          <a href="javascript:void(0);" class="weui-footer__link">力鸿检验集团有限公司</a>
        </p>
        <p class="weui-footer__text">Copyright © 2019</p>
      </div>
    </div>
  </div>
</template>
<script setup>
import {getReportView} from "@/api/system/report";

export default {
  name: "CertificateImage",
  data() {
    return {
      form: {},
      path: '',
      reportId:'',
      url: '',
    }
  },
  created() {
    this.getInfo()
    this.url='/scan?reportId='+this.$route.query.reportId
  },
  mounted() {
  },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: '提示'
    }
  },
  methods: {
    getInfo() {
      let id = this.$route.query.reportId
      if (id) {
        getReportView({id: this.$route.query.reportId}).then(response => {
          this.form = response.data
          this.path=process.env.VUE_APP_BASE_API +this.form.verifyImg
          console.log("path",this.path)
        });
      }
    },
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
.weui-msg {
  padding-top: 36px;
  text-align: center;
}
.weui-msg__text-area {
  margin-bottom: 25px;
  padding: 0 20px;
}
.weui-msg__desc {
  font-size: 14px;
  color: #999;
}
.weui-msg__text-area a {
  color: #586c94;
}
p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  unicode-bidi: isolate;
}
a {
  text-decoration: none;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}
.weui-msg__opr-area {
  margin-bottom: 25px;
}
.weui-msg__extra-area {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  text-align: center;
  margin-bottom: 15px;
  font-size: 14px;
  color: #999;
}
.weui-footer {
  color: #999;
}
.weui-footer, .weui-grid__label {
  text-align: center;
  font-size: 14px;
}
.weui-footer a {
  color: #586c94;
}
.weui-footer__links {
  margin: 0 0;
}
.weui-footer__text{
  margin: 0 0;
}

</style>
