package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.service.CartService;
import java.util.Scanner;

public class CartController {
    CartService cs = new CartService();
    Scanner s = new Scanner(System.in);

    // 장바구니 조회
    public void showRun(String userId) {
        try {
            cs.showCart(userId);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }

    // 장바구니 추가
    public void addRun(String userId) {
        try {
            System.out.println("담을 상품 ID 입력 : ");
            int a = Integer.parseInt(s.nextLine());
            cs.addCart(userId, a);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }

    // 장바구니 삭제
    public void deleteRun(String userId) {
        try {
            System.out.println("삭제할 상품 ID 입력 : ");
            int a = Integer.parseInt(s.nextLine());
            cs.removeCart(userId, a);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }
}