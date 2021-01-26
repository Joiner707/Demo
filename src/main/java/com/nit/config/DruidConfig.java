package com.nit.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.nit.datasource.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid.qa")
    @Bean(name = "druidDataSourceQa")
    public DataSource druidDataSourceQa() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setBreakAfterAcquireFailure(true);
        dataSource.setFailFast(true);
        dataSource.setTimeBetweenConnectErrorMillis(60000*3);
        dataSource.setConnectionErrorRetryAttempts(3);
        return dataSource;
    }

//    @ConfigurationProperties(prefix = "spring.datasource.druid.testing")
//    @Bean(name = "druidDataSourceTesting")
//    public DataSource druidDataSourceTesting() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setBreakAfterAcquireFailure(true);
//        dataSource.setFailFast(true);
//        dataSource.setTimeBetweenConnectErrorMillis(60000*3);
//        dataSource.setConnectionErrorRetryAttempts(3);
//        return dataSource;
//    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");

        /**
         * initParams.put("allow", "localhost")：表示只有本机可以访问
         * initParams.put("allow", ""):表示后台允许所有访问
         */

        initParams.put("allow", "");
        //设置初始化参数
        bean.setInitParameters(initParams);
        return bean;
    }


    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("qa", druidDataSourceQa());
//        dataSourceMap.put("testing", druidDataSourceTesting());
        // 将 master 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultDataSource(druidDataSourceQa());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.nit.modle");    // 扫描Model
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));    // 扫描映射文件
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }


}
