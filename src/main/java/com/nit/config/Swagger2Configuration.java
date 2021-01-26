package com.nit.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

//@EnableKnife4j
@Configuration
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {

        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(3001).message("参数不正确").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(3002).message("查无数据").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(3003).message("参数一样").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(3004).message("重复请求").responseModel(new ModelRef("ApiError")).build());


        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nit"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试api文档")
                .description("能活一天是一天")
                .termsOfServiceUrl("http://www.baidu.com")
                .version("1.0")
                .build();
    }
}
