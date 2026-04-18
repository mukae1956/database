package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {

    private ProductRepository repo = new ProductRepository();

    // 상품 등록
    public boolean addProduct(int pId, String name, String explain, int price, int stock) {
        List<Map<String, Object>> list = repo.findAll();
        for (Map<String, Object> p : list) {
            if ((int) p.get("productId") == pId) {
                System.out.println("이미 있는 상품입니다.");
                return false;
            }
        }
        boolean result = repo.insert(pId, name, explain, price, stock);
        if (result) {
            System.out.println("새로운 상품이 등록되었습니다.");
        }
        return result;
    }

    // 상품 수정
    public void updateProduct(int pId, String newName, String newExplain, int newPrice, int newStock) {
        boolean result = repo.update(pId, newName, newExplain, newPrice, newStock);
        if (result) {
            System.out.println("상품이 수정되었습니다.");
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }
    }

    // 상품 삭제
    public void deleteProduct(int pId) {
        boolean result = repo.delete(pId);
        if (result) {
            System.out.println("상품이 삭제되었습니다.");
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }
    }

    // 판매중지 처리
    public void stopSale(int pId) {
        boolean result = repo.updateStatus(pId, "판매중지");
        if (result) {
            System.out.println("판매중지 처리되었습니다.");
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }
    }

    // 재고 수량 업데이트
    public void updateStock(int pId, int newStock) {
        boolean result = repo.updateStock(pId, newStock);
        if (result) {
            System.out.println("재고가 업데이트되었습니다.");
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }
    }

    // 품절 처리
    public void soldOut(int pId) {
        boolean result = repo.updateStock(pId, 0);
        if (result) {
            System.out.println("품절 처리되었습니다.");
        } else {
            System.out.println("상품을 찾을 수 없습니다.");
        }
    }

    // 상품 목록 조회 (재고 있고 판매중지 아닌 것만)
    public List<Map<String, Object>> productShow() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> p : repo.findAll()) {
            int stock = (int) p.get("productStock");
            String status = (String) p.get("productStatus");
            if (stock != 0 && !"판매중지".equals(status)) {
                result.add(p);
            }
        }
        return result;
    }
}