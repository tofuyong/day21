package ibf2022.day21.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.day21.model.Customer;
import ibf2022.day21.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestController {

    @Autowired
    CustomerService custSvc;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return custSvc.retrieveAllCustomers();
    }

    @GetMapping("/limit")
    public List<Customer> getAllCustomers(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        return custSvc.retrieveAllCustomersWithLimitOffset(limit, offset);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return custSvc.getCustomerById(id);
    }

    // Controller method to get order from NorthWind
    // @GetMapping("/{customer_id}/orders")
    // public List<Order> getCustomerOrder(@PathVariable("id") int customer_id) {
    //     return custSvc.getCustomerOrder(id);
    // }

    
}
