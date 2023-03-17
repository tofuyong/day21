package ibf2022.day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.day21.model.Employee;
import ibf2022.day21.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public Boolean save(Employee employee) {
        return empRepo.save(employee);
    }

    public int update(Employee employee) {
        return empRepo.update(employee);
    }

    public int delete(Integer id) {
        return empRepo.delete(id);
    }

    public List<Employee> findAll() {
        return empRepo.findAll();
    }

    public Employee findByEmployeeId(Integer employeeId) {
        return empRepo.findById(employeeId);
    }
}
