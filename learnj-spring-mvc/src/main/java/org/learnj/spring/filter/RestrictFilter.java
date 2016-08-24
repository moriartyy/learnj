package org.learnj.spring.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Loster on 2016/8/22.
 */
public class RestrictFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(((HttpServletRequest)request).getRequestURI());
        System.out.println(this.getClass().getSimpleName() + " doFilter");
        response.getOutputStream().write("Restrict".getBytes());
        response.getOutputStream().flush();
    }

    @Override
    public void destroy() {

    }
}
