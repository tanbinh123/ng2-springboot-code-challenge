package au.momenton.codechallenge.db.scripts;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import java.util.Properties;

/**
 * Created by WPerera on 9/23/2017.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String args[]) throws Exception {
        ApplicationContext flywayContext = new ClassPathXmlApplicationContext("classpath:META-INF/applicationContext-flyway.xml");
        Flyway flyway = flywayContext.getBean("flyway", Flyway.class);
        Properties p = new Properties();
        Resource resource = flywayContext.getResource("classpath:scripts.properties");
        p.load(resource.getInputStream());
        flyway.setLocations(p.getProperty("flyway.location"));
        System.out.println("setting flyway location : " + p.getProperty("flyway.location"));

        flyway.setOutOfOrder(true);
        flyway.setValidateOnMigrate(false);
        flyway.migrate();
    }
}
