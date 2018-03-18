package br.com.skipthedishes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customerEntity;

    @Column(name = "customerId", insertable = false, updatable = false)
    private Long customerId;

    private String deliveryAddress;

    private String contact;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "storeId")
    private StoreEntity storeEntity;

    @Column(name = "storeId", insertable = false, updatable = false)
    private Long storeId;

    @OneToMany
    private List<OrderItemEntity> orderItems;

    private Double total;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date lastUpdate;

    protected OrderEntity() {
    }

    public OrderEntity(Date date, CustomerEntity customerEntity, String deliveryAddress, String contact, StoreEntity storeEntity, List<OrderItemEntity> orderItems, Double total, String status, Date lastUpdate) {
        this.date = date;
        this.customerEntity = customerEntity;
        this.customerId = customerEntity.getId();
        this.deliveryAddress = deliveryAddress;
        this.contact = contact;
        this.storeEntity = storeEntity;
        this.storeId = storeEntity.getId();
        this.orderItems = orderItems;
        this.total = total;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public StoreEntity getStoreEntity() {
        return storeEntity;
    }

    public void setStoreEntity(StoreEntity storeEntity) {
        this.storeEntity = storeEntity;
    }

    public Long getStoreId() {
        return storeId;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", date=" + date +
                ", customerEntity=" + customerEntity +
                ", customerId=" + customerId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contact='" + contact + '\'' +
                ", storeEntity=" + storeEntity +
                ", storeId=" + storeId +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
