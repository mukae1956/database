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
            if (p.get("productId").equals(String.valueOf(pId))) {  // String 비교로 변경
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

    // 상품 목록 조회 (재고 있는 것만)
    public List<Map<String, Object>> productShow() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> p : repo.findAll()) {
            int stock = Integer.parseInt((String) p.get("productStock"));  // String → int 변환
            if (stock != 0) {
                result.add(p);
            }
        }
        return result;
    }
}