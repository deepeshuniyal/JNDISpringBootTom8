package com.deepeshuniyal.app.configs;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class TomcatConfigs {
	
	 @Bean
	 public TomcatEmbeddedServletContainerFactory tomcatFactory() {  // if datasouce defind in tomcat xml configuration then no need to create this bean
	        
		 return new TomcatEmbeddedServletContainerFactory() {
	          
	        	@Override
	            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) 
	        	{
	                tomcat.enableNaming();
	                return super.getTomcatEmbeddedServletContainer(tomcat);
	            }
	        	
	            @Override
	            protected void postProcessContext(Context context) 
	            {
	                ContextResource resource = new ContextResource();

	                resource.setType(DataSource.class.getName());
	                resource.setName("deepeshuniyal");
	                resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
	                resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
	                resource.setProperty("url", "jdbc:mysql://localhost/test");
	                resource.setProperty("username", "root");
	                resource.setProperty("password", "Bagoli@123");
	                
	                context.getNamingResources().addResource(resource);
	            }
	        };
	    }
	 
//	    @Bean
//	    public DataSource jndiDataSource() throws IllegalArgumentException, NamingException 
//	    {
////	        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();    
////	        bean.setJndiName("java:/comp/env/jdbc/deepeshuniyal");
////	        bean.setProxyInterface(DataSource.class);
////	        bean.setLookupOnStartup(true);
////	        bean.afterPropertiesSet();
////	        
////	        return (DataSource) bean.getObject();
//	    	JndiDataSourceLookup dataSource = new JndiDataSourceLookup();
//	        dataSource.setResourceRef(true);
//	        return dataSource.getDataSource("jdbc/deepeshuniyal");
//	    }
}

//URL: http://localhost:8080/springboot-jndi-lookup/get-cust-info