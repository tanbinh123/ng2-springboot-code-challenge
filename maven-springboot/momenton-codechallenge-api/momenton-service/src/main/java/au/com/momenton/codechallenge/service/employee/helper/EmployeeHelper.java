package au.com.momenton.codechallenge.service.employee.helper;

import au.momenton.codechallenge.dao.employee.EmployeeDao;
import au.momenton.codechallenge.domain.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by WPerera on 9/24/2017.
 */
@Component
public class EmployeeHelper {

    public Employee populateSubordinates(Employee localManager, EmployeeDao employeeDao) {
        List<au.momenton.codechallenge.db.entity.Employee> subordinates =
                employeeDao.findSubordinatesByManagerId(localManager.getId());
        if (subordinates != null && !subordinates.isEmpty()) {
            for (au.momenton.codechallenge.db.entity.Employee currSubordinate: subordinates) {
                Employee subordinate = new Employee();
                subordinate.setId(currSubordinate.getId());
                subordinate.setFirstName(currSubordinate.getFirstName());
                subordinate.setManager(localManager);
                Employee populatedSubordinate = this.populateSubordinates(subordinate, employeeDao);
                localManager.getSubordinates().add(populatedSubordinate);
            }
        }
        return localManager;
    }
}
