package ibf2022.day21.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.day21.model.Customer;
import ibf2022.day21.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository custRepo;

    public List<Customer> retrieveAllCustomers() {
        return custRepo.getAllCustomers();
    }

    public List<Customer> retrieveAllCustomersWithLimitOffset(int limit, int offset) {
        return custRepo.getAllCustomersWithLimitOffset(limit, offset); //limit, offset will come from controller
    }

    public Customer getCustomerById(int id) {
        return custRepo.getCustomerById(id);
    }

    // public Order getCustomerOrder(int customer_id) {
    //     return custRepo.getCustomerOrder(customer_id);
    // }
}
