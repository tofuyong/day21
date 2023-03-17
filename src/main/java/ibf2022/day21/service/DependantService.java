package ibf2022.day21.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.day21.model.Dependant;
import ibf2022.day21.repository.DependantRepository;

@Service
public class DependantService {
    
    @Autowired
    DependantRepository depRepo;

     // #1b Create Dependant (template.update method)
     public Boolean add(Dependant dependant){
        return depRepo.add(dependant);
    }

    // #2 Update Dependant (template.update method)
    public Boolean update(Dependant dependant){
        return depRepo.update(dependant);
    }

    // #3 Delete Dependant (template.update method)
    public Boolean delete(Integer id){
        return depRepo.delete(id);
    }

    // #4 Find All Dependants (template.queryForRowSet method)
    public List<Dependant> findAll(){
        return depRepo.findAll();
    }

    // #5b Find a single Dependant by ID (template.queryForRowSet method)
    public Dependant findDepById(Integer id) {
        return depRepo.findDepById(id);
    }

}
