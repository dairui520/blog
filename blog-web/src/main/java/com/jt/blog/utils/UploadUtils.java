package com.jt.blog.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @author : 戴瑞
 * @Description :文件上传工具类
 * @create : 2017-05-11 15:08
 **/

public class UploadUtils {

    private static UploadUtils uploadUtils = null;

    private String separator = File.separator;

    private String uploadPath = "//usr//local//app//image";

    private UploadUtils() {
    }

    // 单例模式
    public static UploadUtils getInstance() {
        if (uploadUtils == null) {
            synchronized (UploadUtils.class) {
                //未初始化，则初始instance变量
                if (uploadUtils == null) {
                    uploadUtils = new UploadUtils();
                }
            }
        }
        return uploadUtils;
    }

    /**
     * @param files   文件 可变参数
     * @param request 请求的request
     */
    public String Fileload(HttpServletRequest request, MultipartFile... files) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("");
        return uploadFile(path, files);
    }

    /**
     * 文件保存在类加载路径中
     *
     * @param files 文件 可变参数
     */
    public String Fileload(MultipartFile... files) throws Exception {

        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        return uploadFile(path, files);
    }

    /**
     * 指定文件的上传路径
     * 如果路径为空，则默认使用类加载路径
     * @param files 文件 可变参数
     */
    public String Fileload(String specifyPath, MultipartFile... files) throws Exception {
        if (StringUtils.isEmpty(specifyPath)){
           return Fileload(files);
        }
        return uploadFile(specifyPath, files);
    }

    private String uploadFile(String path, MultipartFile... files) throws Exception {

        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : files) {
            // 获取文件的原始名称
            String originalFilename = file.getOriginalFilename();
            // 校验
            if (StringUtils.isEmpty(originalFilename)) {
                throw new Exception("上传文件为空");
            }
            String relativePath = separator + calendar.get(Calendar.YEAR) +
                    separator + calendar.get(Calendar.MONTH) +
                    separator + calendar.get(Calendar.DATE) +
                    separator + UUID.randomUUID() +
                    originalFilename.substring(originalFilename.lastIndexOf("."));
            String realPath = path + separator + relativePath;
            File sourceFile = new File(realPath);
            // 如果父级目录不存在就创建父级目录
            if (!sourceFile.exists()) {
                // 获取父级目录，并创建
                File parentFile = sourceFile.getParentFile();
                parentFile.mkdirs();
            }
            // 保存到硬盘中
            file.transferTo(sourceFile);
            paths.add(relativePath);
        }
        return String.join(",", paths);
    }
}
