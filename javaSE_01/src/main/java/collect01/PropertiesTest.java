package collect01;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author：Dawn
 * @Date：2021/2/20 23:58
 * @Desc： 测试读取配置文件
 */
public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        properties.load(new InputStreamReader(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"),
                "utf-8"
        ));

        String host = properties.getProperty("redis.host");
        String port = properties.getProperty("redis.port");

        System.out.println("host: " + host);
        System.out.println("port: " + port);
    }
}
