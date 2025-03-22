<template>
  <el-card shadow="never" class="box-card card-bg "
           :body-style="{'padding': '14px 15px 7px', 'min-height': '40px', 'border-bottom':'none'}">
    <div slot="header" class="elchatHeader">
      <img class="backTop"
           src="data:image/png;base64,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">
      <img class="logoTop" src="../assets/images/logo_white_lighter.png">
    </div>
    <h2 class="pageTitle">证书/报告信息</h2>
    <div style="display: flex">
      <el-tag size="mini" color="unset" class="teg"><el-image
        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAHqADAAQAAAABAAAAHgAAAADKQTcFAAAELUlEQVRIDd1Xb4hUVRQ/571ZsVTaDXd2ZwzD/LMRFIoh6ScJpQRFpU9LhARRJLKp1YazM7s5b0cx/7XI+sUgMai0KJM+JtW2Zn4UCVQWsQ8zzo4tao2ruDvv9LuDR+57M0OzqxT4YDj3/P3dc965574h+p8engyul5cXxackCy0kptPskJds5d8mEsuZiLGx9a7IuwD9gpkOujNoPnb+JfgTvTnZPJFYdWcMwOUilECWzRGH1m+L8WUFQgXmAPwo+GLEpa5EC59WXS1aAdyblbVCtBYlfAxADcisBc5tkN0A6MEn4tT3OvPtcMAfRSKDOXobdh3waQa9SEJ5rMfg95e49G2qlU+oXwAY5eoA2CZh2gODogMnvIsCsrjQGeW8Ov0b3ZOTmdjZ0wCPIlYEG2gEUCeS2Z+KcX+Ffzonf2QKsrBC8QAESOp5LyeXNVSwuURmx5vpvCofJG2K0HkReVJjBoFV+h/Qhw8Y/eJ5WTlbq3iBjJm59OcwubWM65Wnr8guEkmiq4+rz98Rck185QPAEI7iN02Vk6HprOwmXzoBkumexT0aA5AmrolffsLARZy/SQOjvHuJ5D2Aeqk4JxXE0DtSjltUWRh4BHWOqtJQBFvcX5DptqzaGmd0H8q7lYjTAO0O2/B4eQKOqDwMPOT7NE+VZSp0fGScftp5XZoCcosB6Mc4o1vY4Q/t8lom2A/NBT+ksgAwxuWQL7RAlYZibLaDtI2P0g/7bsjjts6sUZE+gL7jONyDcbg9rFcesdtMfOUDwLhXz2CmLlOlock4D0aEXsZy/s1iEBzdewDl7UCm3ckYp22/8BodvszEV3kAeCrTAEqy9JjIFDUwNDGLT7ELcKZ5AD9pLgF0bz+6d5PDnEKmnm0fXn8qMhU31BL0z8+qCwC/38oFKH6/mKNVaqA02cK/ui69hNvmqVuCK49kI0C7UJFetalFs3lag02fS8T4qtoEgI0QO/vMZ9qgBjY1F7zjAJzopkP8AUB32Ppaa/TNBgAfsfV4pcFn/zVpLI7SJZpCS1PNfCGonTiXzsuzVKKBGY/SnC1NfF0jVGRslMjogIxRRo3ui/q0E9n12aAmXgWwET7i0kcgi3E+1xl+sg8+o9oB+kyUy/ECYaoCo8nwDvFehD5JF2RRwKNOpjcvL/iEL1HEeSvO92a0ulcFNko0zoA4tJHG6HvzFakO9VDvqrRhAn6HRnwzEedfqvnUBDbG3TE+BucM+XTKG5YV1QKEZdjkavTHIAL3YKh8FdYrX9HVqrBpJisrcZEehuzzhmm0Y1sjX7P1Zr07L9FbJerB5+x6bPa1rlY+Gbax+bqAjUNmWFr8ErIXWoczeTjCdNTBB1zpDj2HTbXjJLyKuf41fgl7UNhg9rpuYHW6+6/hDQyaVzDF5kJuBv83DUyH7H8Xal+L/gM/DX0cUOhQywAAAABJRU5ErkJggg=="
        class="image"></el-image><span style="font-weight: bold">一证一码</span>
      </el-tag>
    <el-tag size="mini" color="unset" class="teg"><el-image
        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAAXNSR0IArs4c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAABAAEAAKACAAQAAAABAAAAHKADAAQAAAABAAAAHAAAAABkvfSiAAAEL0lEQVRIDa1WXWhcVRD+5u5iVNCKVbO7wRLzIj4YLWoSK9gSaRGxKsW3KqUiqPhDH2wrJhtLdjdbEfEHfVNatFrEnyoUQYupP1BrlTT6UIpQiz+7ezdFKLaCNXvv+M1tbzi52TRbuwd2z8ycmfnOmZlz5gpaGGMVvS0AVkLQr4peAToUSNH0JOlJyg+KYPdwVg4s5I76zYeqSsHHgxJig0KXikidmp/R+S+cpwgcQNFFoGsIvhKqGepMqOCVfAZvk6Z47mgKWK5p97RiO50sh8guT7B1KIPv53NimytPYSAI8DTpNQIZ5/kfzmfkaBJyDmDB10GE+Jjb+51A61sJk+u0WNO+ULGNjq/m7+7hnHztrs8CHKvp8obiUwZjfNGFWPvUYvnLVW6Vfv6YXnLqX7zDcA9y06uHsrI3tp0BLNe1ZzrAj8zL3nwO9zF8Yaz0f2aG1itW8QlzersH9PKkv5of0mCqVBoB3iT5W8cFWHu+YObTfFiUeKIK07PdMEweAbIa11GwIu3hoc1XyglbaMewlKQ8rDffpRoeMJ8RaqGiB4l/dCQna5JApbp2Bg31k/L5ePFkUz4rL7jrharuIr8kn5Ob0kVfB8JAb/RS8rirFNNXBDhxDPJkzMczw7SFmzzMEH0Qy2xmhX7j8kbzlFsbge4v1nUZiF4crWqdx47Cm1Sej6fNkdGKvj7fuiunbyGOX6jpcwaylHHd045CcUFcmr4ZEHzOGzDg8e8GMvZcnXUwz1/wRLNyc1aD5KLgiL3DaeZhEY9ZTa4neeplPMXlrpwX+zGG6pFYRocvjXTJxphPzFOMZEc6Esrp65FQWJhVfEvLj2LFtOCHmE7O0WPP+kkzpMfJXJVUWIjnbmmGyZGsvLiQbrTOzsL5ZJphOUSipyWj81AiTjcPN8m0YIJ+Vlnpnos/Hs8qr6Vhvqm8yho1XzN8GKo+U65LH62/S3ooVnWDyWiwmFvKlXy9w/gwwEXkry9W9Anj3THcJa+5fKmOfiYgw1dod3QqlvxhGh9q9rSNVvh2nOMY6WJTcgYr+X2yPdHTZnJNYQsC3WnNM9lwk8aOn5ZIfj3cMh3q/fBknRnYS4N8J95jP/mKnd7C2rZhjZgNfZt9cth3jjmO7uGZp2dF25DoyN5mNuAdLJQl/L5ZfQbjdHtqJ5D5evVPvfT4P9jBqzDIZN7lftfMSm4MzE5QZh3fmUrh0WczMqdyY71mM6v6ZlbZW/zZJ+S97I1funpRDl2B0ZLCG6zavxsh9rGCd4752p/USfLW61iN7xLoAH8+HfcmwSLfScOYtxyU+CHMC76Z9HXcxh/c8TjXf+al9JkMexYv49xLgGVMWjfzNMGQvTyUtXBGLSl2NzM3DenMKgl7JUpTuFVD3EPwPoJcS/HFNAwIdIqb+Img+8XDnuFO2efaNqP/A3tdojOGcozLAAAAAElFTkSuQmCC"
        class="image"></el-image><span style="font-weight: bold">扫码验真</span>
    </el-tag>
    </div>
    <el-card shadow="always" class="info-card">
      <div slot="header">
        <span style="font-size: 14px;color: #888;">{{form.reportNo}}</span>
      </div>
      <el-descriptions :column="1" direction="vertical" :colon="false">
        <el-descriptions-item label="签发日期" style="font-size: 14px"><span style="color: #000">{{
            parseTime(form.signIssueDate, '{y}-{m}-{d}')
          }}</span></el-descriptions-item>
      </el-descriptions>
      <div style="text-align: right;">
        <el-button type="success" size="mini" style="font-size: 10px;font-weight: bold;padding: 7px 5px;" @click="openImg">查看证书
        </el-button>
      </div>
    </el-card>
      <el-tabs  v-model="activeName" class="no-underline-tabs" style=" border-bottom: none !important;">
          <el-tab-pane label="基本信息" name="base">
            <el-form :model="form" label-width="120px" size="mini" label-position="left">

              <el-form-item label="证书编号" style="text-align: right;margin:10px 0px;">{{ form.reportNo }}</el-form-item>
              <el-divider style="margin:5px 0px;"></el-divider>
              <el-form-item label="发布状态" style="text-align: right;margin:10px 0px;">已发布</el-form-item>
              <el-divider></el-divider>
              <el-form-item label="签发日期" style="text-align: right;margin:10px 0px;">
                {{ parseTime(form.signIssueDate, '{y}-{m}-{d}') }}
              </el-form-item>
              <el-divider></el-divider>
              <el-form-item label="有效期" style="text-align: right;margin:10px 0px;">-</el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
