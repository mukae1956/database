package kr.co.javaex.sec23.run;

import kr.co.javaex.sec23.controller.CategoryController;
import kr.co.javaex.sec23.controller.ProductController;
import kr.co.javaex.sec23.controller.UserController;
import kr.co.javaex.sec23.controller.CartController;
import kr.co.javaex.sec23.controller.OrderController;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Scanner;

public class Ecommerce {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner s = new Scanner(System.in);
        UserController uc = new UserController();
        CategoryController cc = new CategoryController();
        ProductController pc = new ProductController();
        CartController cartC = new CartController();
        OrderController oc = new OrderController();

        Map<String, Object> loginUser = null;

        while (true) {
            System.out.println("==============================");
            System.out.println("☕카에 카페에 오신 걸 환영합니다✨");
            System.out.println("==============================");
            System.out.println("무엇을 도와 드릴까요?");
            System.out.println("1. 회원가입 | 2. 로그인 | 3. 회원정보 수정 | 4. 종료");
            System.out.println("선택 > ");
            int num = Integer.parseInt(s.nextLine());

            if (num == 1) {
                System.out.println("회원가입을 도와드리겠습니다!");
                uc.regiRun();

            } else if (num == 2) {
                System.out.println("로그인을 선택하셨습니다!");
                loginUser = uc.loginRun();

                if (loginUser == null) {
                    System.out.println("로그인에 실패했습니다. 다시 시도해주세요.");
                    continue;
                }

                int role = (Integer) loginUser.get("role");       // ✅ int → Integer
                String userId = (String) loginUser.get("userId"); // ✅ int → String

                while (true) {
                    if (role == 2) {
                        System.out.println("1. 카테고리 | 2. 상품 | 3. 로그아웃");
                        System.out.println("선택 > ");
                        int num2 = Integer.parseInt(s.nextLine());

                        if (num2 == 1) {
                            System.out.println("1. 추가 | 2. 수정 | 3. 삭제 | 4. 목록 | 5. 종료");
                            System.out.println("선택 > ");
                            int num3 = Integer.parseInt(s.nextLine());
                            if (num3 == 1) {
                                System.out.println("카테고리 추가를 선택하셨습니다!");
                                cc.addRun();
                            } else if (num3 == 2) {
                                System.out.println("카테고리 수정을 선택하셨습니다!");
                                cc.updateRun();
                            } else if (num3 == 3) {
                                System.out.println("카테고리 삭제를 선택하셨습니다!");
                                cc.deleteRun();
                            } else if (num3 == 4) {
                                System.out.println("카테고리 목록을 선택하셨습니다!");
                                cc.showRun();
                            } else if (num3 == 5) {
                                System.out.println("이전 메뉴로 돌아갑니다.");
                                break;
                            } else {
                                System.out.println("없는 기능입니다! 다시 선택해 주세요!!");
                            }

                        } else if (num2 == 2) {
                            System.out.println("1. 추가 | 2. 수정 | 3. 삭제 | 4. 재고관리 | 5. 품절 | 6. 목록 | 7. 종료");
                            System.out.println("선택 > ");
                            int num4 = Integer.parseInt(s.nextLine());
                            if (num4 == 1) {
                                System.out.println("상품 추가를 선택하셨습니다!");
                                pc.addRun();
                            } else if (num4 == 2) {
                                System.out.println("상품 수정을 선택하셨습니다!");
                                pc.updateRun();
                            } else if (num4 == 3) {
                                System.out.println("상품 삭제를 선택하셨습니다!");
                                pc.deleteRun();
                            } else if (num4 == 4) {
                                System.out.println("상품 재고관리를 선택하셨습니다!");
                                pc.stockUpdateRun();
                            } else if (num4 == 5) {
                                System.out.println("상품 품절을 선택하셨습니다!");
                                pc.outRun();
                            } else if (num4 == 6) {
                                System.out.println("상품 목록을 선택하셨습니다!");
                                pc.showRun();
                            } else if (num4 == 7) {
                                System.out.println("이전 메뉴로 돌아갑니다.");
                                break;
                            } else {
                                System.out.println("없는 기능입니다! 다시 선택해 주세요!!");
                            }

                        } else if (num2 == 3) {
                            loginUser = null;
                            System.out.println("로그아웃 되었습니다. 이용해 주셔서 감사합니다.");
                            break;
                        } else {
                            System.out.println("없는 기능입니다! 다시 선택해 주세요!!");
                        }

                    } else if (role == 1) {
                        System.out.println("환영합니다! 어떤 기능을 사용하시겠습니까?");
                        System.out.println("1. 상품 보기 | 2. 장바구니 조회 | 3. 장바구니 추가 | 4. 장바구니 삭제 | 5. 주문하기 | 6. 주문 목록 | 7. 로그아웃");
                        System.out.println("선택 > ");
                        int num5 = Integer.parseInt(s.nextLine());

                        if (num5 == 1) {
                            pc.showRun();
                        } else if (num5 == 2) {
                            System.out.println("장바구니 조회를 선택하셨습니다!");
                            cartC.showRun(userId);
                        } else if (num5 == 3) {
                            System.out.println("장바구니 추가를 선택하셨습니다!");
                            cartC.addRun(userId);
                        } else if (num5 == 4) {
                            System.out.println("장바구니 삭제를 선택하셨습니다!");
                            cartC.deleteRun(userId);
                        } else if (num5 == 5) {
                            System.out.println("주문하기를 선택하셨습니다!");
                            oc.orderRun();
                        } else if (num5 == 6) {
                            System.out.println("주문 목록을 선택하셨습니다!");
                            oc.showRun();
                        } else if (num5 == 7) {
                            loginUser = null;
                            System.out.println("로그아웃 되었습니다. 이용해 주셔서 감사합니다.");
                            break;
                        } else {
                            System.out.println("없는 기능입니다! 다시 선택해 주세요!!");
                        }
                    }
                }

            } else if (num == 3) {
                if (loginUser == null) {
                    System.out.println("로그인이 필요합니다!");
                    continue;
                }
                String userId = (String) loginUser.get("userId");

                System.out.println("회원정보 수정을 도와드리겠습니다!!");
                System.out.println("1. 회원명 | 2. 휴대전화 | 3. 이메일 | 4. 비밀번호 | 5. 종료");
                System.out.println("선택 > ");
                int num6 = Integer.parseInt(s.nextLine());

                if (num6 == 1) {
                    uc.nameRun(userId);
                } else if (num6 == 2) {
                    uc.phoneRun(userId);
                } else if (num6 == 3) {
                    uc.emailRun(userId);
                } else if (num6 == 4) {
                    uc.pwRun(userId);
                } else if (num6 == 5) {
                    System.out.println("이전 메뉴로 돌아갑니다.");
                } else {
                    System.out.println("없는 기능입니다!! 다시 선택해주세요");
                }

            } else if (num == 4) {
                System.out.println("시스템을 종료하겠습니다.");
                break;

            } else {
                System.out.println("없는 기능입니다!! 다시 선택해주세요");
            }
        }
    }
}