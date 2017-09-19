package com.rakuten.esd.payments.payvault.demo.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author devendra.nalawade on 2017/09/14
 */
public class HomeController implements IController {
    @Override
    public void processTemplate(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine)
            throws Exception {
        WebContext webContext = new WebContext(request, response, servletContext, request.getLocale());
        webContext.setVariable("today", Calendar.getInstance());

        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        webContext.setVariable("formattedDate", format.format(Calendar.getInstance().getTime()));
        templateEngine.process("home/home", webContext, response.getWriter());
    }
}
