/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.centralplains.daas;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author flysLi
 * @ClassName SwaggerConfig
 * @Decription TODO
 * @Date 2019/1/17 10:25
 * @Version 1.0
 */
@ComponentScan("org.centralplains.daas.controller")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("DAAS数据接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("org.centralplains.daas.controller")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DAAS数据分析系统(DMS) APIs")
                .description("数据分析、预约采集平台")
                .termsOfServiceUrl("https://book.qidian.com")
                .contact("contact")
                .version("1.0")
                .build();
    }
}
