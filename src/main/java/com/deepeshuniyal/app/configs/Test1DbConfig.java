package com.deepeshuniyal.app.configs;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
    basePackages = {"com.deepeshuniyal.app.test1.repo"})
public class Test1DbConfig {

	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSource dataSource()  throws IllegalArgumentException, NamingException{
	    //return DataSourceBuilder.create().build();
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();    
        bean.setJndiName("java:/comp/env/jdbc/deepeshuniyal1");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(true);
        bean.afterPropertiesSet();
        
        return (DataSource) bean.getObject();
	  }

	  @Bean(name = "entityManagerFactory")
	  @Primary
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
	      EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
	    return builder.dataSource(dataSource).packages("com.deepeshuniyal.app.test1.domain").persistenceUnit("test1")
	        .build();
	  }

	  @Bean(name = "transactionManager")
	  @Primary
	  public PlatformTransactionManager transactionManager(
	      @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
	    return new JpaTransactionManager(entityManagerFactory);
	  }
}
