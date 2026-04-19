package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.service.ProductService;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductController {
    ProductService ps = new ProductService();
    Scanner s = new Scanner(System.in);

    // 상품 추가 controller
    public void addRun() {
        try {
            System.out.println("등록할 상품 ID : ");
            int a = Integer.parseInt(s.nextLine());
            System.out.println("등록할 상품 명 : ");
            String b = s.nextLine();
            System.out.println("등록할 상품의 상세 설명 : ");
            String c = s.nextLine();
            System.out.println("등록할 상품의 가격 : ");
            int d = Integer.parseInt(s.nextLine());
            System.out.println("등록할 상품의 재고 : ");
            int e = Integer.parseInt(s.nextLine());
            ps.addProduct(a, b, c, d, e);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }

    // 상품 수정 controller
    public void updateRun() {
        try {
            System.out.println("수정할 상품 ID : ");
            int a = Integer.parseInt(s.nextLine());
            System.out.println("수정할 상품 명 : ");
            String b = s.nextLine();
            System.out.println("수정할 상품의 상세 설명 : ");
            String c = s.nextLine();
            System.out.println("수정할 상품의 가격 : ");
            int d = Integer.parseInt(s.nextLine());
            System.out.println("수정할 상품의 재고 : ");
            int e = Integer.parseInt(s.nextLine());
            ps.updateProduct(a, b, c, d, e);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }

    // 상품 삭제 controller
    public void deleteRun() {
        try {
            System.out.println("삭제할 상품 ID : ");
            int a = Integer.parseInt(s.nextLine());
            ps.deleteProduct(a);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }


    // 재고 수량 업데이트
    public void stockUpdateRun() {
        try {
            System.out.println("재고 수량 변경할 상품 ID : ");
            int a = Integer.parseInt(s.nextLine());
            System.out.println("수량 입력 : ");
            int b = Integer.parseInt(s.nextLine());
            ps.updateStock(a, b);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }

    // 품절 처리
    public void outRun() {
        try {
            System.out.println("품절 처리할 상품 ID : ");
            int a = Integer.parseInt(s.nextLine());
            ps.soldOut(a);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }

    // 상품 목록 조회
    public void showRun() {
        try {
            List<Map<String, Object>> list = ps.productShow();
            if (list.isEmpty()) {
                System.out.println("등록된 상품이 없습니다.");
                return;
            }
            for (Map<String, Object> p : list) {
                System.out.println("상품명 : " + p.get("productName") +
                        " | 가격 : " + p.get("productPrice") + "원" +
                        " | 남은 수량 : " + p.get("productStock") +
                        " | 상세설명 : " + p.get("productExplain"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}