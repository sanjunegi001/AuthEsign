package com.auth.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.auth.model.EsignSession;
import com.auth.model.User;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "UserEntityManager", 
		transactionManagerRef = "UserTransactionManager", 
		basePackages = "com.auth.repository"
)
@EnableTransactionManagement
public class DBConfig {
	
	 
	    @Primary
	    @Bean(name = "UserTransactionManager")
		public PlatformTransactionManager mysqlTransactionManager(@Qualifier("UserEntityManager") EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
		}
	  
	   
	    @Primary
	    @Bean(name = "UserEntityManager")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory1(EntityManagerFactoryBuilder builder,@Qualifier("spring.datasource") DataSource dataSource) {
			return builder
						.dataSource(dataSource)
						.properties(hibernateProperties())
						.packages(User.class)
						.persistenceUnit("UserPU")
						.build();
		}
	    @Primary
	    @Bean(name = "UserEntityManager")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder,@Qualifier("spring.datasource") DataSource dataSource) {
			return builder
						.dataSource(dataSource)
						.properties(hibernateProperties())
						.packages(EsignSession.class)
						.persistenceUnit("EsignSessionPU")
						.build();
		}
	   
	    
	    
	    @Primary
	    @Bean(name = "spring.datasource")
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public DataSource dataSource1() {
	        return DataSourceBuilder
	                .create()
	                .build();
	    }
	    
	    private Map hibernateProperties() {
	        final Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	        return hibernateProperties;
	    }
}

