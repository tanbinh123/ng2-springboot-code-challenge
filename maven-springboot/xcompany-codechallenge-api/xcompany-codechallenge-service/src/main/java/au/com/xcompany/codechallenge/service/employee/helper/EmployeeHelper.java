package au.com.xcompany.codechallenge.service.employee.helper;

import au.com.xcompany.codechallenge.dao.employee.EmployeeDao;
import au.com.xcompany.codechallenge.domain.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by WPerera on 9/24/2017.
 */
@Component
public class EmployeeHelper {

    public Employee populateSubordinates(Employee localManager, EmployeeDao employeeDao) {
        List<au.com.xcompany.codechallenge.db.entity.Employee> subordinates =
                employeeDao.findSubordinatesByManagerId(localManager.getId());
        if (subordinates != null && !subordinates.isEmpty()) {
            for (au.com.xcompany.codechallenge.db.entity.Employee currSubordinate: subordinates) {
                Employee subordinate = new Employee();
                subordinate.setId(currSubordinate.getId());
                subordinate.setFirstName(currSubordinate.getFirstName());
                subordinate.setManager(localManager);
                subordinate.setManagerID(localManager.getId());
                Employee populatedSubordinate = this.populateSubordinates(subordinate, employeeDao);
                localManager.getSubordinates().add(populatedSubordinate);
            }
        }
        return localManager;
    }
}
