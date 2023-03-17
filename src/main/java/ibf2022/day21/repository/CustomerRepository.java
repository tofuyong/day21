package ibf2022.day21.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.day21.model.Customer;

@Repository
public class CustomerRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL = "select * from customer";
    // private final String findAllSQL = "select id, first_name, last_name, dob customer";

    private final String findAllSQLLimitOffset = "select * from customer limit ? offset ?";

    private final String findByIdSQL = "select * from customer where id = ?";

    // Method to get all customers
    public List<Customer> getAllCustomers() {
        final List<Customer> custList = new ArrayList<Customer>();
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQL);
        
        while (rs.next()) {
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setFirstName(rs.getString("first_name"));
            cust.setLastName(rs.getString("last_name"));
            cust.setDob(rs.getDate("dob"));
            custList.add(cust);
        }
        return Collections.unmodifiableList(custList);
    }

    // Method to get customers with limit & offset
    public List<Customer> getAllCustomersWithLimitOffset(int limit, int offset) {
        final List<Customer> custList = new ArrayList<Customer>();
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQLLimitOffset, limit, offset);
        
        while (rs.next()) {
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setFirstName(rs.getString("first_name"));
            cust.setLastName(rs.getString("last_name"));
            cust.setDob(rs.getDate("dob"));
            custList.add(cust);
        }
        return Collections.unmodifiableList(custList);
    }

    // Method to get customer by ID
    public Customer getCustomerById(int id) {
        // return jdbcTemplate.queryForObject(findByIdSQL, Customer.class, id);
        return jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Customer.class), id);
    }





    
    // For northwind DB
    // private final String findOrderByCustomer = "select id, customer_id, order_date, shipped_date, ship_name";

    // public List<Order> getCustomerOrder(int customer_id) {
    //     final List<Order> orderList = new ArrayList<Order>();
    //     final SqlRowSet rs = jdbcTemplate.queryForRowSet(findOrderByCustomer, BeanPropertyRowMapper.newInstance(Order.class), customer_id);
        
    //     while (rs.next()) {
    //         Order order = new Order();
    //         order.setId(rs.getInt("id"));
    //         order.setCustomerId(rs.getInt("first_name"));
    //         order.setOrderDate(rs.getObject("last_name"));
    //         order.setShippedDate(rs.getObject("shippedDate"));
    //         order.setShipName(rs.getString("shipName"));
    //         orderList.add(order);
    //     }
    //     return Collections.unmodifiableList(orderList);
    // }
}
