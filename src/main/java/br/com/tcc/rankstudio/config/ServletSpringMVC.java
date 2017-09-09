package br.com.tcc.rankstudio.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Classe responsavel pela configuracao do DispatcherServlet do SpringMVC que controla todas as requisicoes feitas para a aplicacao
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Carrega as classes de configuracao da aplicacao
     * @return
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
     * @return
     */
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
