package au.com.xcompany.codechallenge.db.entity;

/**
 * Created by WPerera on 9/23/2017.
 */
public class Employee extends BaseEntity<Long> {
    private String firstName;
    private Long managerId;

    public Employee(Long id, String firstName, Long managerId) {
        super(id);
        this.firstName = firstName;
        this.managerId = managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getManagerId() {
        return managerId;
    }
}
