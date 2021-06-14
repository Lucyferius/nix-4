package nix.alevel.finance;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import nix.alevel.finance.controller.JDBCMode;
import nix.alevel.finance.controller.JPAMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        // command args -> loginForDB, passForDB, userEmail, modeOfWork (1-jpa, 2-jdbc)
        if(args.length!=4) {
            System.out.println("Check the command args -> loginForDB, passForDB, userEmail, modeOfWork (1-jpa, 2-jdbc) ");
            System.exit(0);
        }
        Map<String,String> properties = new HashMap<>();
        properties.put("login",args[0]);
        properties.put("pass",args[1]);
        properties.put("email",args[2]);
        properties.put("mode",args[3]);

        if(properties.get("mode").equals("1")){
            Configuration configuration = new Configuration().configure()
                    .setProperty("hibernate.connection.username", properties.get("login"))
                    .setProperty("hibernate.connection.password", properties.get("pass"));

            try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                 Session session = sessionFactory.openSession()){
                Validator validator = validatorFactory.getValidator();

                JPAMode runFirstMode = new JPAMode(session, validator, properties.get("email"));
                try {
                    runFirstMode.run();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    System.out.println(e.getMessage());
                }
            }catch(Exception e){
                logger.error(e.getMessage());
                System.out.println(e.getMessage());
            }
        }else if(properties.get("mode").equals("2")){
            String fileName = "operations.csv";
            Properties props = loadProperties();
            String url = props.getProperty("url");
            try (Connection connection = DriverManager.getConnection(url, properties.get("login"), properties.get("pass"))){
               JDBCMode jdbcMode = new JDBCMode(connection, properties.get("email"),fileName);
               jdbcMode.run();
            } catch (Exception e) {
                logger.error(e.getMessage());
                System.out.println(e.getMessage());
            }
        }else {
            logger.info("Incorrect mode of work");
            System.exit(0);
        }

    }
    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = Main.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }
}
