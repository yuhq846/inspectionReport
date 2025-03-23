<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="委托编号" prop="entrustNo">
        <el-input
          v-model="queryParams.entrustNo"
          placeholder="请输入委托编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报告编号" prop="reportNo">
        <el-input
          v-model="queryParams.reportNo"
          placeholder="请输入报告编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="委托方" prop="entrustPay">
        <el-input
          v-model="queryParams.entrustPay"
          placeholder="请输入委托方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申报品名" prop="applyProductName">
        <el-input
          v-model="queryParams.applyProductName"
          placeholder="请输入申报品名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:report:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:report:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:report:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:report:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="委托编号" align="center" prop="entrustNo" />
      <el-table-column label="报告编号" align="center" prop="reportNo" />
      <el-table-column label="委托方" align="center" prop="entrustPay" />
      <el-table-column label="申报品名" align="center" prop="applyProductName" />
      <el-table-column label="样品状态" align="center" prop="sampleState" />
      <el-table-column label="运输工具" align="center" prop="transport" />
      <el-table-column label="申报重量" align="center" prop="applyWeight" />
      <el-table-column label="装货地点" align="center" prop="loadingPlace" />
      <el-table-column label="流向" align="center" prop="flowDirection" />
      <el-table-column label="检测日期" align="center" prop="inspectionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectionDate, '{y}年{m}月{d}日') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="签发日期" align="center" prop="signIssueDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signIssueDate, '{y}年{m}月{d}日') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:report:edit']"
          >修改</el-button>
          <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)" v-hasPermi="['system:report:query','system:report:remove']">
            <el-button size="mini" type="text" icon="el-icon-d-arrow-right">更多</el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="handleView" icon="el-icon-view" v-hasPermi="['system:report:query']">详情</el-dropdown-item>
              <el-dropdown-item command="handleReportQrcode" icon="el-icon-circle-check" v-hasPermi="['system:report:query']">生成报告二维码</el-dropdown-item>
              <el-dropdown-item command="handleCertificateQrcode" icon="el-icon-circle-check" v-hasPermi="['system:report:query']">生成证书二维码</el-dropdown-item>
              <el-dropdown-item command="handleVerifyQrcode" icon="el-icon-circle-check" v-hasPermi="['system:report:query']">生成检测二维码</el-dropdown-item>
              <el-dropdown-item command="handleDelete" icon="el-icon-delete" v-hasPermi="['system:report:remove']">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <!--          <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-view"
                      @click="handleView(scope.row)"
                      v-hasPermi="['system:report:query']"
                    >详情</el-button>
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-view"
                      @click="handleGenerateImg(scope.row)"
                      v-hasPermi="['system:report:query']"
                    >生成</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 检测报告详细 -->
    <el-dialog title="检测报告详细" :visible.sync="view" width="1000px" append-to-body>
      <el-form ref="form" :model="form" label-width="120px" size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label="委托编号:" >{{form.entrustNo}}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报告编号:" >{{form.reportNo}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="委托方:">{{form.entrustPay}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="申报品名:">{{form.applyProductName}}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="样品状态:">{{form.sampleState}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="运输工具:">{{form.transport}}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申报重量:">{{form.applyWeight}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="装货地点:">{{form.loadingPlace}}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="流向:">{{form.flowDirection}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检测日期:">{{ parseTime(form.inspectionDate, '{y}年{m}月{d}日') }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="签发日期:">{{ parseTime(form.signIssueDate, '{y}年{m}月{d}日') }}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="采样日期:">{{ parseTime(form.samplingDate, '{y}年{m}月{d}日') }}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采样标准:">{{form.samplingStandard}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="制样标准:">{{form.makeStandard}}</el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采样天气:">{{form.samplingWeather}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="检测说明:">{{form.inspectionDescribe}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注:" prop="notes">{{form.notes}}</el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="报告图片:"><image-upload v-model="form.reportImg" :limit=1 :disabled="true" :isShowTip="false"/></el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证书图片:"><image-upload v-model="form.certificateImg" :limit=1  :disabled="true" :isShowTip="false"/></el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检验图片:"><image-upload v-model="form.verifyImg" :limit=1  :disabled="true" :isShowTip="false"/></el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检验PDF:"><file-upload v-model="form.verifyPdf" :fileType="fileType" :limit=1  :disabled="true" :isShowTip="false"/></el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
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
                  <td class="el-table__cell is-leaf"><div class="cell">{{ form.madAd }}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">水分</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{ form.madAd }}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">灰分</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.aAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.aAd}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.adr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">挥发分</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.vAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.vAd}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.vdr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.vDaf}}</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">焦渣特征</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf" colspan="4"><div class="cell">{{form.cb}}</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">固定碳</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.fcAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.fcAd}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.fcDr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">全硫</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.stAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.stAd}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.stDr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">氢</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">%</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.hAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.hAd}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.hdr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">高位发热量</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">MJ/kg</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.qgrvAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.qgrvAd}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.qgrvDr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">低位发热量</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">MJ/kg</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.qnetarAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">低位发热量</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">Kcal/kg</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.qnetarkAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">堆密度</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">t/m3</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.dmdAr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">{{form.dmdDr}}</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                </tbody>
              </table>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="view = false">关 闭</el-button>
      </div>
    </el-dialog>
    <!-- 添加或修改检测报告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules"  >
        <el-row>
          <el-col :span="12">
            <el-form-item label="委托编号" prop="entrustNo" label-width="130px">
              <el-input v-model="form.entrustNo" placeholder="请输入委托编号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="报告编号" prop="reportNo" label-width="130px">
              <el-input v-model="form.reportNo" placeholder="请输入报告编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="委托方" prop="entrustPay" label-width="130px">
              <el-input v-model="form.entrustPay" placeholder="请输入委托方" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="申报品名" prop="applyProductName" label-width="130px">
              <el-input v-model="form.applyProductName" placeholder="请输入申报品名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="样品状态" prop="sampleState" label-width="130px">
              <el-input v-model="form.sampleState" placeholder="请输入样品状态" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="运输工具" prop="transport" label-width="130px">
              <el-input v-model="form.transport" placeholder="请输入运输工具" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="申报重量" prop="applyWeight" label-width="130px">
              <el-input v-model="form.applyWeight" placeholder="请输入申报重量" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="装货地点" prop="loadingPlace" label-width="130px">
              <el-input v-model="form.loadingPlace" placeholder="请输入装货地点" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="流向" prop="flowDirection" label-width="130px">
              <el-input v-model="form.flowDirection" placeholder="请输入流向" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检测日期" prop="inspectionDate" label-width="130px">
              <el-date-picker clearable
                              v-model="form.inspectionDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择检测日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="签发日期" prop="signIssueDate" label-width="130px">
              <el-date-picker clearable
                              v-model="form.signIssueDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择签发日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="采样日期" prop="samplingDate" label-width="130px">
              <el-date-picker clearable
                              v-model="form.samplingDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择采样日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采样标准" prop="samplingStandard" label-width="130px">
              <el-input v-model="form.samplingStandard" placeholder="请输入采样标准" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="制样标准" prop="makeStandard" label-width="130px">
              <el-input v-model="form.makeStandard" placeholder="请输入制样标准" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采样天气" prop="samplingWeather" label-width="130px">
              <el-input v-model="form.samplingWeather" placeholder="请输入采样天气" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="检测说明" prop="inspectionDescribe" label-width="130px">
              <el-input v-model="form.inspectionDescribe" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注" prop="notes" label-width="130px">
              <el-input v-model="form.notes" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="报告图片" prop="reportImg" label-width="130px" >
              <image-upload v-model="form.reportImg" :limit="1"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证书图片" prop="certificateImg" label-width="130px">
              <image-upload v-model="form.certificateImg" :limit="1"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="检测图片" prop="verifyImg" label-width="130px">
              <image-upload v-model="form.verifyImg" :limit="1"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="检测PDF" prop="verifyPdf" label-width="130px">
              <file-upload v-model="form.verifyPdf" :fileType="fileType" :limit="1"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
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
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">低位发热量</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">Kcal/kg</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="qnetarkAr">
                    <el-input-number v-model="form.qnetarkAr" placeholder="请输入低位发热量收到基" />
                  </el-form-item></div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                <tr>
                  <td class="el-table__cell is-leaf"><div class="cell">堆密度</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">t/m3</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="qnetarkAr">
                    <el-input-number v-model="form.dmdAr" placeholder="请输入堆密度收到基" />
                  </el-form-item></div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                  <td class="el-table__cell is-leaf"><div class="cell"><el-form-item label="" prop="qnetarkAr">
                    <el-input-number v-model="form.dmdDr" placeholder="请输入堆密度干燥基" />
                  </el-form-item></div></td>
                  <td class="el-table__cell is-leaf"><div class="cell">/</div></td>
                </tr>
                </tbody>
              </table>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/system/report";

export default {
  name: "Report",
  data() {
    return {
      fileType:['pdf'],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 检测报告表格数据
      reportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情弹框
      view: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        qnetarkAr: null,
        cb: null,
        notes: null,
        samplingDate: null,
        samplingStandard: null,
        makeStandard: null,
        samplingWeather: null,
        dmdAr: null,
        dmdDr: null,
        verifyImg: null
      },
      // 表单参数
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
        qnetarkAr: null,
        cb: null,
        notes: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        reportImg: null,
        certificateImg: null,
        samplingDate: null,
        samplingStandard: null,
        makeStandard: null,
        samplingWeather: null,
        dmdAr: null,
        dmdDr: null,
        verifyImg: null,
        verifyPdf: null
      },
      // 表单校验
      rules: {
        entrustNo: [
          { required: true, message: "委托编号不能为空", trigger: "blur" }
        ],
        reportNo: [
          { required: true, message: "报告编号不能为空", trigger: "blur" }
        ],
        entrustPay: [
          { required: true, message: "委托方不能为空", trigger: "blur" }
        ],
        applyProductName: [
          { required: true, message: "申报品名不能为空", trigger: "blur" }
        ],
        sampleState: [
          { required: true, message: "样品状态不能为空", trigger: "blur" }
        ],
        transport: [
          { required: true, message: "运输工具不能为空", trigger: "blur" }
        ],
        applyWeight: [
          { required: true, message: "申报重量不能为空", trigger: "blur" }
        ],
        loadingPlace: [
          { required: true, message: "装货地点不能为空", trigger: "blur" }
        ],
        flowDirection: [
          { required: true, message: "流向不能为空", trigger: "blur" }
        ],
        inspectionDate: [
          { required: true, message: "检测日期不能为空", trigger: "blur" }
        ],
        signIssueDate: [
          { required: true, message: "签发日期不能为空", trigger: "blur" }
        ],
        samplingDate: [
          { required: true, message: "采样日期不能为空", trigger: "blur" }
        ],
        samplingStandard: [
          { required: true, message: "采样标准不能为空", trigger: "blur" }
        ],
        makeStandard: [
          { required: true, message: "制样标准不能为空", trigger: "blur" }
        ],
        samplingWeather: [
          { required: true, message: "采样天气不能为空", trigger: "blur" }
        ],
        inspectionDescribe: [
          { required: true, message: "检测说明不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    sanitizeJson(obj) {
      if (typeof obj === "string") {
        return obj.replace("&lt;","<", ).replace("&gt;",">", );
      } else if (Array.isArray(obj)) {
        return obj.map(this.sanitizeJson);
      } else if (typeof obj === "object" && obj !== null) {
        return Object.fromEntries(
          Object.entries(obj).map(([key, value]) => [key, this.sanitizeJson(value)])
        );
      }
      return obj;
    },
    /** 查询检测报告列表 */
    getList() {
      this.loading = true;
      listReport(this.queryParams).then(response => {
        this.reportList = this.sanitizeJson(response.rows);
        this.total = response.total;
        this.loading = false;
      });
    },
    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleView":
          this.handleView(row);
          break;
        case "handleReportQrcode":
          this.handleReportQrcode(row);
          break;
        case "handleCertificateQrcode":
          this.handleCertificateQrcode(row);
          break;
        case "handleVerifyQrcode":
          this.handleVerifyQrcode(row);
          break;
        case "handleGenerateImg":
          this.handleGenerateImg(row);
          break;
        case "handleDelete":
          this.handleDelete(row);
          break;
        default:
          break;
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
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
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加检测报告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReport(id).then(response => {
        this.form = this.sanitizeJson(response.data);
        this.open = true;
        this.title = "修改检测报告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            console.log("form",this.form)
            updateReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReport(this.form).then(response => {
              this.$modal.msg("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 任务详细信息 */
    handleView(row) {
      const id = row.id || this.ids
      getReport(id).then(response => {
        this.form = this.sanitizeJson(response.data);
        this.view = true;
        this.title = "修改检测报告";
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除检测报告编号为"' + ids + '"的数据项？').then(function() {
        return delReport(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 生成报告二维码按钮操作 */
    handleReportQrcode(row) {
      const id = row.id || this.ids
      this.download('system/report/generateReportQrcode/'+id, {}, `report_${row.entrustNo}.png`)
    },
    /** 生成证书二维码按钮操作 */
    handleCertificateQrcode(row) {
      const id = row.id || this.ids
      this.download('system/report/generateCertificateQrcode/'+id, {}, `certificate_${row.entrustNo}.png`)
    },
    /** 生成证书二维码按钮操作 */
    handleVerifyQrcode(row) {
      const id = row.id || this.ids
      this.download('system/report/generateVerifyQrcode/'+id, {}, `Verify_${row.entrustNo}.png`)
    },
    /** 生成按钮操作 */
    handleGenerateImg(row) {
      const id = row.id || this.ids
      this.download('system/report/generateImg/'+id, {}, `report_${row.entrustNo}.png`)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/report/export', {
        ...this.queryParams
      }, `report_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.cell{
  text-align: center;
}
</style>

