package com.jt.blog.controller;

import com.jt.blog.utils.UploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;

/**
 * @author : 戴瑞
 * @Description :通用的文件上传Controller
 * @create : 2017-06-27 20:06
 **/
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Value("${upload-file-path}")
    private String path;

    @RequestMapping("/page")
    public String upload(){
        return "/front/fileupload";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(MultipartFile image, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(image.getOriginalFilename())) {
            return "";
        }
        // 调到文件上传工具类，获取文件的路径
        String imageUrl = UploadUtils.getInstance().Fileload(path,image);
        Map<String, Object> result = new HashMap<String,Object>();
        if (StringUtils.isEmpty(imageUrl)) {
            result.put("flag", false);
            return result;
        }
        result.put("flag", true);
        result.put("url", imageUrl);
        return result;
    }
}
