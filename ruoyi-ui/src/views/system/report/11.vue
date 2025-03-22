<script setup>

</script>

<template>
  <div class="el-table el-table--enable-row-hover el-table--medium">
    <table cellspacing="0" style="width: 100%;">
      <thead>
      <tr>
        <th class="el-table__cell el-table__cell is-leaf"><div class="cell">检测项目</div></th>
        <th class="el-table__cell is-leaf"><div class="cell">单位</div></th>
        <th class="el-table__cell is-leaf"><div class="cell">收到基</div></th>
        <th class="el-table__cell is-leaf"><div class="cell">空气干燥基</div></th>
        <th class="el-table__cell is-leaf"><div class="cell">干燥基</div></th>
        <th class="el-table__cell is-leaf"><div class="cell">干燥无灰基</div></th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">全水分</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="mtAr">
          <el-input-number v-model="form.mtAr" placeholder="请输入全水分收到基"></el-input-number>
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">水分</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="madAd">
          <el-input-number v-model="form.madAd" placeholder="请输入水分空气干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">灰分</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="aAr">
          <el-input-number v-model="form.aAr" placeholder="请输入灰分收到基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="aAd">
          <el-input-number v-model="form.aAd" placeholder="请输入灰分空气干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="adr">
          <el-input-number v-model="form.adr" placeholder="请输入灰分干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">挥发分</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="vAr">
          <el-input-number v-model="form.vAr" placeholder="请输入挥发分收到基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="vAd">
          <el-input-number v-model="form.vAd" placeholder="请输入挥发分空气干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="vdr">
          <el-input-number v-model="form.vdr" placeholder="请输入挥发分干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="vDaf">
          <el-input-number v-model="form.vDaf" placeholder="请输入挥发分干灰基" />
        </el-form-item></div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">焦渣特征</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
        <td class="el-table__cell is-leaf" colspan="4"><div class="cell"><el-form-item label="" prop="cb">
          <el-input-number v-model="form.cb" placeholder="请输入焦渣特征" />
        </el-form-item></div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">固定碳</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="fcAr">
          <el-input-number v-model="form.fcAr" placeholder="请输入固定碳收到基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="fcAd">
          <el-input-number v-model="form.fcAd" placeholder="请输入固定碳空气干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="fcDr">
          <el-input-number v-model="form.fcDr" placeholder="请输入固定碳干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">全硫</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="stAr">
          <el-input-number v-model="form.stAr" placeholder="请输入全硫收到基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="stAd">
          <el-input-number v-model="form.stAd" placeholder="请输入全硫空气干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="stDr">
          <el-input-number v-model="form.stDr" placeholder="请输入全硫干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">氢</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="hAr">
          <el-input-number v-model="form.hAr" placeholder="请输入氢收到基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="hAd">
          <el-input-number v-model="form.hAd" placeholder="请输入氢空气干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="hdr">
          <el-input-number v-model="form.hdr" placeholder="请输入氢干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">高位发热量</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">MJ/kg</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="qgrvAr">
          <el-input-number v-model="form.qgrvAr" placeholder="请输入高位发热量收到基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="qgrvAd">
          <el-input-number v-model="form.qgrvAd" placeholder="请输入高位发热量空气干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="qgrvDr">
          <el-input-number v-model="form.qgrvDr" placeholder="请输入高位发热量干燥基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      <tr>
        <td class="el-table__cell is-leaf"><div class="cell">低位发热量</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">MJ/kg</div></td>
        <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="qnetarAr">
          <el-input-number v-model="form.qnetarAr" placeholder="请输入低位发热量收到基" />
        </el-form-item></div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
        <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped lang="scss">

</style>
