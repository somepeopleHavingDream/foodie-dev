package org.yangxin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yangxin
 * 2019/11/19 21:23
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 配置swagger2核心配置docket
     * 访问路径是http:localhost:8088/swagger-ui.html、http:localhost:8088/doc.html
     *
     * @return Docket对象
     */
    @Bean
    public Docket createRestApi() {
        // 指定api类型为swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                // 用于定义api文档汇总信息
                .apiInfo(apiInfo())
                .select()
                // 指定controller包
                .apis(RequestHandlerSelectors.basePackage("org.yangxin.controller"))
                // 所有controller
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档页标题
                .title("天天吃货 电商平台接口api")
                // 联系人信息
                .contact(new Contact("yangxin",
                        "localhost:8088",
                        "1393003255@qq.com"))
                // 详细信息
                .description("专为天天吃货提供的api文档")
                // 文档版本号
                .version("1.0.0")
                // 网站地址
                .termsOfServiceUrl("https://www.baidu.com")
                .build();
    }
}
