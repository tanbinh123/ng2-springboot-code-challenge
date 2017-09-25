package au.momenton.codechallenge.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by WPerera on 9/25/2017.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeDomainTest {

    @Test
    public void testEmployeeDomainObject() throws Exception {

        // Arrange
        String employeeName = "B";
        String managerName = "A";
        String subordinateName = "C";
        Long managerId = 5L;
        Long empId = 10L;
        Long subId = 15L;

        // Act
        Employee manager = new Employee();
        manager.setId(managerId);
        manager.setFirstName(managerName);

        Employee employee = new Employee();
        employee.setFirstName(employeeName);
        employee.setId(empId);
        employee.setManager(manager);
        employee.setManagerID(managerId);

        List<Employee> subs = new ArrayList<>();
        Employee sub = new Employee();
        sub.setFirstName(subordinateName);
        sub.setManager(employee);
        sub.setManagerID(empId);
        sub.setId(subId);
        subs.add(sub);

        employee.setSubordinates(subs);

        // Assert
        assertEquals(employee.getId(), empId);
        assertEquals(employee.getFirstName(), employeeName);
        assertEquals(employee.getManager(), manager);
        assertEquals(employee.getManagerID(), managerId);
        assertNotNull(employee.getSubordinates());
        assertFalse(employee.getSubordinates().isEmpty());
        assertEquals(employee.getSubordinates().get(0).getFirstName(), subordinateName);
    }
}
