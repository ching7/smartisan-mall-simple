package com.cyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 文件描述
 *
 * @ProjectName: mall-dev-template
 * @Package: com.cyn
 * @Date 2020/7/25 15:05
 * @Author:
 * @Version: 1.0
 * @Description: note
 **/
@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties
public class Provider {
    public static void main(String[] args) {
        SpringApplication.run(Provider.class, args);
    }
}
