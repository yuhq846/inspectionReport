<template>
  <div>
  <div>
<!--    <p style="font-size: 50px;"></p>-->
    <a  :href="path" id="zs-img" style="font-size: 18px;" :download="fileName">点此下载</a>
  </div>
  <div style="margin-top: 10px">
    <table style="font-size: 8px;text-align: center;">
      <tbody>
      <tr>
        <td>委托方<sup>#</sup></td>
        <td>{{ form.entrustPay }}</td>
      </tr>
      <tr>
        <td>运输工具<sup>#</sup></td>
        <td>{{ form.transport }}</td>
      </tr>
      <tr>
        <td>采样日期</td>
        <td>{{ form.samplingDate| timeFormat() }}</td>
      </tr>
      <tr>
        <td>品名<sup>#</sup></td>
        <td>{{ form.applyProductName }}</td>
      </tr>
      <tr>
        <td>样品状态</td>
        <td>{{ form.sampleState }}</td>
      </tr>
      <tr>
        <td>重量<sup>#</sup></td>
        <td>{{ form.applyWeight }}</td>
      </tr>
      <tr>
        <td>采样天气</td>
        <td>{{ form.samplingWeather }}</td>
      </tr>
      <tr>
        <td>采样地点</td>
        <td>{{ form.loadingPlace }}</td>
      </tr>
      <tr>
        <td>制样标准</td>
        <td>{{ form.makeStandard }}</td>
      </tr>
      <tr>
        <td>采样标准</td>
        <td>{{ form.samplingStandard }}</td>
      </tr>
      <tr>
        <td>检测日期</td>
        <td>{{form.inspectionDate| timeFormat()}}</td>
      </tr>
      <!-- 如果证书上只做有害元素、全水、水分的话，就只显示一页，如只做全水+磷，那么只有有害的证书一页。 -->
      <!-- 1常规模板检验项目 -->
      <tr>
        <td>收到基全水分</td>
        <td>{{ form.mtAr }}%</td>
      </tr>
      <tr>
        <td>空气干燥基水分</td>
        <td>{{ form.madAd }}%</td>
      </tr>
      <tr>
        <td>收到基灰分</td>
        <td>{{ form.aAr }}%</td>
      </tr>
      <tr>
        <td>空气干燥基灰分</td>
        <td>{{ form.aAd }}%</td>
      </tr>
      <tr>
        <td>干燥基灰分</td>
        <td>{{ form.adr }}%</td>
      </tr>
      <tr>
        <td>收到基挥发分</td>
        <td>{{ form.vAr }}%</td>
      </tr>
      <tr>
        <td>空气干燥基挥发分</td>
        <td>{{ form.vAd }}%</td>
      </tr>
      <tr>
        <td>干燥基挥发分</td>
        <td>{{ form.vdr }}%</td>
      </tr>
      <tr>
        <td>干燥无灰基挥发分</td>
        <td>{{ form.vDaf }}%</td>
      </tr>
      <tr>
        <td>收到基固定碳</td>
        <td>{{ form.fcAr }}%</td>
      </tr>
      <tr>
        <td>空气干燥基固定碳</td>
        <td>{{ form.fcAd }}%</td>
      </tr>
      <tr>
        <td>干燥基固定碳</td>
        <td>{{ form.fcDr }}%</td>
      </tr>
      <tr>
        <td>干燥无灰基固定碳</td>
        <td>/</td>
      </tr>
      <tr>
        <td>收到基全硫</td>
        <td>{{ form.stAr }}%</td>
      </tr>
      <tr>
        <td>空气干燥基全硫</td>
        <td>{{ form.stAd }}%</td>
      </tr>
      <tr>
        <td>干燥基全硫</td>
        <td>{{ form.stDr }}%</td>
      </tr>
      <tr>
        <td>收到基氢</td>
        <td>{{ form.hAr }}%</td>
      </tr>
      <tr>
        <td>空气干燥基氢</td>
        <td>{{ form.hAd }}%</td>
      </tr>
      <tr>
        <td>干燥基氢</td>
        <td>{{ form.hdr }}%</td>
      </tr>
      <tr>
        <td>收到基高位发热量</td>
        <td>{{ form.qgrvAr }}MJ/kg</td>
      </tr>
      <tr>
        <td>空气干燥基高位发热量</td>
        <td>{{ form.qgrvAd }}MJ/kg</td>
      </tr>
      <tr>
        <td>干燥基高位发热量</td>
        <td>{{ form.qgrvDr }}MJ/kg</td>
      </tr>
      <tr>
        <td>收到基低位发热量（MJ/kg）</td>
        <td>{{ form.qnetarAr }}MJ/kg</td>
      </tr>
      <tr>
        <td>收到基低位发热量（kcal/kg）</td>
        <td>{{ form.qnetarkAr }}kcal/kg</td>
      </tr>
      <tr>
        <td>收到基堆密度</td>
        <td>{{ form.dmdAr }}t/m3</td>
      </tr>
      <tr>
        <td>干燥基堆密度</td>
        <td>{{ form.dmdDr }}t/m3</td>
      </tr>
      <!-- 2有害检验项目 -->
      <!-- 3粒度分析 -->
      </tbody>
    </table>
  </div>
  </div>
</template>
<script>
import {getReportView} from "@/api/system/report";
import { ref, inject } from 'vue'

export default {
  name: "checkView",
  data() {
    return {
      path:'',
      fileName:'',
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
        qnetarKAr: null,
        cb: null,
        notes: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
    }
  },
  created() {
    this.getInfo();
  },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: '查看证书'
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
    getInfo() {
      let id = this.$route.query.reportId
      if (id) {
        getReportView({id: this.$route.query.reportId}).then(response => {
          this.form = this.sanitizeJson(response.data);
          this.fileName=this.form.verifyImg.split("/")[this.form.verifyImg.split("/").length-1]
          console.log("fileName",this.fileName)
          this.path=process.env.VUE_APP_BASE_API +this.form.verifyImg
        });
      }
    },
    downfile() {
      console.log(12212,this.path)
      this.download(this.form.verifyImg, {}, `Verify_${this.form.entrustNo}.png`)
      //window.location.href=this.path
    }
  }
}
</script>
<style lang="scss">
.viewTable {
  display: table;
  border-collapse: separate;
  box-sizing: border-box;
  text-indent: initial;
  unicode-bidi: isolate;
  line-height: normal;
  font-weight: normal;
  font-size: medium;
  font-style: normal;
  color: -internal-quirk-inherit;
  text-align: start;
  border-spacing: 2px;
  border-color: gray;
  white-space: normal;
  font-variant: normal;
  font-size: 8px;
  tbody {
    display: table-row-group;
    vertical-align: middle;
    unicode-bidi: isolate;
    border-color: inherit;
  }
  tr {
    display: table-row;
    vertical-align: inherit;
    unicode-bidi: isolate;
    border-color: inherit;
  }
  td {
    display: table-cell;
    vertical-align: inherit;
    unicode-bidi: isolate;
  }
}
#zs-img {
  position: relative;
  width: 100%;
}
a:-webkit-any-link {
  color: -webkit-link;
  cursor: pointer;
  text-decoration: underline;
}
</style>
