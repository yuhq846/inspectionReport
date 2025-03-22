package com.ruoyi.system.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.InspectionReport;

/**
 * 检测报告Service接口
 * 
 * @author ruoyi
 * @date 2025-01-19
 */
public interface IInspectionReportService 
{
    /**
     * 查询检测报告
     * 
     * @param id 检测报告主键
     * @return 检测报告
     */
    public InspectionReport selectInspectionReportById(Long id);

    /**
     * 查询检测报告列表
     * 
     * @param inspectionReport 检测报告
     * @return 检测报告集合
     */
    public List<InspectionReport> selectInspectionReportList(InspectionReport inspectionReport);

    /**
     * 新增检测报告
     * 
     * @param inspectionReport 检测报告
     * @return 结果
     */
    public int insertInspectionReport(InspectionReport inspectionReport);

    /**
     * 修改检测报告
     * 
     * @param inspectionReport 检测报告
     * @return 结果
     */
    public int updateInspectionReport(InspectionReport inspectionReport);

    /**
     * 批量删除检测报告
     * 
     * @param ids 需要删除的检测报告主键集合
     * @return 结果
     */
    public int deleteInspectionReportByIds(Long[] ids);

    /**
     * 删除检测报告信息
     * 
     * @param id 检测报告主键
     * @return 结果
     */
    public int deleteInspectionReportById(Long id);

    File generateReportQrcode(Long id);

    File generateCertificateQrcode(Long id);

    File generateVerifyQrcode(Long id);

    File generateImg(Long id);


    File createFile(Map<String, Object> dataMap) throws Exception;
}
