package ibf2022.day21.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.day21.model.Dependant;

@Repository
public class DependantRepository {

    // private static final Logger logger = LoggerFactory.getLogger(DependantRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL = "select dep.id as dep_id, dep.full_name, dep.relationship, dep.birthdate," +
    " emp.id as emp_id, emp.first_name as emp_first, emp.last_name as emp_last, emp.salary" +
    " from dependant as dep" +
    " inner join employee as emp" +
    " on dep.employee_id = emp.id";

    private final String findByIdSQL = "select dep.id as dep_id, dep.full_name, dep.relationship, dep.birthdate," +
    " emp.id as emp_id, emp.first_name as emp_first, emp.last_name as emp_last, emp.salary" +
    " from dependant as dep" +
    " inner join employee as emp" +
    " on dep.employee_id = emp.id" +
    " where dep.id = ?";

    private final String insertSQL = "insert into dependant (employee_id, full_name, relationship, birthdate) values (?, ?, ?, ?)";
    private final String updateSQL = "update dependant set full_name = ? where id = ?";
    private final String deleteSQL = "delete from dependant where id = ?";
    

    // // #1 Create Dependant (template.execute method)
    // public Boolean save(Dependant dependant){

    //     Boolean bSaved = false;
    //     // Use method taught by Darryl for Employee creation but return rows affected
    //     PreparedStatementCallback <Integer> psc = new PreparedStatementCallback<Integer>() {
    //         public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException {
    //             ps.setInt(1, dependant.getEmployeeId());
    //             ps.setString(2,dependant.getFullName());
    //             ps.setString(3,dependant.getRelationship());
    //             ps.setDate(4, dependant.getBirthdate());
    //             int rowsAffected = ps.executeUpdate();
    //             return rowsAffected;
    //         }
    //     }; 
    //     // throw an exception if dependant object or a field is null
    //     if (dependant == null || dependant.getEmployeeId() == null 
    //     || dependant.getFullName() == null || dependant.getRelationship() == null 
    //     || dependant.getBirthdate() == null) {
    //         throw new NullPointerException("Dependant object or one of its fields is null.");
    //     }

    //     int rowsAffected = jdbcTemplate.execute(insertSQL, psc);
    //     if (rowsAffected > 0) {
    //         bSaved = true;
    //     }
    //     return bSaved;
    // }

    // #1b Create Dependant (template.update method)
    public Boolean add(Dependant dependant){

        Boolean bSaved = false;
        int iAdded = 0;
        iAdded = jdbcTemplate.update(insertSQL, dependant.getEmployeeId(), dependant.getFullName(), 
                                dependant.getRelationship(), dependant.getBirthdate());
         
        if (iAdded > 0) {
            bSaved = true;
        }
        return bSaved;
    }

    // #2 Update Dependant (template.update method)
    public Boolean update(Dependant dependant){

        Boolean bUpdated = false;
        int iUpdated = 0;
        iUpdated = jdbcTemplate.update(updateSQL, dependant.getFullName(), dependant.getId());
         
        if (iUpdated > 0) {
            bUpdated = true;
        }
        return bUpdated;
    }

    // #3 Delete Dependant (template.update method)
    public Boolean delete(Integer id){

        Boolean bDeleted = false;
        int iDeleted = 0;
        iDeleted = jdbcTemplate.update(deleteSQL, id);
        
        if (iDeleted > 0) {
            bDeleted = true;
        }
        return bDeleted;
    }

    // #4 Find All Dependants (template.queryForRowSet method)
    public List<Dependant> findAll(){
        List<Dependant> dependants = new ArrayList<Dependant>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQL);
        while (rs.next()) {
            Dependant dep = new Dependant();
            dep.setId(rs.getInt("dep_id"));
            dep.setEmployeeId(rs.getInt("emp_id"));
            dep.setFullName(rs.getString("full_name"));
            dep.setRelationship(rs.getString("relationship"));
            dep.setBirthdate(rs.getDate("birthdate"));
            dependants.add(dep);
        }
        return dependants;
    }

    // #5 Find a single Dependant by ID (template.queryForObject method), id and employeeid empty in results
    // public Dependant findDepById(Integer id) {
    //     Dependant dependant = new Dependant();
    //     dependant = jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Dependant.class), id);
    //     return dependant;
    // }

    // #5b Find a single Dependant by ID (template.queryForRowSet method)
    public Dependant findDepById(Integer id) {
        Dependant dep = new Dependant();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findByIdSQL, id);
        while (rs.next()) {
            dep.setId(rs.getInt("dep_id"));
            dep.setEmployeeId(rs.getInt("emp_id"));
            dep.setFullName(rs.getString("full_name"));
            dep.setRelationship(rs.getString("relationship"));
            dep.setBirthdate(rs.getDate("birthdate"));
        }
        return dep;
    }
}
