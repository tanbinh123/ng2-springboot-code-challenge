package au.com.xcompany.codechallenge.service.base;

import org.skife.jdbi.v2.DBI;

/**
 * Created by WPerera on 9/24/2017.
 */
public interface BaseService {
    DBI getDaoAccessor();
}
