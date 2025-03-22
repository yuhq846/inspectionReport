package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测报告对象 inspection_report
 * 
 * @author ruoyi
 * @date 2025-01-19
 */
public class InspectionReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 委托编号 */
    @Excel(name = "委托编号")
    private String entrustNo;

    /** 报告编号 */
    @Excel(name = "报告编号")
    private String reportNo;

    /** 委托方 */
    @Excel(name = "委托方")
    private String entrustPay;

    /** 申报品名 */
    @Excel(name = "申报品名")
    private String applyProductName;

    /** 样品状态 */
    @Excel(name = "样品状态")
    private String sampleState;

    /** 运输工具 */
    @Excel(name = "运输工具")
    private String transport;

    /** 申报重量 */
    @Excel(name = "申报重量")
    private String applyWeight;

    /** 装货地点 */
    @Excel(name = "装货地点")
    private String loadingPlace;

    /** 流向 */
    @Excel(name = "流向")
    private String flowDirection;

    /** 检测日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectionDate;

    /** 签发日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签发日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signIssueDate;

    /** 检测说明 */
    @Excel(name = "检测说明")
    private String inspectionDescribe;

    /** 全水分收到基 */
    @Excel(name = "全水分收到基")
    private String mtAr;

    /** 水分空气干燥基 */
    @Excel(name = "水分空气干燥基")
    private String madAd;

    /** 灰分收到基 */
    @Excel(name = "灰分收到基")
    private String aAr;

    /** 灰分空气干燥基 */
    @Excel(name = "灰分空气干燥基")
    private String aAd;

    /** 灰分干燥基 */
    @Excel(name = "灰分干燥基")
    private String aDr;

    /** 挥发分收到基 */
    @Excel(name = "挥发分收到基")
    private String vAr;

    /** 挥发分空气干燥基 */
    @Excel(name = "挥发分空气干燥基")
    private String vAd;

    /** 挥发分干燥基 */
    @Excel(name = "挥发分干燥基")
    private String vDr;

    /** 挥发分干灰基 */
    @Excel(name = "挥发分干灰基")
    private String vDaf;

    /** 固定碳收到基 */
    @Excel(name = "固定碳收到基")
    private String fcAr;

    /** 固定碳空气干燥基 */
    @Excel(name = "固定碳空气干燥基")
    private String fcAd;

    /** 固定碳干燥基 */
    @Excel(name = "固定碳干燥基")
    private String fcDr;

    /** 全硫收到基 */
    @Excel(name = "全硫收到基")
    private String stAr;

    /** 全硫空气干燥基 */
    @Excel(name = "全硫空气干燥基")
    private String stAd;

    /** 全硫干燥基 */
    @Excel(name = "全硫干燥基")
    private String stDr;

    /** 氢收到基 */
    @Excel(name = "氢收到基")
    private String hAr;

    /** 氢空气干燥基 */
    @Excel(name = "氢空气干燥基")
    private String hAd;

    /** 氢干燥基 */
    @Excel(name = "氢干燥基")
    private String hDr;

    /** 高位发热量收到基 */
    @Excel(name = "高位发热量收到基")
    private String qgrvAr;

    /** 高位发热量空气干燥基 */
    @Excel(name = "高位发热量空气干燥基")
    private String qgrvAd;

    /** 高位发热量干燥基 */
    @Excel(name = "高位发热量干燥基")
    private String qgrvDr;

    /** 低位发热量收到基 */
    @Excel(name = "低位发热量收到基")
    private String qnetarAr;

    /** 低位发热量收到基 */
    @Excel(name = "低位发热量收到基")
    private String qnetarkAr;
    /** 焦渣特征 */
    @Excel(name = "焦渣特征")
    private String cb;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    /** 报告图片 */
    private String reportImg;

    /** 证书图片 */
    private String certificateImg;

    /** 采样日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "采样日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date samplingDate;

    /** 采样标准 */
    @Excel(name = "采样标准")
    private String samplingStandard;

    /** 制样标准 */
    @Excel(name = "制样标准")
    private String makeStandard;

    /** 采样天气 */
    @Excel(name = "采样天气")
    private String samplingWeather;

    /** 堆密度收到基 */
    @Excel(name = "堆密度收到基")
    private String dmdAr;

    /** 堆密度干燥基 */
    @Excel(name = "堆密度干燥基")
    private String dmdDr;

    /** 验证报告图片 */
    @Excel(name = "验证报告图片")
    private String verifyImg;

    /** 删除标志 */
    private String delFlag;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEntrustNo(String entrustNo) 
    {
        this.entrustNo = entrustNo;
    }

    public String getEntrustNo() 
    {
        return entrustNo;
    }
    public void setReportNo(String reportNo) 
    {
        this.reportNo = reportNo;
    }

    public String getReportNo() 
    {
        return reportNo;
    }
    public void setEntrustPay(String entrustPay) 
    {
        this.entrustPay = entrustPay;
    }

    public String getEntrustPay() 
    {
        return entrustPay;
    }
    public void setApplyProductName(String applyProductName) 
    {
        this.applyProductName = applyProductName;
    }

    public String getApplyProductName() 
    {
        return applyProductName;
    }
    public void setSampleState(String sampleState) 
    {
        this.sampleState = sampleState;
    }

    public String getSampleState() 
    {
        return sampleState;
    }
    public void setTransport(String transport) 
    {
        this.transport = transport;
    }

    public String getTransport() 
    {
        return transport;
    }
    public void setApplyWeight(String applyWeight) 
    {
        this.applyWeight = applyWeight;
    }

    public String getApplyWeight() 
    {
        return applyWeight;
    }
    public void setLoadingPlace(String loadingPlace) 
    {
        this.loadingPlace = loadingPlace;
    }

    public String getLoadingPlace() 
    {
        return loadingPlace;
    }
    public void setFlowDirection(String flowDirection) 
    {
        this.flowDirection = flowDirection;
    }

    public String getFlowDirection() 
    {
        return flowDirection;
    }
    public void setInspectionDate(Date inspectionDate) 
    {
        this.inspectionDate = inspectionDate;
    }

    public Date getInspectionDate() 
    {
        return inspectionDate;
    }
    public void setSignIssueDate(Date signIssueDate) 
    {
        this.signIssueDate = signIssueDate;
    }

    public Date getSignIssueDate() 
    {
        return signIssueDate;
    }
    public void setInspectionDescribe(String inspectionDescribe) 
    {
        this.inspectionDescribe = inspectionDescribe;
    }

    public String getInspectionDescribe() 
    {
        return inspectionDescribe;
    }
    public void setMtAr(String mtAr) 
    {
        this.mtAr = mtAr;
    }

    public String getMtAr() 
    {
        return mtAr;
    }
    public void setMadAd(String madAd) 
    {
        this.madAd = madAd;
    }

    public String getMadAd() 
    {
        return madAd;
    }
    public void setaAr(String aAr) 
    {
        this.aAr = aAr;
    }

    public String getaAr() 
    {
        return aAr;
    }
    public void setaAd(String aAd) 
    {
        this.aAd = aAd;
    }

    public String getaAd() 
    {
        return aAd;
    }
    public void setADr(String aDr)
    {
        this.aDr = aDr;
    }

    public String getADr()
    {
        return aDr;
    }
    public void setvAr(String vAr) 
    {
        this.vAr = vAr;
    }

    public String getvAr() 
    {
        return vAr;
    }
    public void setvAd(String vAd) 
    {
        this.vAd = vAd;
    }

    public String getvAd() 
    {
        return vAd;
    }
    public void setVDr(String vDr)
    {
        this.vDr = vDr;
    }

    public String getVDr()
    {
        return vDr;
    }
    public void setvDaf(String vDaf) 
    {
        this.vDaf = vDaf;
    }

    public String getvDaf() 
    {
        return vDaf;
    }
    public void setFcAr(String fcAr) 
    {
        this.fcAr = fcAr;
    }

    public String getFcAr() 
    {
        return fcAr;
    }
    public void setFcAd(String fcAd) 
    {
        this.fcAd = fcAd;
    }

    public String getFcAd() 
    {
        return fcAd;
    }
    public void setFcDr(String fcDr)
    {
        this.fcDr = fcDr;
    }

    public String getFcDr()
    {
        return fcDr;
    }
    public void setStAr(String stAr) 
    {
        this.stAr = stAr;
    }

    public String getStAr() 
    {
        return stAr;
    }
    public void setStAd(String stAd) 
    {
        this.stAd = stAd;
    }

    public String getStAd() 
    {
        return stAd;
    }
    public void setStDr(String stDr)
    {
        this.stDr = stDr;
    }

    public String getStDr()
    {
        return stDr;
    }
    public void sethAr(String hAr) 
    {
        this.hAr = hAr;
    }

    public String gethAr() 
    {
        return hAr;
    }
    public void sethAd(String hAd) 
    {
        this.hAd = hAd;
    }

    public String gethAd() 
    {
        return hAd;
    }
    public void setHDr(String hDr)
    {
        this.hDr = hDr;
    }

    public String getHDr()
    {
        return hDr;
    }
    public void setQgrvAr(String qgrvAr) 
    {
        this.qgrvAr = qgrvAr;
    }

    public String getQgrvAr() 
    {
        return qgrvAr;
    }
    public void setQgrvAd(String qgrvAd) 
    {
        this.qgrvAd = qgrvAd;
    }

    public String getQgrvAd() 
    {
        return qgrvAd;
    }
    public void setQgrvDr(String qgrvDr)
    {
        this.qgrvDr = qgrvDr;
    }

    public String getQgrvDr()
    {
        return qgrvDr;
    }
    public void setQnetarAr(String qnetarAr) 
    {
        this.qnetarAr = qnetarAr;
    }

    public String getQnetarAr() 
    {
        return qnetarAr;
    }
    public void setQnetarkAr(String qnetarkAr)
    {
        this.qnetarkAr = qnetarkAr;
    }

    public String getQnetarkAr()
    {
        return qnetarkAr;
    }

    public void setCb(String cb) 
    {
        this.cb = cb;
    }

    public String getCb() 
    {
        return cb;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public void setReportImg(String reportImg)
    {
        this.reportImg = reportImg;
    }

    public String getReportImg()
    {
        return reportImg;
    }
    public void setCertificateImg(String certificateImg)
    {
        this.certificateImg = certificateImg;
    }

    public String getCertificateImg()
    {
        return certificateImg;
    }


    public void setSamplingDate(Date samplingDate)
    {
        this.samplingDate = samplingDate;
    }

    public Date getSamplingDate()
    {
        return samplingDate;
    }
    public void setSamplingStandard(String samplingStandard)
    {
        this.samplingStandard = samplingStandard;
    }

    public String getSamplingStandard()
    {
        return samplingStandard;
    }
    public void setMakeStandard(String makeStandard)
    {
        this.makeStandard = makeStandard;
    }

    public String getMakeStandard()
    {
        return makeStandard;
    }
    public void setSamplingWeather(String samplingWeather)
    {
        this.samplingWeather = samplingWeather;
    }

    public String getSamplingWeather()
    {
        return samplingWeather;
    }
    public void setDmdAr(String dmdAr)
    {
        this.dmdAr = dmdAr;
    }

    public String getDmdAr()
    {
        return dmdAr;
    }
    public void setDmdDr(String dmdDr)
    {
        this.dmdDr = dmdDr;
    }

    public String getDmdDr()
    {
        return dmdDr;
    }
    public void setVerifyImg(String verifyImg)
    {
        this.verifyImg = verifyImg;
    }

    public String getVerifyImg()
    {
        return verifyImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("entrustNo", getEntrustNo())
            .append("reportNo", getReportNo())
            .append("entrustPay", getEntrustPay())
            .append("applyProductName", getApplyProductName())
            .append("sampleState", getSampleState())
            .append("transport", getTransport())
            .append("applyWeight", getApplyWeight())
            .append("loadingPlace", getLoadingPlace())
            .append("flowDirection", getFlowDirection())
            .append("inspectionDate", getInspectionDate())
            .append("signIssueDate", getSignIssueDate())
            .append("inspectionDescribe", getInspectionDescribe())
            .append("mtAr", getMtAr())
            .append("madAd", getMadAd())
            .append("aAr", getaAr())
            .append("aAd", getaAd())
            .append("aDr", getADr())
            .append("vAr", getvAr())
            .append("vAd", getvAd())
            .append("vD", getVDr())
            .append("vDaf", getvDaf())
            .append("fcAr", getFcAr())
            .append("fcAd", getFcAd())
            .append("fcD", getFcDr())
            .append("stAr", getStAr())
            .append("stAd", getStAd())
            .append("stD", getStDr())
            .append("hAr", gethAr())
            .append("hAd", gethAd())
            .append("hD", getHDr())
            .append("qgrvAr", getQgrvAr())
            .append("qgrvAd", getQgrvAd())
            .append("qgrvD", getQgrvDr())
            .append("qnetarAr", getQnetarAr())
            .append("qnetarkAr", getQnetarkAr())
            .append("cb", getCb())
            .append("notes", getNotes())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("reportImg", getReportImg())
            .append("certificateImg", getCertificateImg())
            .append("samplingDate", getSamplingDate())
            .append("samplingStandard", getSamplingStandard())
            .append("makeStandard", getMakeStandard())
            .append("samplingWeather", getSamplingWeather())
            .append("dmdAr", getDmdAr())
            .append("dmdDr", getDmdDr())
            .append("verifyImg", getVerifyImg())
            .toString();
    }
}
