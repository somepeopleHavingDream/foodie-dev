package org.yangxin.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    @Configuration总结：
    1. @Configuration等价于<beans></beans>
    2. @Bean等价于<bean></bean>
    3. @ComponentScan等价于<context:component-scan base-package="xxx.xxx.xxx"/>
 */
/**
 * mvc
 *
 * @author yangxin
 * 2020/03/19 21:02
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    /*
        1. @Bean注解在返回实例的方法上，如果未通过@Bean指定bean的名称，则默认与标注的方法名相同；
        2. @Bean注解默认作用域为单例singleton作用域，可通过@Scope("prototype")设置为原型作用域；
        3. 既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、@Service、@Repository等注解注册bean，
        当然也需要配置@ComponentScan注解进行自动扫描
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * 实现静态资源的映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /*是所有目录，不包含子目录
        // /**是拦截所有目录及里面的子目录
        registry.addResourceHandler("/**")
                // 映射swagger2
                .addResourceLocations("classpath:/META-INF/resources/")
                // 映射本地静态资源
                .addResourceLocations("file:/home/yangxin/Projects/foodie-store/");
    }
}
