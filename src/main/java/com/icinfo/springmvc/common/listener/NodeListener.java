package com.icinfo.springmvc.common.listener;

import com.icinfo.springmvc.common.config.ManagerConfiguration;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：启动ES服务
 */
public class NodeListener implements ServletContextListener {

    private Node node;

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     * .ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {

        // 获取Spring的bean
        ServletContext servletContext = sce.getServletContext();
        ApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        ManagerConfiguration config = (ManagerConfiguration) context
                .getBean("ManagerConfiguration");

        // 设置setting
        Map<String, String> settingMap = new HashMap<String, String>();
        String clusterName = config.getClusterName();
        String pathData = config.getPathData();
        String pathHome = config.getPathHome();
        settingMap.put("cluster.name", clusterName);
        settingMap.put("path.data", pathData);
        settingMap.put("path.home", pathHome);
        Settings settings = ImmutableSettings.settingsBuilder().put(settingMap)
                .build();

        // 创建并启动节点
        NodeBuilder nodeBuilder = NodeBuilder.nodeBuilder();
        nodeBuilder.settings(settings);
        node = nodeBuilder.node();
        //node.start();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        if (null != node) {
            // 关闭节点
            node.stop();
        }
    }
}


