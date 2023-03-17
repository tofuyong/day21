package ibf2022.day21.model;

import java.util.List;

public class Employee {
    private Integer id;

    // SB's built in mapping mechanism: firstName (Entity) --> first_name (mySQL) or firstname (Entity) --> firstname (mySQL)
    private String firstName;
    private String lastName;
    private Float salary;

    // Can include a list of dependant if want to link or show dependants
    private List<Dependant> dependants;


    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Float getSalary() {
        return this.salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }


    public List<Dependant> getDependants() {
        return this.dependants;
    }

    public void setDependants(List<Dependant> dependants) {
        this.dependants = dependants;
    }


}
