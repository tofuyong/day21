package ibf2022.day21.repository;

import ibf2022.day21.model.Room;
import java.util.List;

public interface IRoomRepository {

    // Interface contains method signature but no functions
    int count();

    Boolean save(Room room);

    List<Room> findAll();

    Room findById(Integer id);

    int update(Room room);

    int deleteById(Integer id);

}
