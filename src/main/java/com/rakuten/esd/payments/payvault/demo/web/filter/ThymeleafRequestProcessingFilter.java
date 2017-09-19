package com.rakuten.esd.payments.payvault.demo.web.filter;

import com.rakuten.esd.payments.payvault.demo.web.application.ThymeleafDemoApplication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author devendra.nalawade on 2017/09/14
 */
public class ThymeleafRequestProcessingFilter implements Filter {

    private ServletContext servletContext;
    private ThymeleafDemoApplication application;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        this.application = new ThymeleafDemoApplication(this.servletContext);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (isRequestSupported(request, response)) {
            chain.doFilter(request, response);
        }
        try {
            processRequest(request, response, servletContext);
        } catch (Exception e) {
            try {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
                // TODO for later
            }
            throw new ServletException(e);
        }

    }

    @Override
    public void destroy() {
        // TODO
    }

    protected boolean isRequestSupported(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        return (req.getRequestURI().startsWith("/css") ||
                req.getRequestURI().startsWith("/images") ||
                req.getRequestURI().startsWith("/favicon") ||
                !this.application.isRouteSupported(req));
    }

    protected void processRequest(ServletRequest request, ServletResponse response, ServletContext servletContext) throws Exception {
        this.application.processRequest((HttpServletRequest) request, (HttpServletResponse) response, servletContext);
    }
}
