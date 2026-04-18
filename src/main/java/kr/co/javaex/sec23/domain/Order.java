package kr.co.javaex.sec23.domain;

public class Order {

    private int orderId;
    private int productId;
    private String productName; // JOIN해서 가져올 상품명
    private int orderStock;

    public Order() {}

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getOrderStock() { return orderStock; }
    public void setOrderStock(int orderStock) { this.orderStock = orderStock; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", orderStock=" + orderStock +
                '}';
    }
}