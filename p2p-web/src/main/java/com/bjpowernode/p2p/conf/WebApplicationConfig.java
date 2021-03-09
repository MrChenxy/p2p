package com.bjpowernode.p2p.conf;


import com.bjpowernode.p2p.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        String addPath[] = {"/loan/**"};
        String excludePath[] =
                {"/loan/loan",
                        "/loan/loanInfo",
                        "/loan/login",
                        "/loan/page/login",
                        "/loan/page/register",
                        "/loan/phone",
                        "/loan/register",
                        "/loan/sendCode"
                };
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(addPath)
                .excludePathPatterns(excludePath);
    }
}
