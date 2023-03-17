package ibf2022.day21.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import ibf2022.day21.model.Room;

@Repository
public class RoomRepository implements IRoomRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // It is possible to create constants and call the constants as well
    private final String countSQL = "select count(*) from room";
    private final String findAllSQL = "select * from room";
    private final String findByIdSQL = "select * from room where id = ?";
    private final String deleteByIdSQL = "delete from room where id = ?";
    private final String insertSQL = "insert into room (room_type, price) values (?, ?)";
    private final String updateSQL = "update room set price = ? where id = ?";

    // #1 Get Count
    @Override
    public int count() {
        Integer result = 0; 
        result = jdbcTemplate.queryForObject(countSQL, Integer.class); // count = int
        if (result == null) {
            return 0;  // to account for the fact that result may be null
        } else {
            return result;
        }
    }

    // #2 Create in CRUD
    @Override
    public Boolean save(Room room) {
        Boolean saved = false; // default state

        // below is new method that Darryl taught us, different from notes. execute will return either T/F
        saved = jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>(){
            
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setString(1, room.getRoomType());
                ps.setFloat(2, room.getPrice());
                Boolean rsult = ps.execute();

                return rsult;
            }
        });
        return saved;
    }

    // #3 Read(all) in CRUD
    @Override
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        // Darryl thinks below method is easier than queryForRowSet method in notes and CustomerRepo. to show us different jdbctemplate functions 
        rooms = jdbcTemplate.query(findAllSQL, BeanPropertyRowMapper.newInstance(Room.class)); // automatically maps to object as long as formats comply 
        return rooms;
    }

    // #4 Read in CRUD
    @Override
    public Room findById(Integer id) {
        return jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Room.class), id);
    }

    // #5 Update in CRUD
    @Override
    public int update(Room room) {
        int updated = 0; // method returns 1 if successful, and -1 if unsuccessful

        // inline definition of PreparedStatementSetter can be taken out and defined separately as shown in #6
        updated = jdbcTemplate.update(updateSQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setFloat(1, room.getPrice());
                ps.setInt(2, room.getId());
            }
        });
        return updated;
    }

    // #6 Delete in CRUD
    @Override
    public int deleteById(Integer id) {
        int deleted = 0;

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id); // parameterIndex unlike array, starts from 1
            }
        };
        deleted = jdbcTemplate.update(deleteByIdSQL, pss);
        return deleted;
    }

}
