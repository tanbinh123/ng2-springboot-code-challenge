package au.com.xcompany.codechallenge.db.scripts;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import java.util.Properties;

/**
 * Created by WPerera on 9/23/2017.
 */
public class DataMigrator {
    private static final Logger log = Logger.getLogger(DataMigrator.class);

    public static void main(String args[]) throws Exception {

        // Flyway configuration
        ApplicationContext flywayContext = new ClassPathXmlApplicationContext("classpath:META-INF/applicationContext-flyway.xml");
        Flyway flyway = flywayContext.getBean("flyway", Flyway.class);
        Properties p = new Properties();
        Resource resource = flywayContext.getResource("classpath:scripts.properties");
        p.load(resource.getInputStream());
        PropertyConfigurator.configure(p);

        // Flyway execution
        flyway.setLocations(p.getProperty("flyway.location"));
        log.debug("setting flyway location : " + p.getProperty("flyway.location"));

        flyway.setOutOfOrder(true);
        flyway.setValidateOnMigrate(true);
        flyway.migrate();
    }
}
