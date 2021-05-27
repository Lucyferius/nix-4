import model.AppProperties;
import util.PropertyHandler;
import util.PropertyLoader;

import java.util.Properties;

public class Runner {
    public static void main(String[] args) {
        Properties properties = PropertyLoader.loadProperties("/app.properties");
        AppProperties appProperties = PropertyHandler.initPropertyObject(properties, AppProperties.class);
        System.out.println(appProperties);
    }
}
