package com.aaa.lee.app.annotation;

import com.aaa.lee.app.api.IRepastService;
import com.aaa.lee.app.model.LoginLog;
import com.aaa.lee.app.utils.DateUtil;
import com.aaa.lee.app.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class LoginAspect {

    @Autowired
    private IRepastService repastService;

    /**
     * 定义切面  也就是说要切哪些地方（controller service 也可以用通配符）
     */
    @Pointcut("@annotation(com.aaa.lee.app.annotation.LoginLogAnnocation)")
    public void pointcut(){
        //TODO

    }

    /**
             *   proceedingJoinPoint:获取目标路径的参数
             * @param proceedingJoinPoint
             * @return
             * @throws Exception
             */
            @Around("pointcut()")
            public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
                Object result = null;
                try{
                    result = proceedingJoinPoint.proceed();

        }catch (Throwable t){
            log.error(t.getMessage());
        }
        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取ip地址
        String ipAddr = IPUtil.getIpAddr(request);
        //获取member的id
        Object[] args = proceedingJoinPoint.getArgs();
        //类的名称(全限定名com.aaa.lee.app.controller.MemberController)
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //获取方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        //通过反射获取类对象
        Class targetClass = Class.forName(className);
        //获取所有的方法
        Method[] methods = targetClass.getMethods();
        String operationType = "";
        String operationName = "";
        //获取到了类中所有的方法 如何确定获取出哪个方法是自己需要的
        for (Method method: methods) {
            if (method.getName().equals(methodName)){
                //根据方法名进行获取自己想要的方法 仍然需要考虑到方法的重载
                //可以再次通过方法的参数个数来确定
                //进行参数的类型的匹配
                Class[] parameterTypes = method.getParameterTypes();
                //进行参数的个数的匹配
                if (parameterTypes.length == args.length){
                    operationType = method.getAnnotation(LoginLogAnnocation.class).operationType();
                    operationName = method.getAnnotation(LoginLogAnnocation.class).operationName();
                    break;

                }
            }
        }

        LoginLog loginLog = new LoginLog();
        String dateString = DateUtil.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss");
        loginLog.setCreateTime(dateString);
        loginLog.setLoginType(1);
        loginLog.setCity(operationName);
        loginLog.setProvince(operationType);
        loginLog.setIp(ipAddr);
        repastService.addLoginLog(loginLog);

        return result;
    }


}
