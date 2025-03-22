<template>
  <table class="viewTable">
    <tbody>
    <tr>
      <td>委托单位</td>
      <td>{{ form.entrustPay }}</td>
    </tr>
    <tr>
      <td>流向</td>
      <td>{{ form.flowDirection }}</td>
    </tr>
    <tr>
      <td>商品名称</td>
      <td>{{ form.applyProductName }}</td>
    </tr>
    <tr>
      <td>重量</td>
      <td>{{ form.applyWeight }}</td>
    </tr>
    <tr>
      <td>运输工具</td>
      <td>{{ form.transport }}</td>
    </tr>
    <tr>
      <td>装货地点</td>
      <td>{{ form.loadingPlace }}</td>
    </tr>
    <tr>
      <td>全水收到基</td>
      <td>{{ form.mtAr }}%</td>
    </tr>
    <tr>
      <td>空干基水分</td>
      <td>{{ form.madAd }}%</td>
    </tr>
    <tr>
      <td>灰分收到基</td>
      <td>{{ form.aAr }}%</td>
    </tr>
    <tr>
      <td>灰分空干基</td>
      <td>{{ form.aAd }}%</td>
    </tr>
    <tr>
      <td>灰分干基</td>
      <td>{{ form.adr }}%</td>
    </tr>
    <tr>
      <td>挥发分收到基</td>
      <td>{{ form.vAr }}%</td>
    </tr>
    <tr>
      <td>挥发分空干基</td>
      <td>{{ form.vAd }}%</td>
    </tr>
    <tr>
      <td>挥发分干基</td>
      <td>{{ form.vdr }}%</td>
    </tr>
    <tr>
      <td>挥发分干燥无灰基</td>
      <td>{{ form.vDaf }}%</td>
    </tr>
    <tr>
      <td>固定碳收到基</td>
      <td>{{ form.fcAr }}%</td>
    </tr>
    <tr>
      <td>固定碳空干基</td>
      <td>{{ form.fcAd }}%</td>
    </tr>
    <tr>
      <td>固定碳干基</td>
      <td>{{ form.fcDr }}%</td>
    </tr>
    <tr>
      <td>硫分收到基</td>
      <td>{{ form.stAr }}%</td>
    </tr>
    <tr>
      <td>硫分空干基</td>
      <td>{{ form.stAd }}%</td>
    </tr>
    <tr>
      <td>硫分干基</td>
      <td>{{ form.stDr }}%</td>
    </tr>
    <tr>
      <td>氢收到基</td>
      <td>{{ form.hAr }}%</td>
    </tr>
    <tr>
      <td>氢空干基</td>
      <td>{{ form.hAd }}%</td>
    </tr>
    <tr>
      <td>氢干基</td>
      <td>{{ form.hdr }}%</td>
    </tr>
    <tr>
      <td>高位发热量收到基</td>
      <td>{{ form.qgrvAr }}MJ/kg</td>
    </tr>
    <tr>
      <td>高位发热量空干基</td>
      <td>{{ form.qgrvAd }}MJ/kg</td>
    </tr>
    <tr>
      <td>高位发热量干基</td>
      <td>{{ form.qgrvDr }}MJ/kg</td>
    </tr>
    <tr>
      <td>低位发热量收到基(MJ/kg)</td>
      <td>{{ form.qnetarAr }}MJ/kg</td>
    </tr>
    <tr>
      <td>低位发热量收到基(kcal/kg)</td>
      <td>{{ form.qnetarkAr }}Kcal/kg</td>
    </tr>
    <tr>
      <td>日期</td>
      <td>{{ parseTime(form.inspectionDate, '{y}年{m}月{d}日') }}</td>
    </tr>
    </tbody>
  </table>
