package cn.edu.bjtu.jzlj.config.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.main.util.results.Resp;
import cn.edu.bjtu.jzlj.util.results.Resp;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MyShiroAuthenticationFilter
 * @Description: 自定义shiro拦截器
 * @Author miaopeng
 * @Date 2019-06-03 16:25:59
 **/
public class MyShiroAuthenticationFilter extends FormAuthenticationFilter {
    public MyShiroAuthenticationFilter(){
        super();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return super.onAccessDenied(request, response);
        } else {
            if (!isBrowser((HttpServletRequest) request)||isAjax((HttpServletRequest) request)) {
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpServletResponse.setContentType("application/json;charset=utf-8");
                String errorMsg = (String) request.getAttribute("errorMsg");
                Resp resp = null;
                if (!StringUtils.isEmpty(errorMsg)) {
                    resp = Resp.getInstantiationError(errorMsg, null, null);
                } else {
                    JSONObject jsonObject = new JSONObject();
                    resp = Resp.getInstantiationError("登录超时", Resp.JSONSTRING, jsonObject);
                }
                httpServletResponse.getWriter().write(JSON.toJSONString(resp));

            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        if (requestedWithHeader != null && requestedWithHeader.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isBrowser(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        if (request.getHeader("User-Agent").startsWith("Mozilla")) {
            return true;
        } else {
            return false;
        }
    }

}
