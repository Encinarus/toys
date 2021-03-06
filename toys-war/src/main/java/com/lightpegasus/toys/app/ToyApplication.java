package com.lightpegasus.toys.app;

import com.lightpegasus.toys.controller.DefaultController;
import com.lightpegasus.toys.controller.ToyController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles setting up and configuring the server. This is based very heavily on
 * thymeleafexamples.gtvg.web.application.GTVGApplication
 */
public class ToyApplication {
  private static Map<String, ToyController> controllersByURL;
  private static TemplateEngine templateEngine;

  static {
    initializeControllersByURL();
    initializeTemplateEngine();
  }

  private static void initializeTemplateEngine() {
    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();

    // XHTML is the default mode, but we will set it anyway for better understanding of code
    templateResolver.setTemplateMode("XHTML");
    // This will convert "home" to "/WEB-INF/templates/home.html"
    templateResolver.setPrefix("/WEB-INF/templates/");
    templateResolver.setSuffix(".html");
    // Set template cache TTL to 1 hour. If not set, entries would live in cache until expelled by LRU
    templateResolver.setCacheTTLMs(3600000L);

    // Cache is set to true by default. Set to false if you want templates to
    // be automatically updated when modified.
    templateResolver.setCacheable(true);

    templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
  }

  private static void initializeControllersByURL() {
    controllersByURL = new HashMap<String, ToyController>();
    controllersByURL.put("/", new DefaultController());
  }

  public static ToyController resolveControllerForRequest(final HttpServletRequest request) {
    final String path = getRequestPath(request);
    return controllersByURL.get(path);
  }

  public static TemplateEngine getTemplateEngine() {
    return templateEngine;
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
