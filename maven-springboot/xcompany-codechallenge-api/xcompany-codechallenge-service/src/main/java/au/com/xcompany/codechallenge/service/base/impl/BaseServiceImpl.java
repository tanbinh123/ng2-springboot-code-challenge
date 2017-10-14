package au.com.xcompany.codechallenge.service.base.impl;

import au.com.xcompany.codechallenge.service.base.BaseService;
import au.com.xcompany.codechallenge.common.utils.CoverageIgnore;
import org.skife.jdbi.v2.DBI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by WPerera on 9/24/2017.
 */
public abstract class BaseServiceImpl implements BaseService {

    @Autowired
    private DBI dbi;

    @CoverageIgnore
    public DBI getDaoAccessor() {
        return dbi;
    }
}
