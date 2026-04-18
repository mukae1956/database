package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.service.CategoryService;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CategoryController {
    CategoryService cs = new CategoryService();
    Scanner s = new Scanner(System.in);

    // 카테고리 추가 controller
    public void addRun() {
        try {
            System.out.println("추가될 카테고리ID 작성 : ");
            int a = s.nextInt();
            s.nextLine();
            System.out.println("추가할 카테고리명 작성 : ");
            String b = s.nextLine();
            System.out.println("100 : 커피 | 200 : 논커피");
            System.out.println("상위 카테고리 ID 입력 (없으면 엔터): ");
            String input = s.nextLine();

            Integer c;
            if (input.isEmpty()) {
                c = null;
            } else {
                c = Integer.parseInt(input);
            }
            cs.addCategory(a, b, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카테고리 수정 controller
    public void updateRun() {
        try {
            System.out.println("변경할 카테고리ID 작성 : ");
            int a = s.nextInt();
            s.nextLine();
            System.out.println("변경할 카테고리명 작성 : ");
            String b = s.nextLine();
            cs.updateCategory(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카테고리 삭제 controller
    public void deleteRun() {
        try {
            System.out.println("삭제할 카테고리ID 작성 : ");
            int a = s.nextInt();
            s.nextLine();
            cs.deleteCategory(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 카테고리 목록 조회
    public void showRun() {
        try {
            List<Map<String, Object>> list = cs.showCategory();
            if (list.isEmpty()) {
                System.out.println("등록된 카테고리가 없습니다.");
                return;
            }
            System.out.println("===== 카테고리 목록 =====");
            for (Map<String, Object> c : list) {
                System.out.println("ID : " + c.get("categoryId") +
                        " | 이름 : " + c.get("categoryName") +
                        " | 상위ID : " + c.get("parentCategoryId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}