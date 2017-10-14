package au.com.xcompany.codechallenge.db.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by WPerera on 9/25/2017.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeDBEntityTest {

    @Test
    public void testEmployeeDBEntity() {
        // Arrange
        Long employeeID = 10L;
        String firstName = "A";
        Long managerID = 5L;

        // Act
        Employee employee = new Employee(employeeID, firstName, managerID);

        // Assert
        assertEquals(employee.getId(), employeeID);
        assertEquals(employee.getFirstName(), firstName);
        assertEquals(employee.getManagerId(), managerID);
    }
}
