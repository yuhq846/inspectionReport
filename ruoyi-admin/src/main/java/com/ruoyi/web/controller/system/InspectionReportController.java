package com.ruoyi.web.controller.system;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.InspectionReport;
import com.ruoyi.system.service.IInspectionReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测报告Controller
 * 
 * @author ruoyi
 * @date 2025-01-19
 */
@RestController
@RequestMapping("/system/report")
public class InspectionReportController extends BaseController
{
    @Autowired
    private IInspectionReportService inspectionReportService;

    /**
     * 查询检测报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(InspectionReport inspectionReport)
    {
        startPage();
        List<InspectionReport> list = inspectionReportService.selectInspectionReportList(inspectionReport);
        return getDataTable(list);
    }

    /**
     * 导出检测报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:report:export')")
    @Log(title = "检测报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InspectionReport inspectionReport)
    {
        List<InspectionReport> list = inspectionReportService.selectInspectionReportList(inspectionReport);
        ExcelUtil<InspectionReport> util = new ExcelUtil<InspectionReport>(InspectionReport.class);
        util.exportExcel(response, list, "检测报告数据");
    }

    /**
     * 获取检测报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inspectionReportService.selectInspectionReportById(id));
    }
    /**
     * 获取检测报告详细信息
     */
    @GetMapping(value = "/view")
    public AjaxResult getViewInfo(@RequestParam("id") Long id)
    {
        return success(inspectionReportService.selectInspectionReportById(id));
    }

    /**
     * 新增检测报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:add')")
    @Log(title = "检测报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InspectionReport inspectionReport)
    {
        return toAjax(inspectionReportService.insertInspectionReport(inspectionReport));
    }

    /**
     * 修改检测报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:edit')")
    @Log(title = "检测报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InspectionReport inspectionReport)
    {
        return toAjax(inspectionReportService.updateInspectionReport(inspectionReport));
    }

    /**
     * 删除检测报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:remove')")
    @Log(title = "检测报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(inspectionReportService.deleteInspectionReportByIds(ids));
    }

    /**
     * 生成报告二维码
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @Log(title = "生成报告二维码", businessType = BusinessType.EXPORT)
    @PostMapping("generateReportQrcode/{id}")
    public void generateReportQrcode(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
    {
        File file = inspectionReportService.generateReportQrcode(id);
        download(file,response);
    }
    /**
     * 生成证书二维码
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @Log(title = "生成证书二维码", businessType = BusinessType.EXPORT)
    @PostMapping("generateCertificateQrcode/{id}")
    public void generateCertificateQrcode(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
    {
        File file = inspectionReportService.generateCertificateQrcode(id);
        download(file,response);
    }
    /**
     * 生成检测二维码
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @Log(title = "生成检测二维码", businessType = BusinessType.EXPORT)
    @PostMapping("generateVerifyQrcode/{id}")
    public void generateVerifyQrcode(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
    {
        File file = inspectionReportService.generateVerifyQrcode(id);
        download(file,response);
    }
    /**
     * 生成检测报告
     */
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @Log(title = "生成检测报告", businessType = BusinessType.EXPORT)
    @PostMapping("generateImg/{id}")
    public void generateImg(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
    {
        File file = inspectionReportService.generateImg(id);
        download(file,response);
    }
    public void download(File waterFile, HttpServletResponse response) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try {
            // 如果文件存在，则进行下载
            if (waterFile.exists()) {
                // 配置文件下载
            /*response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");*/
                response.setCharacterEncoding("utf-8");
                response.setContentType("multipart/form-data");
                // 实现文件下载
                byte[] buffer = new byte[1024];
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(waterFile.getName(), "UTF-8"));
                //生成文件流
                fis = new FileInputStream(waterFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                os.flush();
                os.close();
            } else {
                //throw new GenericException(FileStorageEnum.FILE_IS_NULL.getCode(), FileStorageEnum.FILE_IS_NULL.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            //throw new GenericException(FileStorageEnum.FILE_DOWNLOAD_FAIL.getCode(), FileStorageEnum.FILE_DOWNLOAD_FAIL.getMsg());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (waterFile != null && waterFile.exists()) {
                waterFile.delete();
            }
        }
    }
}
