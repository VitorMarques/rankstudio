package br.com.tcc.rankstudio.config;

import br.com.tcc.rankstudio.controller.UsuarioController;
import br.com.tcc.rankstudio.dao.UsuarioDao;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.service.UsuarioServiceImpl;
import br.com.tcc.rankstudio.util.DataUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.support.OpenSessionInViewInterceptor;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.print.attribute.standard.Media;
import java.awt.*;

/**
 * Classe responsavel por configurar o container do Spring e todos os detalhes referentes a aplicacao
 */
@EnableWebMvc
@ComponentScan(basePackageClasses = {UsuarioController.class, UsuarioServiceImpl.class, UsuarioDao.class, Usuario.class, DataUtils.class})
@PropertySource(value = {"classpath:messages.properties", "classpath:app.properties"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    private final SessionFactory sessionFactory;

    @Autowired
    public ApplicationConfiguration(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return resolver;
    }

    @Bean
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(openSessionInViewInterceptor());
        super.addInterceptors(registry);
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver resolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public OpenSessionInViewInterceptor openSessionInViewInterceptor(){
        OpenSessionInViewInterceptor openSessionInViewInterceptor = new OpenSessionInViewInterceptor();
        openSessionInViewInterceptor.setSessionFactory(sessionFactory);
        return openSessionInViewInterceptor;
    }

}
