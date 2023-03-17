package ibf2022.day21.model;

import java.time.LocalDateTime;

public class Order {
    private Integer id;
    // private Integer employee;
    private Integer customerId;
    private LocalDateTime orderDate;
    private LocalDateTime shippedDate;
    private String shipName;


    public Integer getId() {return this.id;}
    public void setId(Integer id) {this.id = id;}

    public Integer getCustomerId() {return this.customerId;}
    public void setCustomerId(Integer customerId) {this.customerId = customerId;}

    public LocalDateTime getOrderDate() {return this.orderDate;}
    public void setOrderDate(LocalDateTime orderDate) {this.orderDate = orderDate;}

    public LocalDateTime getShippedDate() {return this.shippedDate;}
    public void setShippedDate(LocalDateTime shippedDate) {this.shippedDate = shippedDate;}

    public String getShipName() {return this.shipName;}
    public void setShipName(String shipName) {this.shipName = shipName;}

}
