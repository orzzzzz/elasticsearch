package com.icinfo.springmvc.common.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 描述：io.searchbox.jest 版本0.0.2  构造jestClient配置
 */
//@Configuration
//public class SpringConfiguration {
//
//    public @Bean
//    ClientConfig clientConfig() {
//
//        String connectionUrl = "http://192.168.5.115:9200";
//
//        ClientConfig clientConfig = new ClientConfig();
//        LinkedHashSet servers = new LinkedHashSet();
//        servers.add(connectionUrl);
//        clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);
//        clientConfig.getClientFeatures().put(ClientConstants.IS_MULTI_THREADED, false);
//        return clientConfig;
//    }
//
//    public @Bean
//    JestClient jestClient() {
//        JestClientFactory factory = new JestClientFactory();
//        factory.setClientConfig(clientConfig());
//        return factory.getObject();
//    }
//}

/**
 * 描述：io.searchbox.jest 版本0.1.2  构造jestClient配置
 */
@Configuration
public class SpringConfiguration {
    @Value("${es.connection.url}")
    private String connectionUrl;

    // SpringConfiguration
    public @Bean HttpClientConfig httpClientConfig() {
        //String connectionUrl = "http://192.168.5.39:9200";
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder(connectionUrl).multiThreaded(true).build();
        return httpClientConfig;
    }

    public @Bean JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(httpClientConfig());
        return factory.getObject();
    }
}
