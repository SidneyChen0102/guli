package com.sidney.vod;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
        import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.stereotype.Component;

        import javax.xml.crypto.Data;

/**
 * @author Sidney
 * @data 2020/5/26  16:25
 * @description
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.sidney"})
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}
