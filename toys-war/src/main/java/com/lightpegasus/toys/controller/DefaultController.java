package com.lightpegasus.toys.controller;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * Created by alek on 4/11/14.
 */
public class DefaultController implements ToyController {
  @Override
  public void process(HttpServletRequest request, HttpServletResponse response,
      ServletContext servletContext, TemplateEngine templateEngine) throws Exception {

    WebContext context = new WebContext(request, response, servletContext, request.getLocale());

    context.setVariable("today", Calendar.getInstance());
    context.setVariable("world", "Mars");
    context.setVariable("alert", "<script>alert('This shouldn't show');</script>");

    templateEngine.process("toy", context, response.getWriter());
  }
}
