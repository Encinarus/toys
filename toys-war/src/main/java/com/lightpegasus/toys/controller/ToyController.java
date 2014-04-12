package com.lightpegasus.toys.controller;

import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Toy controller. Based heavily on thymeleafexamples.gtvg.web.controller.IGTVGController
 */
public interface ToyController {
  public void process(
      HttpServletRequest request, HttpServletResponse response,
      ServletContext servletContext, TemplateEngine templateEngine)
      throws Exception;
}
