package com.deepeshuniyal.app;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;

@SpringBootApplication

public class SpringBootApp extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApp.class);
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
	    return new TomcatEmbeddedServletContainerFactory() {

	        @Override
	        protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
	                Tomcat tomcat) {
	            tomcat.enableNaming();
	            return super.getTomcatEmbeddedServletContainer(tomcat);
	        }
	    };
	}
	
	@Bean(name = "db1")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource jndiDataSourcePrimary() throws IllegalArgumentException, NamingException 
    {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();    
        bean.setJndiName("java:/comp/env/jdbc/deepeshuniyal1");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(true);
        bean.afterPropertiesSet();
        
        return (DataSource) bean.getObject();
//    	JndiDataSourceLookup dataSource = new JndiDataSourceLookup();
//        dataSource.setResourceRef(true);
//        return dataSource.getDataSource("jdbc/deepeshuniyal");
    }
	@Bean(name = "db2")
	@ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource jndiDataSourceSecondary() throws IllegalArgumentException, NamingException 
    {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();    
        bean.setJndiName("java:/comp/env/jdbc/deepeshuniyal2");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(true);
        bean.afterPropertiesSet();
        
        return (DataSource) bean.getObject();
//    	JndiDataSourceLookup dataSource = new JndiDataSourceLookup();
//        dataSource.setResourceRef(true);
//        return dataSource.getDataSource("jdbc/deepeshuniyal");
    }
	
	@Bean(name = "jdbcTemplate1")
	 public JdbcTemplate jdbcTemplate1(@Qualifier("db1") DataSource ds) {
	  return new JdbcTemplate(ds);
	 }
	
	@Bean(name = "jdbcTemplate2")
	 public JdbcTemplate jdbcTemplate2(@Qualifier("db2") DataSource ds) {
	  return new JdbcTemplate(ds);
	 }
}