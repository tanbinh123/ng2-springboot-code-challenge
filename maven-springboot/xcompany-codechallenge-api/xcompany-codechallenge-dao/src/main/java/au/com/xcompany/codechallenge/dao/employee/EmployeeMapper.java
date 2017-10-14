package au.com.xcompany.codechallenge.dao.employee;

import au.com.xcompany.codechallenge.common.utils.CoverageIgnore;
import au.com.xcompany.codechallenge.db.entity.Employee;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WPerera on 9/23/2017.
 */
public class EmployeeMapper implements ResultSetMapper<Employee>
{
    @CoverageIgnore
    public Employee map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Employee(
                r.getLong("ID"),
                r.getString("FIRST_NAME"),
                r.getLong("MANAGER_ID"));
    }
}
