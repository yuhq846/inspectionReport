<script setup>

</script>

<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <el-button icon="el-icon-arrow-left" circle></el-button>
      <span style="margin-left: 10px; font-size: 18px; font-weight: bold;">中险在线</span>
    </div>
    <h2 style="font-size: 18px; font-weight: bold;">证书/报告信息</h2>
    <el-card shadow="always" class="info-card">
      <p><strong>证书编号:</strong> HB04241200574</p>
      <p><strong>签发日期:</strong> 2024-12-31</p>
      <el-button type="success" plain size="small">查看证书</el-button>
    </el-card>
    <el-divider></el-divider>
    <h3 style="font-size: 16px; font-weight: bold;">基本信息</h3>
    <el-descriptions :column="1" border>
      <el-descriptions-item label="证书编号">HB04241200574</el-descriptions-item>
      <el-descriptions-item label="发布状态">已发布</el-descriptions-item>
      <el-descriptions-item label="签发日期">2024-12-31</el-descriptions-item>
      <el-descriptions-item label="有效期">-</el-descriptions-item>
    </el-descriptions>
  </el-card>
</template>

<style scoped lang="scss">

</style>
