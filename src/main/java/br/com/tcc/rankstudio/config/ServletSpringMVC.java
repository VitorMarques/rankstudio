package br.com.tcc.rankstudio.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Classe responsavel pela configuracao do DispatcherServlet do SpringMVC que controla todas as requisicoes feitas para a aplicacao
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String LOCATION = ""; // Temporary location where files will be stored
    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk

    /**
     * Carrega as classes de configuracao da aplicacao
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfiguration.class, HibernateConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    /**
     * Define a partir de qual o URL o DispatcherServlet deve comecar a responder.
     * Neste caso deve responder para todas as requisicoes feitas a partir da raiz da aplicacao
     */
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    private MultipartConfigElement getMultipartConfigElement() {
        return new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
    }
}
