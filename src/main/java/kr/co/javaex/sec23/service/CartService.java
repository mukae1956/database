package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.repository.CartRepository;
import kr.co.javaex.sec23.repository.ProductRepository;
import java.util.List;
import java.util.Map;

public class CartService {

    private CartRepository cartRepo = new CartRepository();
    private ProductRepository productRepo = new ProductRepository();

    // 장바구니 담기
    public boolean addCart(String userId, int productId) {
        // 상품이 실제로 존재하는지 체크
        List<Map<String, Object>> products = productRepo.findAll();
        boolean exists = false;
        for (Map<String, Object> p : products) {
            if ((int) p.get("productId") == productId) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            System.out.println("존재하지 않는 상품입니다.");
            return false;
        }

        // 이미 장바구니에 있는지 체크
        List<Map<String, Object>> cartList = cartRepo.findByUserId(userId);
        for (Map<String, Object> c : cartList) {
            if ((int) c.get("productId") == productId) {
                System.out.println("이미 장바구니에 담긴 상품입니다.");
                return false;
            }
        }

        boolean result = cartRepo.insert(userId, productId);
        if (result) {
            System.out.println("장바구니에 담겼습니다.");
        }
        return result;
    }

    // 장바구니 조회
    public void showCart(String userId) {
        List<Map<String, Object>> cartList = cartRepo.findByUserId(userId);
        if (cartList.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }
        System.out.println("===== 장바구니 =====");
        int total = 0;
        for (Map<String, Object> c : cartList) {
            System.out.println("상품명: " + c.get("productName") +
                    " | 가격: " + c.get("productPrice") + "원");
            total += (int) c.get("productPrice");
        }
        System.out.println("총 합계: " + total + "원");
    }

    // 장바구니 삭제
    public void removeCart(String userId, int productId) {
        boolean result = cartRepo.delete(userId, productId);
        if (result) {
            System.out.println("장바구니에서 삭제되었습니다.");
        } else {
            System.out.println("해당 상품이 장바구니에 없습니다.");
        }
    }
}