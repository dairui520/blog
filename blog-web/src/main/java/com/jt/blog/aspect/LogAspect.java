package com.jt.blog.aspect;

import com.jt.blog.common.util.ExceptionUtil;
import com.jt.blog.utils.SendMailUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : 戴瑞
 * @create 2016-11-16 14
 **/
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private String FileDilimiter="\r\n";
   // private String

    @Pointcut("execution(public * com.jt.blog.controller..*.*(..))")
    public void allController(){

    }

    @Before("allController()")
    public void tip(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        Object[] args = joinPoint.getArgs();
        logger.info("REQUEST : " + Arrays.toString(args));
    }

    @AfterReturning(returning = "ret", pointcut = "allController()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

    /**
     * 异常处理，发送报警邮件
     * @param joinPoint 目标对象
     * @param e 异常
     */
    @AfterThrowing(pointcut = "allController()",throwing = "e")
    public void doAfterException(JoinPoint joinPoint,Throwable e){
        String message="异常发生的类："+joinPoint.getTarget().getClass().getName()+"\r\n"
                +"异常方法："+joinPoint.getSignature().getName()+"()\r\n"
                +"方法参数：("+Arrays.toString(joinPoint.getArgs()) + ")"+"\r\n"
                +"异常信息："+e.getClass().getName()+":"
                + ExceptionUtil.getStackTrace(e);

        logger.error(message);
        boolean b = SendMailUtils.send_qqmail("dairui20171120@aliyun.com", "博客", "测试");
        logger.info("发送邮件状态："+b+FileDilimiter+"邮件内容："+message);
    }
}
