/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.co.tlab.ng.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 *
 * @author Hoon Ki Kim <rymf0802@tlab.co.kr>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"kr.co.tlab.ng"})
public class Dispatcher extends WebMvcConfigurerAdapter {
    
     private final Logger logger = LoggerFactory.getLogger(Dispatcher.class);
    
    // equivalents for <mvc:resources/> tags
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/index.html");
//        registry.addViewController("/office/welcome").setViewName("welcome");
//        registry.addViewController("/office/notAllow").setViewName("notAllow");
//        registry.addViewController("/office/login").setViewName("login");
//        registry.addViewController("/office/error").setViewName("error");
//
//        registry.addRedirectViewController("/", "/index");
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/app/**")
		        .maxAge(360L)
		        .allowedOrigins("*")
		        .allowedMethods("GET", "POST")
		        .allowedHeaders("x-requested-with")
		        .allowedOrigins("*");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters
    ) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);

        //objectMapper.setFilters(null);
        //objectMapper.configure(SerializationFeature.);
        //objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        //Hibernate4Module module = new Hibernate4Module();
        //module.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, true);
        //objectMapper.registerModule(module);
        jsonConverter.setObjectMapper(objectMapper);
        converters.add(jsonConverter);

        MappingJackson2XmlHttpMessageConverter xmlConverter = new MappingJackson2XmlHttpMessageConverter();
        XmlMapper xmlMapper = new XmlMapper();
        //Jacksonxml
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        xmlMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
        xmlMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, true);
        xmlMapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true);
        //xmlMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        //xmlMapper.configure(SerializationFeature.);
        xmlConverter.setObjectMapper(xmlMapper);

//        Jaxb2RootElementHttpMessageConverter xmlConverter = new Jaxb2RootElementHttpMessageConverter();
//        List<MediaType> types = new ArrayList<>();
//        types.add(MediaType.APPLICATION_XML);
//        xmlConverter.setProcessExternalEntities(Boolean.FALSE);
//
//        xmlConverter.setSupportedMediaTypes(types);
        converters.add(xmlConverter);

        super.configureMessageConverters(converters);
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new PathMatchingResourcePatternResolver().getResource("/properties.xml"));
        return ppc;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

//    @Bean
//    public BeanNameViewResolver beanNameViewResolver() {
//        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
//
//        beanNameViewResolver.setOrder(0);
//
//        return beanNameViewResolver;
//    }

//    @Bean
//    public ViewResolver getViewResolver(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/");
//        resolver.setSuffix("");
//        resolver.setOrder(1);
//        return resolver;
//    }

    @Bean
    public MappingJackson2JsonView jacksonJsonView() {
        return new MappingJackson2JsonView();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
