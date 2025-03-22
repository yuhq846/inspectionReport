package com.ruoyi.system.service.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.system.SystemUtil;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ReportingEngine;
import com.aspose.words.SaveFormat;
import com.aspose.words.net.System.Data.DataSet;
import com.aspose.words.net.System.Data.DataTable;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.word2pdf.WordToPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.InspectionReportMapper;
import com.ruoyi.system.domain.InspectionReport;
import com.ruoyi.system.service.IInspectionReportService;

/**
 * 检测报告Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-19
 */
@Service
public class InspectionReportServiceImpl implements IInspectionReportService 
{
    private static final String filePath = System.getProperty("java.io.tmpdir") + File.separator;

    @Value("${qr.reportUrl}")
    private String reportQrUrl;

    @Value("${qr.certificateUrl}")
    private  String CertificateQrUrl;

    @Value("${qr.verifyQrUrl}")
    private  String VerifyQrUrl;

    @Autowired
    private InspectionReportMapper inspectionReportMapper;

    /**
     * 查询检测报告
     * 
     * @param id 检测报告主键
     * @return 检测报告
     */
    @Override
    public InspectionReport selectInspectionReportById(Long id)
    {
        return inspectionReportMapper.selectInspectionReportById(id);
    }

    /**
     * 查询检测报告列表
     * 
     * @param inspectionReport 检测报告
     * @return 检测报告
     */
    @Override
    public List<InspectionReport> selectInspectionReportList(InspectionReport inspectionReport)
    {
        return inspectionReportMapper.selectInspectionReportList(inspectionReport);
    }

    /**
     * 新增检测报告
     * 
     * @param inspectionReport 检测报告
     * @return 结果
     */
    @Override
    public int insertInspectionReport(InspectionReport inspectionReport)
    {
        inspectionReport.setCreateTime(DateUtils.getNowDate());
        return inspectionReportMapper.insertInspectionReport(inspectionReport);
    }

    /**
     * 修改检测报告
     * 
     * @param inspectionReport 检测报告
     * @return 结果
     */
    @Override
    public int updateInspectionReport(InspectionReport inspectionReport)
    {
        inspectionReport.setUpdateTime(DateUtils.getNowDate());
        return inspectionReportMapper.updateInspectionReport(inspectionReport);
    }

    /**
     * 批量删除检测报告
     * 
     * @param ids 需要删除的检测报告主键
     * @return 结果
     */
    @Override
    public int deleteInspectionReportByIds(Long[] ids)
    {
        return inspectionReportMapper.deleteInspectionReportByIds(ids);
    }

    /**
     * 删除检测报告信息
     * 
     * @param id 检测报告主键
     * @return 结果
     */
    @Override
    public int deleteInspectionReportById(Long id)
    {
        return inspectionReportMapper.deleteInspectionReportById(id);
    }


