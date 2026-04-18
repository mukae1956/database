package kr.co.javaex.sec23.domain;

public class Products {

    private int productId;
    private String productName;
    private String productExplain;
    private int productPrice;
    private int productStock;
    private String productStatus; // 정상, 판매중지

    public Products() {}

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductExplain() { return productExplain; }
    public void setProductExplain(String productExplain) { this.productExplain = productExplain; }

    public int getProductPrice() { return productPrice; }
    public void setProductPrice(int productPrice) { this.productPrice = productPrice; }

    public int getProductStock() { return productStock; }
    public void setProductStock(int productStock) { this.productStock = productStock; }

    public String getProductStatus() { return productStatus; }
    public void setProductStatus(String productStatus) { this.productStatus = productStatus; }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStock=" + productStock +
                ", productStatus='" + productStatus + '\'' +
                '}';
    }
}