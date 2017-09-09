package br.com.tcc.rankstudio.config;

import br.com.tcc.rankstudio.controller.UsuarioController;
import br.com.tcc.rankstudio.dao.UsuarioDao;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.service.UsuarioServiceImpl;
import br.com.tcc.rankstudio.util.DataUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Classe responsavel por configurar o container do Spring e todos os detalhes referentes a aplicacao
 */
@EnableWebMvc
@ComponentScan(basePackageClasses = {UsuarioController.class, UsuarioServiceImpl.class, UsuarioDao.class, Usuario.class, DataUtils.class})
@PropertySource(value = {"classpath:messages.properties"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
