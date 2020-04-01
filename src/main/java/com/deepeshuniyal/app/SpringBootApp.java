package com.deepeshuniyal.app;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableJpaRepositories(basePackages={"com.deepeshuniyal.app.test1.repo","com.deepeshuniyal.app.test2.repo"})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan({"com.deepeshuniyal.app.configs","com.deepeshuniyal.app.controller"})
//@EntityScan(basePackages={"com.deepeshuniyal.app.test1.domain","com.deepeshuniyal.app.test2.domain"})
//@EnableTransactionManagement

@ComponentScan({"com.deepeshuniyal.app.configs","com.deepeshuniyal.app"})
@Configuration
@SpringBootApplication(scanBasePackages={"com.deepeshuniyal.app"})
public class SpringBootApp  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootApp.class);
	}
	

}