    @Override
    public File generateReportQrcode(Long id){
        InspectionReport inspectionReport=inspectionReportMapper.selectInspectionReportById(id);
        String path=SystemUtil.get(SystemUtil.USER_DIR) + File.separator +"report"+File.separator+ inspectionReport.getReportNo()+".png";
        BufferedImage qrImage = generateTransparentQRCode(reportQrUrl+id, 300);
        ImgUtil.write(qrImage,  FileUtil.file(path));
        File filepath = new File(path);
        return  filepath;
    }
    public static BufferedImage generateTransparentQRCode(String content, int size) {
        QrConfig config = new QrConfig(size, size);
        // 生成普通二维码（带白色背景）
        BufferedImage qrImage = QrCodeUtil.generate(content, config);

        // 创建一个支持透明背景的 BufferedImage
        BufferedImage transparentImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int pixel = qrImage.getRGB(x, y);
                // 判断是否是白色（白色背景转换为透明）
                if (pixel == Color.WHITE.getRGB()) {
                    transparentImage.setRGB(x, y, 0x00FFFFFF); // 透明
                } else {
                    transparentImage.setRGB(x, y, pixel); // 保持二维码颜色
                }
            }
        }
        return transparentImage;
    }
    @Override
    public File generateCertificateQrcode(Long id){
        InspectionReport inspectionReport=inspectionReportMapper.selectInspectionReportById(id);
        String path=SystemUtil.get(SystemUtil.USER_DIR) + File.separator + "certificate"+File.separator+inspectionReport.getReportNo()+".png";
        BufferedImage qrImage = generateTransparentQRCode(CertificateQrUrl+id, 300);
        ImgUtil.write(qrImage,  FileUtil.file(path));
        File filepath = new File(path);
        return  filepath;
    }
    @Override
    public File generateVerifyQrcode(Long id){
        InspectionReport inspectionReport=inspectionReportMapper.selectInspectionReportById(id);
        String path=SystemUtil.get(SystemUtil.USER_DIR) + File.separator + "verify"+File.separator+inspectionReport.getReportNo()+".png";
        BufferedImage qrImage = generateTransparentQRCode(VerifyQrUrl+id, 300);
        ImgUtil.write(qrImage,  FileUtil.file(path));
        File filepath = new File(path);
        return  filepath;
    }

    
    @Override
    public File generateImg(Long id){
        InspectionReport inspectionReport=inspectionReportMapper.selectInspectionReportById(id);
        String path=SystemUtil.get(SystemUtil.USER_DIR) + File.separator +"report"+File.separator+ inspectionReport.getReportNo()+".png";
        QrCodeUtil.generate(reportQrUrl+id, 100, 100, FileUtil.file(path));
        Map<String,Object> dataMap=BeanUtil.beanToMap(inspectionReport);
        dataMap.put("qrImg",path);
        Date inspectionDate=(Date) dataMap.get("inspectionDate");
        dataMap.put("inspectionDate", DateUtil.format(inspectionDate,"yyyy年MM月dd日"));
        Date signIssueDate=(Date) dataMap.get("signIssueDate");
        dataMap.put("signIssueDate", DateUtil.format(signIssueDate,"yyyy年MM月dd日"));
        dataMap.replaceAll((key, value) -> {
            if(value instanceof String){
                return ((String)value).replace("&lt;","<").replace("&gt;",">");
            }
            return value;
        });
        return createFile(dataMap);
    }

    @Override
    public File createFile(Map<String, Object> dataMap)  {
        String fileName="图片";
        //拼接生成文件的路径
        String tpath = filePath;
        File filepath = new File(tpath);
        if (!filepath.exists()) {
            filepath.mkdirs();
        }
        //要生成的文件地址 注意如想生成pdf,此处为.pdf后缀,其他文件写法相同,这里用png举例
        FileOutputStream out1 = null;
        try {
            out1 = new FileOutputStream(tpath + "/" + fileName + ".png");
            File targetFile = new File(tpath + "/" + fileName + ".png");
            //调用去水印的方法 读取license.xml文件
            WordToPDF.getLicense();

            Document document = new Document(FileUtil.getInputStream(SystemUtil.get(SystemUtil.USER_DIR) + File.separator + "信用报告模板.docx"));
            DocumentBuilder builder = new DocumentBuilder(document);
            LinkedHashMap<String, Object> map = new LinkedHashMap<>(dataMap);
            DataSet dataSet = new DataSet();
            DataTable dataTable = new DataTable("qrImg");
            dataTable.getColumns().add("qrImg");
            dataTable.getRows().add(dataMap.get("qrImg"));
            dataSet.getTables().add(dataTable);
            ReportingEngine engine = new ReportingEngine();
            engine.buildReport(document, dataSet);
            Set set = map.keySet();
            for (Object o : set) {
                String next = String.valueOf(o);//获取模板中的域名
                String s = String.valueOf(map.get(next));//通过域名获取想要在该域名处替换的数据
                if(StrUtil.isBlank(s)||s.equals("null")){
                    s="/";
                }
                //向域中添加图片
                if (next.equals("qrImg")) {
                } else
                    //普通文本的数据替换
                    if (builder.moveToMergeField(next)) {
                        int count = 1;
                        do {
                            builder.write(String.valueOf(s));
                        } while (builder.moveToMergeField(next + count));
                    }

            }
            document.save(out1, SaveFormat.PNG);
            out1.flush();
            out1.close();
            return targetFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
