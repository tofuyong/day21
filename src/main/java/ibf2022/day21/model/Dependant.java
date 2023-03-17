package ibf2022.day21.model;

import java.sql.Date;

public class Dependant {
    private Integer id;
    private Integer employeeId;
    private String fullName;
    private String relationship;
    private Date birthdate;
    private Employee employee;


    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}

    public Integer getEmployeeId() {return this.employeeId;}
    public void setEmployeeId(Integer employeeId) {this.employeeId = employeeId;}

    public String getFullName() {return this.fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}
    
    public String getRelationship() {return this.relationship;}
    public void setRelationship(String relationship) {this.relationship = relationship;}

    public Date getBirthdate() {return this.birthdate;}
    public void setBirthdate(Date birthdate) {this.birthdate = birthdate;}
    
    public Employee getEmployee() {return this.employee;}
    public void setEmployee(Employee employee) {this.employee = employee;}

}
