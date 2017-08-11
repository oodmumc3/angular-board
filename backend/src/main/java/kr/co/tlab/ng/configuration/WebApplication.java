package kr.co.tlab.ng.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by JinPark on 2016-12-21.
 */
public class WebApplication implements WebApplicationInitializer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        logger.debug("ENTRY onStartup");

        // Spring
        WebApplicationContext dispatcherContext = getDispatcherContext();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);

        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcher", dispatcherServlet);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/api/*");
        dispatcher.setAsyncSupported(true);
        dispatcher.setMultipartConfig(new MultipartConfigElement(null, 20480000, 40960000, 0));

        sc.addListener(new ContextLoaderListener(dispatcherContext));

        CharacterEncodingFilter charEncodingFilter = new CharacterEncodingFilter();
        charEncodingFilter.setEncoding("UTF-8");
        charEncodingFilter.setForceEncoding(true);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        FilterRegistration.Dynamic charEncoding = sc.addFilter("characterEncoding", charEncodingFilter);
        charEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }

    private AnnotationConfigWebApplicationContext getDispatcherContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(Dispatcher.class);
        context.register(Persistence.class);
        return context;
    }

}
