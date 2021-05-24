package nix.alevel.db;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionHandler {
    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    public static Connection getConnection() throws SQLException {
        Properties props = loadProperties();
        String url = props.getProperty("url");
        log.info("Connecting to {}", url);
        Connection connection = DriverManager.getConnection(url, "lucy", "lucy26");
        log.info("Connected successfully to database");
        System.out.println();
        return connection;
    }
    private static Properties loadProperties() {

        Properties props = new Properties();

        try(InputStream input = ConnectionHandler.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return props;
    }
}
