package kr.co.javaex.sec23.domain;

public class Cart {

    private String userId;
    private int productId;
    private String productName; // JOIN해서 가져올 상품명
    private int productPrice;   // JOIN해서 가져올 가격

    public Cart() {}

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    @Override
    public String toString() {
        return "Cart{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}