<!--    <h3 style="font-size: 16px; font-weight: bold;">
      基本信息
    </h3>
    <el-form :model="form" label-width="120px" size="mini" label-position="left">

      <el-form-item label="证书编号" style="text-align: right;margin:10px 0px;">{{ form.reportNo }}</el-form-item>
      <el-divider style="margin:5px 0px;"></el-divider>
      <el-form-item label="发布状态" style="text-align: right;margin:10px 0px;">已发布</el-form-item>
      <el-divider></el-divider>
      <el-form-item label="签发日期" style="text-align: right;margin:10px 0px;">
        {{ parseTime(form.signIssueDate, '{y}-{m}-{d}') }}
      </el-form-item>
      <el-divider></el-divider>
      <el-form-item label="有效期" style="text-align: right;margin:10px 0px;">-</el-form-item>
    </el-form>-->
  </el-card>
  <!--  <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>基本信息</span>
      </div>
      <el-form :model="form" label-width="120px" size="mini">

        <el-form-item label="证书编号:">{{form.reportNo}}</el-form-item>
        <el-form-item label="发布状态:">已发布</el-form-item>
        <el-form-item label="签发日期:">{{ parseTime(form.signIssueDate, '{y}-{m}-{d}') }}</el-form-item>
        <el-form-item label="有效期:"></el-form-item>
      </el-form>
      <div class="dialog-footer" style="text-align: right">
        <el-button type="primary" @click="openImg">查看证书</el-button>
      </div>
    </el-card>-->
