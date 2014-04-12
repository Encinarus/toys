package com.lightpegasus.toys.filters;

import com.lightpegasus.toys.app.ToyApplication;
import com.lightpegasus.toys.controller.ToyController;
import org.thymeleaf.TemplateEngine;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Toy filter for playing with Thymeleaf (http://www.thymeleaf.org/doc/html/Using-Thymeleaf.html)
 *
 * Based heavily on thymeleafexamples.gtvg.web.filter.GTVGFilter.
 */
public class ToyThymeFilter implements Filter {
  private ServletContext servletContext;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.servletContext = filterConfig.getServletContext();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    if (!process((HttpServletRequest)request, (HttpServletResponse)response)) {
      chain.doFilter(request, response);
    }
  }

  private boolean process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.getWriter().println("Hello world!!!");

    ToyController toyController = ToyApplication.resolveControllerForRequest(request);
    if (toyController == null) {
      return false;
    }

    TemplateEngine templateEngine = ToyApplication.getTemplateEngine();

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    try {
      toyController.process(request, response, this.servletContext, templateEngine);
    } catch (Exception e) {
      throw new ServletException(e);
    }

    return true;
  }

  @Override
  public void destroy() {

  }
}
