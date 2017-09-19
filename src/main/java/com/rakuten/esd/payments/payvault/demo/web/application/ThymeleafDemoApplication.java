package com.rakuten.esd.payments.payvault.demo.web.application;

import com.rakuten.esd.payments.payvault.demo.web.controller.HomeController;
import com.rakuten.esd.payments.payvault.demo.web.controller.IController;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author devendra.nalawade on 2017/09/14
 */
public class ThymeleafDemoApplication {

    private TemplateEngine templateEngine;
    private Map<String, IController> viewControllers;

    private IController defaultNoOpController = (request, response, servletContext, templateEngine1) -> {
        // Do Nothing
    };

    public ThymeleafDemoApplication(final ServletContext servletContext) {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);

        viewControllers = new HashMap<>();
        viewControllers.put("/", new HomeController());
    }

    public boolean isRouteSupported(HttpServletRequest request) {
        final String route = getRequestPath(request);
        return viewControllers.containsKey(route);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
        final String route = getRequestPath(request);
        final IController controller = viewControllers.getOrDefault(route, defaultNoOpController);
        controller.processTemplate(request, response, servletContext, templateEngine);
    }

    private static String getRequestPath(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();

        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }

        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }
}
