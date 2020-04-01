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
@EnableJpaRepositories(entityManagerFactoryRef = "test2EntityManagerFactory",
    transactionManagerRef = "test2TransactionManager", basePackages = {"com.deepeshuniyal.app.test2.repo"})
public class Test2DBConfig {

	@Bean(name = "test2DataSource")
	@ConfigurationProperties(prefix="spring.datasource.secondary")
	  public DataSource dataSource()  throws IllegalArgumentException, NamingException{
	    //return DataSourceBuilder.create().build();
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();    
        bean.setJndiName("java:/comp/env/jdbc/deepeshuniyal2");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(true);
        bean.afterPropertiesSet();
        
        return (DataSource) bean.getObject();
	  }

	  @Bean(name = "test2EntityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean test2EntityManagerFactory(
	      EntityManagerFactoryBuilder builder, @Qualifier("test2DataSource") DataSource dataSource) {
	    return builder.dataSource(dataSource).packages("com.deepeshuniyal.app.test2.domain").persistenceUnit("test2")
	        .build();
	  }

	  @Bean(name = "test2TransactionManager")
	  public PlatformTransactionManager test2TransactionManager(
	      @Qualifier("test2EntityManagerFactory") EntityManagerFactory test2EntityManagerFactory) {
	    return new JpaTransactionManager(test2EntityManagerFactory);
	  }
}
