package ibf2022.day21.model;

import java.sql.Date;

public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date dob;

    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}

    public String getFirstName() {return this.firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public Date getDob() {return this.dob;}
    public void setDob(Date dob) {this.dob = dob;}
}
