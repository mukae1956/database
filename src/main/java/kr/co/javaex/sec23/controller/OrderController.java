package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.service.OrderService;
import java.util.Scanner;

public class OrderController {
    OrderService os = new OrderService();
    Scanner s = new Scanner(System.in);

    // 주문 목록 조회
    public void showRun() {
        try {
            os.showOrders();
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }

    // 주문하기
    public void orderRun() {
        try {
            System.out.println("주문 ID 입력 : ");
            int a = Integer.parseInt(s.nextLine());
            System.out.println("주문할 상품 ID 입력 : ");
            int b = Integer.parseInt(s.nextLine());
            System.out.println("주문 수량 입력 : ");
            int c = Integer.parseInt(s.nextLine());
            os.order(a, b, c);
        } catch (Exception e) {
            System.out.println("[오류!!]");
        }
    }
}