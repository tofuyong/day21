package ibf2022.day21.controller;
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

import ibf2022.day21.model.Dependant;
import ibf2022.day21.service.DependantService;

@RestController
@RequestMapping("/api/dependants")
public class DependantController {

    // private static final Logger logger = LoggerFactory.getLogger(DependantController.class);

    @Autowired 
    DependantService depSvc;

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody Dependant dependant) {
        Boolean bSaved = depSvc.add(dependant);
        return new ResponseEntity<Boolean>(bSaved, HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Dependant dependant) {
        Boolean bUpdated = depSvc.update(dependant);
        return new ResponseEntity<Boolean>(bUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id) {
        Boolean bDeleted = depSvc.delete(id);
        return new ResponseEntity<Boolean>(bDeleted, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Dependant>> findAll() {
        List<Dependant> dependants = depSvc.findAll();
        return new ResponseEntity<List<Dependant>>(dependants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dependant> findById(@PathVariable("id") Integer id) {
        Dependant dependant = depSvc.findDepById(id);
        return new ResponseEntity<Dependant>(dependant, HttpStatus.OK);
    }
}
