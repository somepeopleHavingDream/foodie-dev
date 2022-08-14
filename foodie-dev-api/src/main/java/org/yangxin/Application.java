package org.yangxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SuppressWarnings({"AlibabaClassMustHaveAuthor", "AlibabaCommentsMustBeJavadocFormat"})
@SpringBootApplication
// 扫描mybatis通用mapper所在包
@MapperScan(basePackages = "org.yangxin.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"org.yangxin", "org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
