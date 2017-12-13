package com.jt.blog.controller;

import com.jt.blog.common.util.AutoNoGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : 戴瑞
 * @create 2016-08-31 14
 **/
@Controller
@RequestMapping("/ckeditor")
public class CKEditorController {

    /**
     *  文章图片上传路径
     */
    @Value("${upload-blogFile-path}")
    private String articlePath;

    /**
     * 图片文件上传
     * @param request
     * @param response
     * @param file
     */
    @RequestMapping("/upload")
    public void ckeditorpload(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "upload", required = false) MultipartFile file){
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out;
        try {
            out = response.getWriter();
            String contentType = file.getContentType();
            long size = file.getSize();
            if (contentType.equals("image/pjpeg") || contentType.equals("image/jpeg")
                    ||contentType.equals("image/png") || contentType.equals("image/x-png")
                    ||contentType.equals("image/gif")||contentType.equals("image/bmp")){
            }else{
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'file type error（must be .jpg/.gif/.bmp/.png文件）');");
                out.println("</script>");
                return;
            }
            if(size>10*1024*1024){
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'file size less than 10M');");
                out.println("</script>");
                return;
            }
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf('.'));
            byte[] bytes = file.getBytes();
            //String destPath = "/usr/blog/img/";
            File destFolder = new File(articlePath);
            if(!destFolder.exists()){
                destFolder.mkdirs();
            }
            String newName = AutoNoGenerator.nextNo()+fileType;
            File destFile = new File(destFolder,newName);
            FileUtils.writeByteArrayToFile(destFile,bytes);
            String imgUrl = "/blog/img/"+newName;
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" +imgUrl+"','')");
            out.println("</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
