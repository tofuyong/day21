package ibf2022.day21.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import ibf2022.day21.model.Dependant;
import ibf2022.day21.model.Employee;
import io.micrometer.common.lang.Nullable;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String findAllSQL = "select emp.id as emp_id, emp.first_name, emp.last_name, emp.salary," +
    " dep.id as dep_id, dep.full_name, dep.birthdate, dep.relationship" +
    " from employee as emp" +
    " left join dependant as dep" +
    " on emp.id = dep.employee_id";

    String findByIdSQL = "select emp.id as emp_id, emp.first_name, emp.last_name, emp.salary," +
    " dep.id as dep_id, dep.full_name, dep.birthdate, dep.relationship" +
    " from employee as emp" +
    " left join dependant as dep" +
    " on emp.id = dep.employee_id" +
    " where emp.id = ?";

    String insertSQL = "insert into employee (first_name, last_name, salary) values (?, ?, ?)";
    String updateSQL = "update employee set first_name = ?, last_name = ?, salary = ? where id = ?";
    String deleteSQL = "delete from employee where id = ?";

    // #1 Create Employee
    public Boolean save(Employee employee) {
        Boolean bSaved = false;
        PreparedStatementCallback <Boolean> psc = new PreparedStatementCallback<Boolean>() {
            @Override
            @Nullable
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setFloat(3, employee.getSalary());
                Boolean rslt = ps.execute(); // this executes the SQL insert statement
                return rslt;
            }
        };
        bSaved = jdbcTemplate.execute(insertSQL, psc);
        return bSaved;
    }

    // #2 Update Employee
    public int update(Employee employee) {
        // different method as in the notes, Darryl wants to show us his preferred method
        int iUpdated = 0; //jdbctemplate.update functions throws integer as output, mouseover .update to check
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setFloat(3, employee.getSalary());
                ps.setInt(4, employee.getId());
            }
        };
        iUpdated = jdbcTemplate.update(updateSQL, pss);
        return iUpdated;
    } 

    // #3 Delete Employee
    public int delete(Integer id) {
        int iDeleted = 0;
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        };
        iDeleted = jdbcTemplate.update(deleteSQL, pss);
        return iDeleted;
    }

    // #4 Find all Employees
    public List<Employee> findAll() {
        // Create a new array list object to hold Employee objects returned by method
        List<Employee> employees = new ArrayList<Employee>();
    
        employees = jdbcTemplate.query(findAllSQL, new ResultSetExtractor<List<Employee>>(){
            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Employee> emps = new ArrayList<Employee>();

                // emp.id as emp_id, emp.first_name, emp.last_name, emp.salary
                while(rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("emp_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setSalary(rs.getFloat("salary"));
                    employee.setDependants(new ArrayList<Dependant>());

                    // dep.id as dep_id, dep.full_name, dep.birthdate, dep.relationship
                    Dependant dependant = new Dependant();
                    dependant.setId(rs.getInt("dep_id"));
                    dependant.setFullName(rs.getString("full_name"));
                    dependant.setBirthdate(rs.getDate("birthdate"));
                    dependant.setRelationship(rs.getString("relationship"));

                    if (emps.size() == 0) { // if employee record is empty
                        employee.getDependants().add(dependant); // take dependant info and 
                        emps.add(employee); // put into array. now we have a new record
                    } else {
                        // check if employee exists or not using filtering
                        List<Employee> tmpList = emps.stream().filter(e -> e.getId() == employee.getId())
                        .collect(Collectors.toList());

                        // if employee exists (index not equal 0), go into the if loop
                        if (tmpList.size() > 0) {
                            // append to dependant list for the found employee
                            // find employee exist on which index of the array
                            int idx = emps.indexOf(tmpList.get(0));

                            // go to the index to add the dependant
                            if (idx >= 0) {
                                emps.get(idx).getDependants().add(dependant);
                            }
                        
                        // if the employee record is not found in the list of employees
                        } else {
                            // add new employee record tgt with depdendant information
                            employee.getDependants().add(dependant);
                            emps.add(employee);
                        }
                    }
                }
                return emps;
            }
         });
        return employees;
    }

    // #5 Find an employee by ID (in class task)
    public Employee findById (int employeeId) {
        Employee employee = new Employee();

        // Can also use SQLrowset in chuk's slides if don't want to use this complicated method pg 28,29 of D21 notes
        employee = jdbcTemplate.query(findByIdSQL, new ResultSetExtractor<Employee>() {
            @Override
            public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
                Employee emp = new Employee();

                // emp.id as emp_id, emp.first_name, emp.last_name, emp.salary
                while(rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("emp_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setSalary(rs.getFloat("salary"));
                    employee.setDependants(new ArrayList<Dependant>());

                    // dep.id as dep_id, dep.full_name, dep.birthdate, dep.relationship
                    Dependant dependant = new Dependant();
                    dependant.setId(rs.getInt("dep_id"));
                    dependant.setFullName(rs.getString("full_name"));
                    dependant.setBirthdate(rs.getDate("birthdate"));
                    dependant.setRelationship(rs.getString("relationship"));
                    
                    if (rs.isFirst()) {
                        emp = employee;
                        emp.getDependants().add(dependant);
                    } else {
                        emp.getDependants().add(dependant);
                    }
                }
                return emp;
            }
        
        }, employeeId);
        return employee;
    }  
}
