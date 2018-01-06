package com.darkhorse.service.dao.source;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@MapperScan("com.darkhorse.service.dao")
public class DataSourceConfig {

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setConnectionTimeout(connectionTimeoutMs);
		dataSource.setIdleTimeout(idleTimeoutMs);
		dataSource.setMaxLifetime(maxLifetimeMs);
		dataSource.setMinimumIdle(minIdle);
		dataSource.setMaximumPoolSize(maxPoolSize);
		dataSource.setConnectionTestQuery(connectionTestQuery);
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setConfiguration(configuration());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setMapperLocations(resolver.getResources("com/darkhorse/service/dao/**/*.xml"));
		sqlSessionFactory.setPlugins(new Interceptor[] {pageInterceptor()});
		return sqlSessionFactory.getObject();
	}

	@Bean
	public org.apache.ibatis.session.Configuration configuration() {
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setCacheEnabled(true);
		configuration.setLazyLoadingEnabled(true);
		configuration.setAggressiveLazyLoading(false);
		configuration.setMultipleResultSetsEnabled(true);
		configuration.setUseColumnLabel(true);
		configuration.setUseGeneratedKeys(false);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
		configuration.setDefaultExecutorType(ExecutorType.BATCH);
		configuration.setDefaultStatementTimeout(10);
		configuration.setLogImpl(Slf4jImpl.class);
		return configuration;
	}
	
	@Bean
	public PageInterceptor pageInterceptor() {
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager =  new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.connectionTimeoutMs}")
	private long connectionTimeoutMs;

	@Value("${jdbc.idleTimeoutMs}")
	private long idleTimeoutMs;

	@Value("${jdbc.maxLifetimeMs}")
	private long maxLifetimeMs;

	@Value("${jdbc.maxPoolSize}")
	private int maxPoolSize;

	@Value("${jdbc.minIdle}")
	private int minIdle;

	@Value("${jdbc.connectionTestQuery}")
	private String connectionTestQuery;
}