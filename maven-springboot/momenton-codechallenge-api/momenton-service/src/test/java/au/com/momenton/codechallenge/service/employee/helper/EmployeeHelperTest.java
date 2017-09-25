package au.com.momenton.codechallenge.service.employee.helper;

import au.momenton.codechallenge.dao.employee.EmployeeDao;
import au.momenton.codechallenge.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by WPerera on 9/25/2017.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeHelperTest {

    private static final Long MANAGER_ID = 5L;
    private static final String MANAGER_NAME = "A";
    private static final Long SUB_ID = 10L;
    private static final String SUB_NAME = "B";

    @Mock
    private EmployeeDao employeeDao;

    @Spy
    @InjectMocks
    private EmployeeHelper employeeHelper;

    @Before
    public void init() {
        // Pre-Arrange
        when(employeeDao.findSubordinatesByManagerId(any())).thenAnswer(new Answer<List>() {
            @Override
            public List answer(InvocationOnMock invocationOnMock) throws Throwable {
                Long localManagerID = (Long) invocationOnMock.getArguments()[0];
                if (localManagerID.equals(MANAGER_ID)) {
                    au.momenton.codechallenge.db.entity.Employee sub =
                            new au.momenton.codechallenge.db.entity.Employee(SUB_ID, SUB_NAME, MANAGER_ID);
                    List<au.momenton.codechallenge.db.entity.Employee> subs = new ArrayList<>();
                    subs.add(sub);
                    return subs;
                } else {
                    // we assume all other employees have no subs in this set of tests
                    return new ArrayList();
                }
            }
        });
    }

    @Test
    public void testPopulateSubordinatesForSubordinate() throws Exception {

        // Arrange
        Employee subordinate = new Employee();
        subordinate.setId(SUB_ID);
        subordinate.setFirstName(SUB_NAME);

        // Act
        Employee populatedSubordinate = employeeHelper.populateSubordinates(subordinate, employeeDao);

        // Assert
        assertNotNull(populatedSubordinate);
        assertEquals(populatedSubordinate.getFirstName(), SUB_NAME);
        assertEquals(populatedSubordinate.getId(), SUB_ID);
        assertTrue(populatedSubordinate.getSubordinates().isEmpty());
    }

    @Test
    public void testPopulateSubordinatesForManager() throws Exception {

        // Arrange
        Employee manager = new Employee();
        manager.setId(MANAGER_ID);
        manager.setFirstName(MANAGER_NAME);

        // Act
        Employee populatedManager = employeeHelper.populateSubordinates(manager, employeeDao);

        // Assert
        assertNotNull(populatedManager);
        assertEquals(populatedManager.getFirstName(), MANAGER_NAME);
        assertEquals(populatedManager.getId(), MANAGER_ID);
        assertFalse(populatedManager.getSubordinates().isEmpty());
        assertEquals(populatedManager.getSubordinates().size(), 1);
        assertEquals(populatedManager.getSubordinates().get(0).getId(), SUB_ID);
        assertEquals(populatedManager.getSubordinates().get(0).getFirstName(), SUB_NAME);
        assertTrue(populatedManager.getSubordinates().get(0).getSubordinates().isEmpty());
    }
}
