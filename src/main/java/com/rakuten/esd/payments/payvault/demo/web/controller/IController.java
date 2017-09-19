package com.rakuten.esd.payments.payvault.demo.web.controller;

import org.thymeleaf.ITemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author devendra.nalawade on 2017/09/14
 */
public interface IController {

    void processTemplate(
            HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, ITemplateEngine templateEngine) throws Exception;

}
