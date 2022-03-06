package com.example.atomikos.config;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
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
        basePackages = "com.example.atomikos.service.secondary.mapper",
        sqlSessionTemplateRef = "secondarySqlSessionTemplate",
        sqlSessionFactoryRef = "secondarySqlSessionFactory")
public class SecondaryDataSourceConfig {

    @Value("${spring.datasource.druid.secondarydb.type}")
    private String xaDataSourceClassName;

//    @ConfigurationProperties(prefix = "secondarydb")
//    @Bean(initMethod = "init", destroyMethod = "close", name = "secondaryDataSource")
//    public DataSource secondDataSource() {
//        return new AtomikosDataSourceBean();
//    }

    @SneakyThrows
    @Bean(name = "secondaryDataSource")
    @DependsOn({"txManager"})
    public DataSource secondDataSource(SecondaryProperties properties) {

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        //DataSource不能直接使用Druid提供的DruidDataSource, 需要使用atomikos来包装一下Druid提供的DruidXADataSource，来支持XA规范
//        DruidXADataSource xaDataSource = new DruidXADataSource();
        SQLServerXADataSource xaDataSource = new SQLServerXADataSource();

//        xaDataSource.setServerName(properties.getName());
        xaDataSource.setURL(properties.getUrl());
        xaDataSource.setUser(properties.getUsername());
        xaDataSource.setPassword(properties.getPassword());

        atomikosDataSourceBean.setXaDataSource(xaDataSource);
        atomikosDataSourceBean.setXaDataSourceClassName(xaDataSourceClassName);
        atomikosDataSourceBean.setUniqueResourceName(properties.getName());
        atomikosDataSourceBean.setPoolSize(10);
        atomikosDataSourceBean.setMinPoolSize(5);
        atomikosDataSourceBean.setMaxPoolSize(20);
        atomikosDataSourceBean.setTestQuery(properties.getValidationQuery());
        return atomikosDataSourceBean;
    }

//    @Bean(name = "secondarySqlSessionFactory")
//    public SqlSessionFactory secondarySqlSessionFactory(
//            @Qualifier("secondaryDataSource") DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean.getObject();
//    }

    @Bean("secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
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
                .getResources("classpath:mapper/secondary/*.xml"));
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

    @Bean("secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
