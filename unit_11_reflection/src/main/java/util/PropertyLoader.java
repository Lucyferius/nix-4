package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertyLoader {
    public static Properties loadProperties(String file) {

        Properties props = new Properties();

        try(InputStream input = PropertyLoader.class.getResourceAsStream(file)) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return props;
    }
}
