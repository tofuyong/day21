package ibf2022.day21.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.day21.exception.ResourceNotFoundException;
import ibf2022.day21.model.Room;
import ibf2022.day21.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController {
    
    @Autowired
    RoomService roomSvc;

    // #1 Get Count
    // http://localhost:8080/api/rooms/count
    @GetMapping("/count")
    public ResponseEntity<Integer> getRoomCount() {
        Integer roomCount = roomSvc.count();
        return new ResponseEntity<Integer>(roomCount, HttpStatus.OK);
        // Same as writing below:
        // return ResponseEntity.ok().body(roomCount);
    }
    // What a normal method returning roomcount looks like
    // public int getRoomCount() {
    //     Integer roomCount = roomSvc.count();
    //     return roomCount;
    // }

    // #2 Create in CRUD
    @PostMapping
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {
        Boolean created = roomSvc.save(room);
        if (created) {
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(created, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // #3 Read(all) in CRUD
    @GetMapping("getall")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = new ArrayList<Room>();
        rooms = roomSvc.findAll();
        if (rooms.isEmpty()) {
            // return ResponseEntity<>(HttpStatus.NO_CONTENT);
            // return ResponseEntity.noContent().build();
            throw new ResourceNotFoundException("No room found"); // Customised RNFE
        } else {
            // return new ResponseEntity<>(rooms, HttpStatus.OK);
            return ResponseEntity.ok().body(rooms);
        }
    }

    // #4 Read in CRUD 
    @GetMapping("/{room-id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("room-id") Integer id) {
        Room room = new Room();
        room = roomSvc.findById(id);
        if (room == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(room);
    }
    
    // #5 Update in CRUD
    @PutMapping
    public ResponseEntity<Integer> updateRoom(@RequestBody Room room) {
        int updated = 0;  // Method returns 1 if successful
        updated = roomSvc.update(room);
        if (updated == 1) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(updated, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // #6 Delete in CRUD
    @DeleteMapping("/{room-id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("room-id") Integer id) {
        int deleted = 0;
        deleted = roomSvc.deleteById(id);
        if (deleted == 1) {
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(deleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
