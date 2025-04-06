<script setup>
import { getJsapiSignature } from "@/api/login";
export default {
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
    this.initWeChat()
  },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: '证书识别'
    }
  },
  methods: {
    async initWeChat() {
      //const url = encodeURIComponent(location.href.split("#")[0]); // 当前页面 URL
      //const url = encodeURIComponent("http://192.168.2.76:1024/scan")
      const url=location.href
      console.log("url",url)
      const { data } = await getJsapiSignature('wx68ba58cf50d520bc',{url:url}); // 向后端请求签名信息
      console.log("1223",data)
      wx.config({
        debug: false, // 生产环境关闭
        appId: data.appId,

        timestamp: data.timestamp,
        nonceStr: data.nonceStr,
        signature: data.signature,
        jsApiList: ["scanQRCode"], // 需要调用的 API
      });

      wx.ready(() => {
        console.log("微信 JS-SDK 准备就绪");
      });

      wx.error((err) => {
        console.error("微信 JS-SDK 初始化失败", err);
      });
    },
    scanQRCode() {
      wx.scanQRCode({
        needResult: 1, // 0: 只返回扫码结果，1: 返回扫码结果并回调
        scanType: ["qrCode", "barCode"], // 可扫描二维码和条形码
        success: (res) => {
          //alert("扫码结果：" + res.resultStr);
          let result=res.resultStr;
          var zsxId = result.split("reportId=")[1]
          if (zsxId) {
            window.location.href = '/checkView?reportId=' + zsxId;
          } else {
            window.location.href = '/noRealView';
          }
        },
      });
    },
  }
}


</script>

<template>
  <div class="">
    <div class="page__bd">
      <article class="weui-article">
        <h1>使用指南</h1>
        <section>
          <h2 class="f-s-12"> 1. 仅支持在本微信公众号下使用“证书扫码”辨别我公司出具的证书真伪。</h2>
          <section >


          </section>
          <h2 class="f-s-12">2.点击本页下方“扫码”按钮，扫描二维码。</h2>
          <section>
            <p class="f-s-12">
              2.1 如果扫码结果直接显示出电子证书，该证书即为我公司出具，为真实证书。
            </p>
            <p class="f-s-12">
              2.2 如果扫码结果显示下图，该证书非我公司出具，为仿造证书。
            </p>
            <p>
              <img src="../assets/images/msg1.png" alt="">
            </p>
          </section>
          <h2 class="title f-s-12">3.若未关注本公众号时，使用微信直接扫描本公司证书二维码，会出现如下提示。可点击提示中的蓝色文字“证书扫码”或直接长按二维码关注公众号后再进行扫码验证。</h2>
          <section>
            <p>
              <img src="../assets/images/msg2.png" alt="">
            </p>
          </section>
        </section>
      </article>
    </div>
    <div class="weui-msg__text-area">
      <p class="weui-msg__desc">通过扫描证书右下方二维码，可以识别证书真伪。</p>
    </div>
    <div class="weui-msg__opr-area">
      <p class="weui-btn-area">
        <a @click="scanQRCode()" class="weui-btn weui-btn_primary">扫码</a>
      </p>
    </div>
    <div class="weui-msg__opr-area">
      <div class="weui-footer">
        <p class="weui-footer__links">
          <a href="javascript:void(0);" class="weui-footer__link">力鸿检验集团有限公司</a>
        </p>
        <p class="weui-footer__text">Copyright © 2019</p>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.page__bd{
  font-family: -apple-system-font, Helvetica Neue, Helvetica, sans-serif !important;
}
.weui-article {
  padding: 20px 15px;
  font-size: 15px;
}
.weui-article h2, .weui-article h3 {
  font-weight: 400;
  margin-bottom: 15px;
}
.weui-article h2 {
  font-size: 18px;
}

element.style {
}
.weui-article h2, .weui-article h3 {
  font-weight: 400;
  margin-bottom: 15px;
}
.f-s-12 {
  font-size: 16px;
}

.weui-article p {
  margin: 0 0 0.8em;
}

.weui-article * {
  max-width: 100%;
  box-sizing: border-box;
  word-wrap: break-word;
}
.weui-msg__text-area {
  margin-bottom: 25px;
  padding: 0 20px;
}
.weui-msg__desc {
  font-size: 14px;
  color: #999;
}
.weui-msg__opr-area {
  margin-bottom: 25px;
}
.weui-btn-area {
  margin: 1.17647059em 15px .3em;
}
.weui-btn_primary {
  background-color: #1aad19;
}
.weui-btn {
  position: relative;
  display: block;
  margin-left: auto;
  margin-right: auto;
  padding-left: 14px;
  padding-right: 14px;
  box-sizing: border-box;
  font-size: 18px;
  text-align: center;
  text-decoration: none;
  color: #fff;
  line-height: 2.55555556;
  border-radius: 5px;
  -webkit-tap-highlight-color: rgba(0,0,0,0);
  overflow: hidden;
}
.weui-msg__opr-area {
  margin-bottom: 25px;
}
.weui-footer {
  color: #999;
}
.weui-footer__links {
  font-size: 0;
}
.weui-footer__text {
  padding: 0 .34em;
  font-size: 12px;
}
h2 {
  display: block;
  font-size: 1.5em;
  margin-block-start: 0.83em;
  margin-block-end: 0.83em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}
p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
</style>
