package org.yangxin.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域设置
 *
 * @author yangxin
 * 2019/11/20 20:50
 */
@Configuration
@NoArgsConstructor
public class CorsConfig {
    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        // 添加cors配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8080");

        // 设置是否发送cookie信息
        corsConfiguration.setAllowCredentials(true);

        // 设置允许请求的方式
        corsConfiguration.addAllowedMethod("*");

        // 设置允许的header
        corsConfiguration.addAllowedHeader("*");

        // 为url添加映射路径
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        // 返回重新定义好的corsSource
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