</template>
<script>
import {getReportView} from "@/api/system/report";

export default {
  name: "CertificateView",
  data() {
    return {
      activeName: 'base',
      form: {
        id: null,
        entrustNo: null,
        reportNo: null,
        entrustPay: null,
        applyProductName: null,
        sampleState: null,
        transport: null,
        applyWeight: null,
        loadingPlace: null,
        flowDirection: null,
        inspectionDate: null,
        signIssueDate: null,
        inspectionDescribe: null,
        mtAr: null,
        madAd: null,
        aAr: null,
        aAd: null,
        adr: null,
        vAr: null,
        vAd: null,
        vdr: null,
        vDaf: null,
        fcAr: null,
        fcAd: null,
        fcDr: null,
        stAr: null,
        stAd: null,
        stDr: null,
        hAr: null,
        hAd: null,
        hdr: null,
        qgrvAr: null,
        qgrvAd: null,
        qgrvDr: null,
        qnetarAr: null,
        cb: null,
        notes: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        certificateImg: null
      },
    }
  },
  created() {
    //console.log("11",this.$route.query.reportId)
    this.getInfo();
  },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: '证书详情'
    }
  },
  methods: {
    sanitizeJson(obj) {
      if (typeof obj === "string") {
        return obj.replace("&lt;", "<",).replace("&gt;", ">",);
      } else if (Array.isArray(obj)) {
        return obj.map(this.sanitizeJson);
      } else if (typeof obj === "object" && obj !== null) {
        return Object.fromEntries(
          Object.entries(obj).map(([key, value]) => [key, this.sanitizeJson(value)])
        );
      }
      return obj;
    },
    // 更新下滑线的位置
     updateUnderline  () {
      if (tabRef.value) {
        const { offsetLeft, offsetWidth } = tabRef.value
        underlineStyle.value = {
          transform: `translateX(${offsetLeft}px)`,
          width: `${offsetWidth}px`
        }
      }
    },
    getInfo() {
      let id = this.$route.query.reportId
      if (id) {
        getReportView({id: this.$route.query.reportId}).then(response => {
          this.form = this.sanitizeJson(response.data);
        });
      }
    },
    openImg() {
      console.log(process.env.VUE_APP_BASE_API + this.form.certificateImg)
      if (this.form.certificateImg) {
        this.$router.push({
          path: '/certificateImage',
          query:{
            certificateImg: this.form.certificateImg,
            reportId: this.$route.query.reportId,
          }
        })
        //window.open(process.env.VUE_APP_BASE_API + this.form.certificateImg, "_self");
      } else {
        this.$modal.msgWarning("暂无证书");
      }
    },
  }
}
</script>
<style lang="scss">
.card-bg {
  min-height: 100vh;
  background: url("../assets/images/cert_detail_bg.png") no-repeat;
  background-size: contain;
  background-position: top;
}

