package com.jt.blog.controller;

import com.jt.blog.common.validate.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author : 戴瑞
 * @create 2016-09-30 14
 **/
@Controller
@RequestMapping("/validate/code")
public class ValidateCodeController {

    @RequestMapping("/pic")
    public void pic(HttpServletResponse response,HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode vCode = new ValidateCode(120,40,4,50);
        session.setAttribute("vcode", vCode.getCode());
        vCode.write(response.getOutputStream());
    }
}
