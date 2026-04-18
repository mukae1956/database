package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.repository.OrderRepository;
import kr.co.javaex.sec23.repository.ProductRepository;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderRepository orderRepo = new OrderRepository();
    private ProductRepository productRepo = new ProductRepository();

    // 주문하기
    public boolean order(int orderId, int productId, int orderStock) {
        // 상품 재고 체크
        List<Map<String, Object>> products = productRepo.findAll();
        for (Map<String, Object> p : products) {
            if ((int) p.get("productId") == productId) {
                int currentStock = (int) p.get("productStock");
                if (currentStock < orderStock) {
                    System.out.println("재고가 부족합니다. 현재 재고: " + currentStock);
                    return false;
                }
                // 주문 추가
                boolean result = orderRepo.insert(orderId, productId, orderStock);
                if (result) {
                    // 재고 차감
                    productRepo.updateStock(productId, currentStock - orderStock);
                    System.out.println("주문이 완료되었습니다.");
                }
                return result;
            }
        }
        System.out.println("존재하지 않는 상품입니다.");
        return false;
    }

    // 전체 주문 조회
    public void showOrders() {
        List<Map<String, Object>> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        System.out.println("===== 주문 내역 =====");
        for (Map<String, Object> o : orders) {
            System.out.println("주문ID: " + o.get("orderId") +
                    " | 상품명: " + o.get("productName") +
                    " | 수량: " + o.get("orderStock"));
        }
    }
}