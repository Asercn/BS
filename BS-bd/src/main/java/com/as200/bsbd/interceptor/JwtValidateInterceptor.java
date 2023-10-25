package com.as200.bsbd.interceptor;

import com.alibaba.fastjson2.JSON;
import com.as200.bsbd.common.util.JwtUtil;
import com.as200.bsbd.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
//@CrossOrigin
public class JwtValidateInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        log.debug(request.getRequestURI()+"需要验证:" + token);
        if (token != null){
            try {
                jwtUtil.parseToken(token);
                log.debug(request.getRequestURI()+"验证通过!");
                return true;  // 通过
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        log.debug(request.getRequestURI()+"验证失败!");
        response.setContentType("application/json;charset=utf-8");
        Result<Object> fail = Result.fail(20003, "请重新登入");
        response.getWriter().write(JSON.toJSONString(fail));
        return false;  // 拦截
    }
}
