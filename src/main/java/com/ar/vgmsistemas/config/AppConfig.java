package com.ar.vgmsistemas.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.http.converter.HttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

//import org.springframework.http.converter.HttpMessageConverter;
//import org.codehaus.jackson.map.Module;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.ar.vgmsistemas.bo", "com.ar.vgmsistemas.controller", "com.ar.vgmsistemas.controllerUltraweb",
		"com.ar.vgmsistemas.controllerServicios", "com.ar.vgmsistemas.dao.hibernate", "com.ar.vgmsistemas.config",
		"com.ar.vgmsistemas.dao.filesystem", "com.ar.vgmsistemas.security" })
@PropertySource(value = { "classpath:application.properties" })
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Autowired
	private Environment env;
	public static String sqliteUrl;
	public static String sqliteEmpty = "ccinfo-empty";
	public static String extensionSqlite = ".sqlite";
	public static String nombreBase = "ccinfo-";
	// public static String idVendedor;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * Initialize dataSource
	 * 
	 * @return DataSource
	 */
	@Bean(destroyMethod = "close")
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(Integer.parseInt(env.getRequiredProperty("datasource.maximumpoolsize")));
		config.setDataSourceClassName(env.getRequiredProperty("datasource.driver"));
		config.addDataSourceProperty("serverName", env.getRequiredProperty("datasource.servername"));
		// config.addDataSourceProperty("portNumber",
		// env.getRequiredProperty("datasource.portnumber"));
		config.addDataSourceProperty("databaseName", env.getRequiredProperty("datasource.databasename"));
		config.addDataSourceProperty("user", env.getRequiredProperty("datasource.user"));
		config.addDataSourceProperty("password", env.getRequiredProperty("datasource.password"));

		HikariDataSource ds = new HikariDataSource(config);
		sqliteUrl = env.getRequiredProperty("datasourceSqlite.directorio");
		return ds;
	}

	/**
	 * Initialize hibernate properties
	 * 
	 * @return Properties
	 */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getRequiredProperty("hibernate.batch.size"));
		// properties.put(AvailableSettings.HBM2DDL_AUTO,
		// env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
				env.getRequiredProperty("hibernate.current.session.context.class"));
		// properties.put(AvailableSettings.ISOLATION,
		// Connection.TRANSACTION_READ_UNCOMMITTED);
		properties.put(AvailableSettings.CONNECTION_HANDLING, "DELAYED_ACQUISITION_AND_HOLD"); // Ver si realmente sirve
																								// o quedo al pedo
		properties.put("hibernate.mapping.precedence", "class, hbm");
		return properties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setPackagesToScan(new String[] { "com.ar.vgmsistemas.dto" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaProperties(getHibernateProperties());
		return emf;
	}

	@Bean
	public PlatformTransactionManager transactionManager(/* @Qualifier("emSQLServer") */EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// Here we add our custom-configured HttpMessageConverter
		converters.add(mappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	@Bean
	  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	      MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	      ObjectMapper objectMapper = jsonConverter.getObjectMapper();
	      Hibernate5Module hibernate5Module = new Hibernate5Module();
	      hibernate5Module.configure(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION, false);
	      objectMapper.registerModule(hibernate5Module);
	      return jsonConverter;
	  }

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter converter : converters) {
			if (converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter) {
				ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
				Hibernate5Module hibernate5Module = new Hibernate5Module();
				hibernate5Module.configure(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION, false);
				mapper.registerModule(hibernate5Module);
				// replace Hibernate4Module() with the proper class for your hibernate version.
			}
		}
	}
}