package com.cyn.common.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文件描述
 *
 * @ProjectName: mall-dev-template
 * @Package: com.cyn.common.bean
 * @Date 2020/7/29 19:25
 * @Author:
 * @Version: 1.0
 * @Description: note
 **/
@Component
@Data
@ConfigurationProperties(prefix = "interceptorconfig.path") // 配置文件的前缀
public class InterceptorFilterConfig {
    private List<String> include;
}
