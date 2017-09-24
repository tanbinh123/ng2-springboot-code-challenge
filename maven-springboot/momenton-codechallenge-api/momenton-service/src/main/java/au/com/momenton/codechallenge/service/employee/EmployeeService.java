package au.com.momenton.codechallenge.service.employee;

import au.com.momenton.codechallenge.service.CodeChallengeServiceException;
import au.com.momenton.codechallenge.service.base.BaseService;
import au.momenton.codechallenge.domain.Employee;

import java.util.List;

/**
 * Created by WPerera on 9/23/2017.
 */
public interface EmployeeService extends BaseService {
    public List<Employee> getEmployeeHierarchy() throws CodeChallengeServiceException;
}
