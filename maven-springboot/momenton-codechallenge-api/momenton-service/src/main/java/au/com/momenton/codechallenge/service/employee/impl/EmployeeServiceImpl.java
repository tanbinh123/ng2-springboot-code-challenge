package au.com.momenton.codechallenge.service.employee.impl;

import au.com.momenton.codechallenge.service.CodeChallengeServiceException;
import au.com.momenton.codechallenge.service.base.impl.BaseServiceImpl;
import au.com.momenton.codechallenge.service.employee.EmployeeService;
import au.com.momenton.codechallenge.service.employee.helper.EmployeeHelper;
import au.momenton.codechallenge.dao.employee.EmployeeDao;
import au.momenton.codechallenge.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WPerera on 9/23/2017.
 */
@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeHelper employeeHelper;

    private EmployeeDao employeeDao;

    @PostConstruct
    public void init() {
        this.employeeDao = super.getDaoAccessor().onDemand(EmployeeDao.class);
    }

    @Override
    public List<Employee> getEmployeeHierarchy() throws CodeChallengeServiceException {
        List<Employee> employeeHierarchies = new ArrayList<>();
        List<au.momenton.codechallenge.db.entity.Employee> independents =
                employeeDao.findIndependentEmployees();
        if (independents != null && !independents.isEmpty()) {
            for (au.momenton.codechallenge.db.entity.Employee currIndependent: independents) {
                Employee independent = new Employee();
                independent.setId(currIndependent.getId());
                independent.setFirstName(currIndependent.getFirstName());
                Employee populatedIndependent =
                        this.employeeHelper.populateSubordinates(independent, employeeDao);
                employeeHierarchies.add(populatedIndependent);
            }
        }
        return employeeHierarchies;
    }
}
