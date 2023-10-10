package com.zxiaoyao.openai.api.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description swagger 组件
 * @Author hlantian
 * @Date Created in 16:07 2023/6/16
 * @Version 1.0
 */
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Value("${spring.application.name:LAP}")
    private String appName;

    @Bean
    public Docket sysApi() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name("Authorization").description("CAS_TICKET:Bearer TK-XXXX").defaultValue("Bearer TK-XXXX").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lyutone.lap"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    /**
     * 增加API相关信息
     * @return AIP 信息
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(appName+" 微服务")
                .description("微服务")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}
