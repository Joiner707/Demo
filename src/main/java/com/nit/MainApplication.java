package com.nit;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@NacosPropertySource(dataId = "precise", autoRefreshed = true)
@EnableSwagger2
@EnableAsync
@PropertySource(value = {"classpath:application.yml"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
@MapperScan(basePackages = "com.nit.dao")
public class MainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        try {
            //SpringBoot的netty和elasticsearch的netty相关jar冲突
            System.setProperty("es.set.netty.runtime.available.processors", "false");
            SpringApplication.run(MainApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
