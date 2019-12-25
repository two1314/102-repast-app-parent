package com.aaa.lee.app.interceptor;

import com.aaa.lee.app.staticproperties.StaticProperties;
import com.aaa.lee.app.status.LoginStatus;
import com.aaa.lee.app.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AAA QY102 awb
 * @description 登录检查拦截器
 * @date create in 10:34 2019/12/25
 */
@Configuration
public class CheckLoginInterceptor implements WebMvcConfigurer {


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor()).addPathPatterns("/provider/*");
    }

    private HandlerInterceptor handlerInterceptor(){
        HandlerInterceptor handlerInterceptor=new HandlerInterceptor() {
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String token = (null == request.getParameter("token")?"":request.getParameter("token"));
                if (!StringUtil.isEmpty(token)){
                    return true;
                }
                Map<String,Object> tokenNull=new HashMap<String, Object>(16);
               // tokenNull.put(StaticProperties.CODE, LoginStatus.LOGIN_FAILED.getCode());
               // tokenNull.put(StaticProperties.MSG,LoginStatus.LOGIN_FAILED.getMsg());
                PrintWriter writer = response.getWriter();
                writer.print(JSON.toJSON(tokenNull).toString());
                writer.close();
                return false;
            }


            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        };
        return handlerInterceptor;

    }
}
