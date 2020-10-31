package com.csair;

import com.csair.listener.MapsInitializeListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author LiRenGui
 * @date 2020年10月31日18:13:52
 */

@SpringBootApplication
public class MapsApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MapsApplication.class);
        // 添加一个初始化监听器，对映射地址配置进行加载
        application.addListeners(new MapsInitializeListener("maps.properties"));
        application.run(args);
    }
}
