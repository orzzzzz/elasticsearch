package com.icinfo.springmvc.client;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


/**
 * 描述：es客户端管理
 */
public class ClientManage {
    private static Client client;

    private static TransportClient transportClient;
    /**
     * 获取节点客户端
     * @return
     */
    public static Client getNodeClient(){
        client = NodeBuilder.nodeBuilder().clusterName("elasticsearch").node().client();
        return client;
    }

    public static TransportClient getTransportClient() throws Exception {
        Map<String, String> settingMap = new HashMap<String, String>();
        //String clusterName = "elasticsearch";
        //String pathData = "D:/work/proTmp/gsearch/indexPath";
        //String pathHome = "";
        //settingMap.put("cluster.name", clusterName);
        //settingMap.put("path.data", pathData);
        //settingMap.put("path.home", pathHome);
        Settings settings = ImmutableSettings.settingsBuilder().put(settingMap)
                .put("client.transport.sniff", true)
                .build();

        Class<?> clazz = Class.forName(TransportClient.class.getName());
        Constructor<?> constructor = clazz.getDeclaredConstructor(Settings.class);
        constructor.setAccessible(true);
        transportClient = (TransportClient) constructor.newInstance(settings);
        transportClient.addTransportAddress(new InetSocketTransportAddress("192.168.5.29", 9300));

        return transportClient;
    }
}
