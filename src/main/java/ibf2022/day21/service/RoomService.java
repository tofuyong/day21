package ibf2022.day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.day21.model.Room;
import ibf2022.day21.repository.IRoomRepository;

@Service
public class RoomService {

    @Autowired
    IRoomRepository roomRepo;

    // Shifting the interface methods over and adding a public in front
    // #1 Get Count
    public int count() {
        return roomRepo.count();
    }

    // #2 Create in CRUD
    public Boolean save(Room room) {
        return roomRepo.save(room);
    }

    // #3 Read(all) in CRUD
    public List<Room> findAll() {
        return roomRepo.findAll();
    }

    // #4 Read in CRUD
    public Room findById(Integer id) {
        return roomRepo.findById(id);
    }

    // #5 Update in CRUD
    public int update(Room room) {
        return roomRepo.update(room);
    }

    // #6 Delete in CRUD
    public int deleteById(Integer id) {
        return roomRepo.deleteById(id);
    }
}
