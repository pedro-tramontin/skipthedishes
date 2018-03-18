package br.com.skipthedishes.dto;

public class OrderItemDTO {
    private Long productId;

    private Double price;

    private Double quantity;

    private Double total;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Long productId, Double price, Double quantity, Double total) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "productId=" + productId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
