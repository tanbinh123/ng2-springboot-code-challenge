package au.com.xcompany.codechallenge.dao.employee;

import au.com.xcompany.codechallenge.db.entity.Employee;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by WPerera on 9/23/2017.
 */
public interface EmployeeDao {

    @SqlQuery("select id, first_name, manager_id from employee where manager_id is null")
    @Mapper(EmployeeMapper.class)
    List<Employee> findIndependentEmployees();

    @SqlQuery("select id, first_name, manager_id from employee where manager_id = :manager_id")
    @Mapper(EmployeeMapper.class)
    List<Employee> findSubordinatesByManagerId(@Bind("manager_id") Long managerID);
}
