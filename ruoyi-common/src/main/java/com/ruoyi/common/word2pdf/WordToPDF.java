package com.ruoyi.common.word2pdf;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * aspose-words 转pdf
 */
@Service
public class WordToPDF {

    /*****
     * 需要引入jar包：aspose-words-15.8.0-jdk16.jar
     * @param args
     */
    public static void main(String[] args) {
        try {
            doc2pdf("D://doc_f//用freemarker生成Word文档_20210302_58.doc", "d://doc_f//pdf1.pdf", "D://jeesiteWorkspace//credit//cmp-internet-portal//cmp-internet-portal-service//src//main//resources//");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean getLicense() {
        return getLicense(null);
    }

    public static boolean getLicense(String path) {
        boolean result = false;
        try {
            //System.out.println(path+"/license.xml");
            ClassPathResource resource = new ClassPathResource("license.xml");
            //File sourceFile = resource.getFile();
            InputStream is = resource.getInputStream();
            //File file = new File(path+"/license.xml"); // 新建一个空白pdf文档
            //InputStream is = new FileInputStream(sourceFile); // license.xml找个路径放即可。
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean doc2pdf(String inPath, String outPath,String path) throws IOException {
        boolean changeFlag = false;
        if (!getLicense(path)) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            return changeFlag;
        }
        FileOutputStream os = null;
        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);// 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒"); // 转化用时
            changeFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
            changeFlag = false;
        }finally {
            os.close();
        }
        return changeFlag;
    }

}
