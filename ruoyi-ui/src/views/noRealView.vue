<script setup>

</script>

<template>
  <div class="weui-msg">
    <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg el-icon-warning"></i></div>
    <div class="weui-msg__text-area">
      <h2 class="weui-msg__title">证书不存在</h2>
      <p class="weui-msg__desc">该二维码非我公司证书二维码。</p>
    </div>
    <div class="weui-msg__opr-area">
      <p class="weui-btn-area">
        <a href="javascript:goback();" class="weui-btn weui-btn_warn">关闭</a>
      </p>
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

<style scoped lang="scss">
.weui-msg {
  padding-top: 36px;
  text-align: center;
}
.weui-msg__icon-area {
  margin-bottom: 30px;
}
.weui-msg__text-area {
  margin-bottom: 25px;
  padding: 0 20px;
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
}
.weui-msg__extra-area {
  margin-bottom: 15px;
  font-size: 14px;
  color: #999;
}
.weui-icon_msg {
  font-size: 100px;
}
.weui-icon_msg.weui-icon-warn {
  color: #f76260;
}
.weui-msg__title {
  margin-bottom: 5px;
  font-weight: 400;
  font-size: 20px;
}
.weui-msg__desc {
  font-size: 14px;
  color: #999;
}
.weui-btn-area {
  margin: 1.17647059em 15px .3em;
}
.weui-btn_warn:not(.weui-btn_disabled):visited {
  color: #fff;
}
.weui-btn_warn {
  background-color: #e64340;
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
.weui-footer {
  color: #999;
}
.weui-footer, .weui-grid__label {
  text-align: center;
  font-size: 14px;
}
.weui-footer__links {
  font-size: 0;
}
.weui-footer__link {
  display: inline-block;
  vertical-align: top;
  margin: 0 .62em;
  position: relative;
  font-size: 14px;
}
.weui-footer__text {
  padding: 0 .34em;
  font-size: 12px;
}
.weui-footer a {
  color: #586c94;
}
</style>
