package com.csair.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.csair.utlis.MapsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * URL映射过滤器
 *
 * @author LiRenGui
 * @date 2020年10月30日19:38:34
 */
@ServletComponentScan
@Component
@WebFilter(urlPatterns = { "/WXOPEND"}, filterName = "urlMappingFilter")
public class MapsFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(MapsFilter.class);
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        log.info(request.getRequestURI());
        String property = MapsUtils.getProperty(splitURI(request.getRequestURI()));
        if (null != property && "" != property) {
            req.getRequestDispatcher(reWriteURI(request.getRequestURI(),property)).forward(req, response);
        } else {
            filterChain.doFilter(req, response);
        }

    }

    public static String splitURI(String str) {
        String uri;
        uri = "/"+str.split("/*/")[1];
        return uri;
    }

    public static String reWriteURI(String uri,String replaceStr){
        return uri.replace("/WXOPEND",replaceStr);
    }


    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}

