package br.com.skipthedishes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date date;

    private Long customerId;

    private String deliveryAddress;

    private String contact;

    private Long storeId;

    private List<OrderItemDTO> orderItems;

    private Double total;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date lastUpdate;

    protected OrderDTO() {
    }

    public OrderDTO(Date date, Long customerId, String deliveryAddress, String contact, Long storeId, List<OrderItemDTO> orderItems, Double total, String status, Date lastUpdate) {
        this.date = date;
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.contact = contact;
        this.storeId = storeId;
        this.orderItems = orderItems;
        this.total = total;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
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
        return "OrderDTO{" +
                "date=" + date +
                ", customerId=" + customerId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contact='" + contact + '\'' +
                ", storeId=" + storeId +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
