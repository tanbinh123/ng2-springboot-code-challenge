package au.momenton.codechallenge.dao;

import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by WPerera on 9/23/2017.
 */
@Service
public class PersistenceConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DBI dbiBean() {
        DBI dbi = new DBI(dataSource);
        return dbi;
    }
}
