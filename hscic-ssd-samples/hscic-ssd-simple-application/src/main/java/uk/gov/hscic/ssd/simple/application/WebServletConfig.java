package uk.gov.hscic.ssd.simple.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebServletConfig extends WebMvcConfigurerAdapter {

	private static Logger logger = LoggerFactory.getLogger(WebServletConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        logger.debug("configure resource handler registry");

        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/resources/");
    }

    @Bean
    public ViewResolver configureViewResolver() {

        logger.debug("configure view resolver");

        InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
        viewResolve.setPrefix("/WEB-INF/views/");
        viewResolve.setSuffix(".jsp");

        return viewResolve;
    }

}
