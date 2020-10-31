package com.csair.listener;

import com.csair.utlis.MapsUtils;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;



/**
 * URL映射的初始化监听器
 *
 * @author LiRenGui
 * @date 2020年10月31日18:31:16
 */
public class MapsInitializeListener implements ApplicationListener<ApplicationStartingEvent> {

    private String propertiesFileName;

    public MapsInitializeListener(String propertiesFileName) {
        super();
        this.propertiesFileName = propertiesFileName;
    }

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        MapsUtils.loadAllProperties(propertiesFileName);
    }
}

