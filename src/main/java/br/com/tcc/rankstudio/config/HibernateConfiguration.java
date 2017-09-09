package br.com.tcc.rankstudio.config;

import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe responsavel por realizar a configuracao e a integracao do Hibernate4 com o Spring4
 * 
 * @author Vitor Marques
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("br.com.tcc.rankstudio.dao")
@PropertySource(value = {"classpath:datasource.properties"})
public class HibernateConfiguration {

	@Autowired
	private Environment environment;
	
	/**
	 * Define um <code>@Bean</code> de uma SessionFactory que sera utilizado para realizar operacoes na base de dados 
	 * Informa a SessionFactory onde procurar pelos objetos de modelo mapeados com as anotacoes da JPA
	 * 
	 * @return {@link LocalSessionFactoryBean}
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"br.com.tcc.rankstudio.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	/**
	 * Define um <code>@Bean</code> de um DataSource utilizando as configuracoes recuperadas atraves do arquivo .properties do ambiente
	 * 
	 * @return {@link DataSource}
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
	}

	/**
	 * Define um <code>@Bean</code> de um TransactionManager utilizado para o controle transacional das operacoes do banco de dados
	 * 
	 * @return {@link TransactionManager}
	 */
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory factory) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(factory);
       return txManager;
    }
	
	/**
	 * Recupera as propriedades de configuracao do Hibernate do arquivo .properties do ambinete
	 * 
	 * @return {@link Properties} um Objeto com as propriedades de configuracao do hibernate
	 */
	private Properties hibernateProperties() {
		Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;  
	}
	
}