.backTop {
  z-index: 999;
  position: fixed;
  left: 5px;
  top: 10px;
  width: 30px;
}

.logoTop {
  left: 18px;
  top: 18px;
  height: 26px;
}

.el-card {
  .el-card__header {
    border-bottom: 0px !important;
  }
}

.pageTitle {
  margin: 0 0 22px 0;
  font-size: 24px;
  font-weight: bold;
  color: #fff;
  font-family: Microsoft YaHei Regular, Microsoft YaHei !important;
}

.info-card {
  background: url("../assets/images/card_bg.png") no-repeat;
  background-size: contain;
  background-position: 100%;
  background-color: #fff;
}

.el-descriptions__body {
  color: #acacac;
  background-color: unset !important;
}
.el-form-item__label{
  /**color: #616d89;**/
  color: #798092;
}

// scss样式

.no-underline-tabs ::v-deep .el-tabs__header {
  border-bottom: none; /* Remove the bottom border (gray line) */
}

.no-underline-tabs ::v-deep .el-tabs__item.is-active {
  border-color: transparent; /* Ensure no border color when active */
}

.el-tabs__header {
  border-bottom: none !important;
}

/* Remove the border color when active */
.el-tabs__item.is-active {
  border-color: transparent !important;
}
.el-divider {
  background-color: #f0f1f4 !important;
}
.el-divider--horizontal {
  margin: 0 0;
}
.el-card__body {
  padding: 15px !important;
}

.teg{
  color:#fff;
  border: none;
  height: 14px;
  margin: 0px 0px 15px 0px;
  display: flex;
  justify-content: center;
  align-items: center;
  .image{
    height: 14px;
    width: 14px;
    margin-right: 5px;
  }
}
.el-tabs__nav-wrap::after{
  background-color: #ffffff !important;
}
.el-tabs__item{
  font-size: 16px;
  font-weight: bold;

}
.el-tabs__item.is-active {
  color: #000;
}

.el-descriptions__table{
  font-size: 14px;
}
/*.el-descriptions-item {
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
  margin-bottom: 10px;
}*/

 body {
   font-family: Microsoft YaHei Regular, Microsoft YaHei !important;
 }
:root {
    --el-tabs-bottom-bar-width: 40px; /* 修改下划线宽度 */
}
.el-tabs__active-bar {
  width: 30px !important;  /* 修改下滑线宽度 */
  left: 50% !important;    /* 让下划线从标签中间开始 */
  transform: translateX(-50%) !important; /* 水平居中 */
}
label {
   font-weight: 500 !important;
}
</style>
