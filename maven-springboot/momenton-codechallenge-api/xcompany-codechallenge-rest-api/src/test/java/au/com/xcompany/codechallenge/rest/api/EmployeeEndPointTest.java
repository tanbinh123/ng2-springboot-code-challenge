package au.com.xcompany.codechallenge.rest.api;

import au.com.xcompany.codechallenge.service.CodeChallengeServiceException;
import au.com.xcompany.codechallenge.service.employee.EmployeeService;
import au.com.xcompany.codechallenge.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by WPerera on 9/25/2017.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeEndPointTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeEndPoint employeeEndPoint;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeEndPoint).build();
    }

    @Test
    public void testEmployeeHierarchy() throws Exception {

        // Arrange
        String employeeName = "A";
        String subordinateName = "B";
        Employee employee = this.getHierarchicalEmployee(employeeName, subordinateName);
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(employee);

        when(employeeService.getEmployeeHierarchy()).thenReturn(expectedEmployees);

        // Act
        mockMvc.perform(get("/codechallenge/employees/list/hierarchy")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()) // Assert
                .andExpect(jsonPath("$[0].firstName", is(employeeName)));
    }

    @Test
    public void testEmployeeHierarchyErrorScenario() throws Exception {

        // Arrange
        String employeeServiceEndPointError = "Sample Error";
        when(employeeService.getEmployeeHierarchy()).thenThrow(new CodeChallengeServiceException(employeeServiceEndPointError));

        // Act
        mockMvc.perform(get("/codechallenge/employees/list/hierarchy")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError()); // Assert
    }

    private Employee getHierarchicalEmployee(String employeeName, String subordinateName) {
        Employee employee = new Employee();
        employee.setFirstName(employeeName);

        Employee subordinate = new Employee();
        subordinate.setFirstName(subordinateName);

        employee.getSubordinates().add(subordinate);
        return employee;
    }
}
