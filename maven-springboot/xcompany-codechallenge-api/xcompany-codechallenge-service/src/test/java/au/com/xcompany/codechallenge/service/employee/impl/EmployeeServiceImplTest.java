package au.com.xcompany.codechallenge.service.employee.impl;

import au.com.xcompany.codechallenge.service.employee.helper.EmployeeHelper;
import au.com.xcompany.codechallenge.dao.employee.EmployeeDao;
import au.com.xcompany.codechallenge.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by WPerera on 9/25/2017.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeHelper employeeHelper;

    @Mock
    private EmployeeDao employeeDao;

    @Spy
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testWhenNoIndependentEmployees() throws Exception {

        // Arrange
        when(employeeDao.findIndependentEmployees()).thenReturn(new ArrayList<>());

        // Act
        List<Employee> employeeHierarchy = employeeService.getEmployeeHierarchy();

        // Assert
        assertNotNull(employeeHierarchy);
        assertTrue(employeeHierarchy.isEmpty());
    }

    @Test
    public void testWhenOnlyIndependentEmployees() throws Exception {

        // Arrange
        Long bossID = 5L;
        String bossName = "B";
        Long managerID = null;
        when(employeeDao.findIndependentEmployees()).thenReturn(this.getDaoEmployee(bossID, bossName, managerID));
        when(employeeHelper.populateSubordinates(any(), any())).thenReturn(this.getIndependentDomainEmployee(bossID, bossName));

        // Act
        List<Employee> employeeHierarchy = employeeService.getEmployeeHierarchy();

        // Assert
        assertNotNull(employeeHierarchy);
        assertFalse(employeeHierarchy.isEmpty());
        assertEquals(employeeHierarchy.get(0).getFirstName(), bossName);
        assertEquals(employeeHierarchy.get(0).getId(), bossID);
    }

    @Test
    public void testAnEmployeeHierarchy() throws Exception {

        // Arrange
        Long bossID = 5L;
        String bossName = "B";
        Long subID = 50L;
        String subName = "C";
        when(employeeDao.findIndependentEmployees()).thenReturn(this.getDaoEmployee(bossID, bossName, null));
        when(employeeHelper.populateSubordinates(any(), any())).thenReturn(
                this.getManagerDomainEmployee(bossID, bossName, subID, subName));

        // Act
        List<Employee> employeeHierarchy = employeeService.getEmployeeHierarchy();

        // Assert
        assertNotNull(employeeHierarchy);
        assertFalse(employeeHierarchy.isEmpty());
        assertEquals(employeeHierarchy.get(0).getFirstName(), bossName);
        assertEquals(employeeHierarchy.get(0).getId(), bossID);
        assertNull(employeeHierarchy.get(0).getManager());
        assertFalse(employeeHierarchy.get(0).getSubordinates().isEmpty());
        assertEquals(employeeHierarchy.get(0).getSubordinates().get(0).getId(), subID);
        assertEquals(employeeHierarchy.get(0).getSubordinates().get(0).getFirstName(), subName);
        assertNotNull(employeeHierarchy.get(0).getSubordinates().get(0).getManager());
        assertEquals(employeeHierarchy.get(0).getSubordinates().get(0).getManager().getFirstName(), bossName);
    }

    private List<au.com.xcompany.codechallenge.db.entity.Employee> getDaoEmployee(
            Long id, String firstName, Long managerID) {
        List<au.com.xcompany.codechallenge.db.entity.Employee> employees = new ArrayList<>();
        au.com.xcompany.codechallenge.db.entity.Employee independent =
                new au.com.xcompany.codechallenge.db.entity.Employee(id, firstName, managerID);
        employees.add(independent);
        return employees;
    }

    private Employee getIndependentDomainEmployee(
            Long id, String firstName) {
        Employee independent = new Employee();
        independent.setId(id);
        independent.setFirstName(firstName);
        independent.setManagerID(-1L);
        return independent;
    }

    private Employee getManagerDomainEmployee(
            Long managerID, String managerFirstName, Long subordinateID, String subordinateFirstName) {
        Employee manager = new Employee();
        manager.setId(managerID);
        manager.setFirstName(managerFirstName);
        manager.setManagerID(-1L);

        Employee sub = new Employee();
        sub.setManagerID(managerID);
        sub.setId(subordinateID);
        sub.setManager(manager);
        sub.setFirstName(subordinateFirstName);

        manager.getSubordinates().add(sub);

        return manager;
    }
}