<!--  <el-form :model="form" label-position="left" label-width="120px" size="mini" style="margin-left:10px;">
    &lt;!&ndash;    <el-form-item label="委托编号">{{form.entrustNo}}</el-form-item>
        <el-form-item label="报告编号">{{form.reportNo}}</el-form-item>&ndash;&gt;
    <el-form-item label="委托单位">{{ form.entrustPay }}</el-form-item>
    <el-form-item label="流向">{{ form.flowDirection }}</el-form-item>
    <el-form-item label="商品名称">{{ form.applyProductName }}</el-form-item>
    <el-form-item label="重量">{{ form.applyWeight }}</el-form-item>
    <el-form-item label="运输工具">{{ form.transport }}</el-form-item>
    &lt;!&ndash;    <el-form-item label="样品状态">{{form.sampleState}}</el-form-item>&ndash;&gt;
    <el-form-item label="装货地点">{{ form.loadingPlace }}</el-form-item>
    &lt;!&ndash;<el-form-item label="签发日期:">{{ parseTime(form.signIssueDate, '{y}年{m}月{d}日') }}</el-form-item>&ndash;&gt;
    &lt;!&ndash;    <el-form-item label="检测说明:">{{form.inspectionDescribe}}</el-form-item>&ndash;&gt;
    <el-form-item label="全水收到基">{{ form.mtAr }}%</el-form-item>
    <el-form-item label="水分空气干燥基">{{ form.madAd }}%</el-form-item>
    <el-form-item label="灰分收到基">{{ form.aAr }}%</el-form-item>
    <el-form-item label="灰分空气干燥基">{{ form.aAd }}%</el-form-item>
    <el-form-item label="灰分干燥基">{{ form.adr }}%</el-form-item>
    <el-form-item label="挥发分收到基">{{ form.vAr }}%</el-form-item>
    <el-form-item label="挥发分空气干燥基">{{ form.vAd }}%</el-form-item>
    <el-form-item label="挥发分干燥基">{{ form.vdr }}%</el-form-item>
    <el-form-item label="固定碳收到基">{{ form.fcAr }}%</el-form-item>
    <el-form-item label="固定碳空气干燥基">{{ form.fcAd }}%</el-form-item>
    <el-form-item label="固定碳干燥基">{{ form.fcDr }}%</el-form-item>
    <el-form-item label="全硫收到基">{{ form.stAr }}%</el-form-item>
    <el-form-item label="全硫空气干燥基">{{ form.stAd }}%</el-form-item>
    <el-form-item label="全硫干燥基">{{ form.stDr }}%</el-form-item>
    <el-form-item label="氢收到基">{{ form.hAr }}%</el-form-item>
    <el-form-item label="氢空气干燥基">{{ form.hAd }}%</el-form-item>
    <el-form-item label="氢干燥基">{{ form.hdr }}%</el-form-item>
    <el-form-item label="高位发热量收到基">{{ form.qgrvAr }}MJ/kg</el-form-item>
    <el-form-item label="高位发热量空气干燥基">{{ form.qgrvAd }}MJ/kg</el-form-item>
    <el-form-item label="高位发热量干燥基">{{ form.qgrvDr }}MJ/kg</el-form-item>
    <el-form-item label="低位发热量收到基">{{ form.qnetarAr }}MJ/kg</el-form-item>
    <el-form-item label="低位发热量收到基">{{ form.qnetarKAr }}Kcal/kg</el-form-item>
    &lt;!&ndash;    <el-form-item label="焦渣特征">{{form.cb}}%</el-form-item>&ndash;&gt;
    &lt;!&ndash;    <el-form-item label="备注:">{{form.notes}}</el-form-item>&ndash;&gt;
    <el-form-item label="日期">{{ parseTime(form.inspectionDate, '{y}年{m}月{d}日') }}</el-form-item>
  </el-form>-->
</template>
<script>
import {getReport, getReportView} from "@/api/system/report";

export default {
  name: "ReportView",
  data() {
    return {
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
    //console.log("11",this.$route.query.reportId)
    this.getInfo();
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
        });
      }
    },
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

</style>
