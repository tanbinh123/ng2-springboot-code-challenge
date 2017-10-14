package au.com.xcompany.codechallenge.service.employee;

import au.com.xcompany.codechallenge.service.CodeChallengeServiceException;
import au.com.xcompany.codechallenge.service.base.BaseService;
import au.com.xcompany.codechallenge.domain.Employee;

import java.util.List;

/**
 * Created by WPerera on 9/23/2017.
 */
public interface EmployeeService extends BaseService {
    List<Employee> getEmployeeHierarchy() throws CodeChallengeServiceException;
}
