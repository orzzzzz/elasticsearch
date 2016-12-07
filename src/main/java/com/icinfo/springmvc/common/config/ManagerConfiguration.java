package com.icinfo.springmvc.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 描述：获取配置文件内容
 */
@Service("ManagerConfiguration")
public class ManagerConfiguration {

    @Value("${esserver.cluster.name}")
    private String clusterName;

    @Value("${esserver.path.home}")
    private String pathHome;

    @Value("${esserver.path.data}")
    private String pathData;

    public String getClusterName() {
        return clusterName;
    }

    public String getPathData() {
        return pathData;
    }

    public String getPathHome() {
        return pathHome;
    }

}
