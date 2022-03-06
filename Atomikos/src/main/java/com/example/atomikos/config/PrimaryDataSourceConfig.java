package com.example.atomikos.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = "com.example.atomikos.service.primary.mapper",
        sqlSessionTemplateRef = "primarySqlSessionTemplate",
        sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataSourceConfig {

    @Value("${spring.datasource.druid.primarydb.type}")
    private String xaDataSourceClassName;

//    @Primary
//    @ConfigurationProperties(prefix = "primarydb")
//    @Bean(initMethod = "init", destroyMethod = "close", name = "primaryDataSource")
//    public DataSource primaryDataSource() {
//        return new AtomikosDataSourceBean();
//    }

    @SneakyThrows
    @Bean(name = "primaryDataSource")
    @DependsOn({"txManager"})
    public DataSource masterDataSource(PrimaryProperties properties) {

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        //DataSource不能直接使用Druid提供的DruidDataSource, 需要使用atomikos来包装一下Druid提供的DruidXADataSource，来支持XA规范
        DruidXADataSource druid = new DruidXADataSource();

        druid.setName(properties.getName());
        druid.setUrl(properties.getUrl());
        druid.setUsername(properties.getUsername());
        druid.setPassword(properties.getPassword());
        druid.setInitialSize(properties.getInitialSize());
        druid.setMinIdle(properties.getMinIdle());
        druid.setMaxActive(properties.getMaxActive());
        druid.setMaxWait(properties.getMaxWait());
        druid.setPoolPreparedStatements(properties.getPoolPreparedStatements());
        druid.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
        druid.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druid.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druid.setValidationQuery(properties.getValidationQuery());
        druid.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        druid.setTestWhileIdle(properties.getTestWhileIdle());
        druid.setTestOnBorrow(properties.getTestOnBorrow());
        druid.setTestOnReturn(properties.getTestOnReturn());
        druid.setFilters(properties.getFilters());

        atomikosDataSourceBean.setXaDataSource(druid);
        atomikosDataSourceBean.setXaDataSourceClassName(xaDataSourceClassName);
        atomikosDataSourceBean.setUniqueResourceName(properties.getName());
        atomikosDataSourceBean.setPoolSize(10);
        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(20);
        return atomikosDataSourceBean;
    }

//    @Primary
//    @Bean(name = "primarySqlSessionFactory")
//    public SqlSessionFactory primarySqlSessionFactory(
//            @Qualifier("primaryDataSource") DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean.getObject();
//    }

    @Bean("primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        // db底線轉駝峰
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        factoryBean.setConfiguration(configuration);
        //指定xml路徑
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/primary/*.xml"));
//        factoryBean.setPlugins(
//                //分页插件
//                new PaginationInterceptor(),
//                //乐观锁插件
//                new OptimisticLockerInterceptor()
//        );

//        GlobalConfig globalConfig = new GlobalConfig();
        // 自定义底层mapper通用方法
//        globalConfig.setSqlInjector(primaryMyLogicSqlInjector());
//        factoryBean.setGlobalConfig(globalConfig);
        return factoryBean.getObject();
    }

    @Bean("primarySqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Primary
//    @Bean("primaryMyLogicSqlInjector")
//    public MyLogicSqlInjector primaryMyLogicSqlInjector() {
//        return new MyLogicSqlInjector();
//    }
}
