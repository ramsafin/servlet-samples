package ru.kpfu.itis.filters;

import javax.servlet.*;
import java.io.IOException;

public class SetCharacterEncodingFilter implements Filter {

    protected String encoding = "UTF-8";
    protected FilterConfig filterConfig;

    public void destroy() {
        encoding = null;
        filterConfig = null;
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        if (filterConfig.getInitParameter("encoding") != null) {
            this.encoding = filterConfig.getInitParameter("encoding");
        }
    }
}
