package kr.co.javaex.sec23.controller;

import kr.co.javaex.sec23.service.UserService;
import java.util.Map;
import java.util.Scanner;

public class UserController {
    Scanner s = new Scanner(System.in);
    UserService us = new UserService();

    // 회원가입
    public void regiRun() {
        System.out.println("ID 입력 : ");
        String a = s.nextLine();

        System.out.println("이름 입력 : ");
        String b = s.nextLine();

        System.out.println("비밀번호 입력 : ");
        String c = s.nextLine();
        if (!c.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{5,15}$")) {
            System.out.println("비밀번호는 대문자, 소문자, 숫자 모두 1개 이상 포함되어야 합니다.");
            return;
        }

        System.out.println("전화번호 입력 : ");
        String d = s.nextLine();

        System.out.println("이메일 입력 : ");
        String e = s.nextLine();

        System.out.println("회원 구분 입력 (1. 일반 | 2. 관리자) : ");
        int f = Integer.parseInt(s.nextLine());

        us.register(a, b, c, d, e, f);
    }

    // 로그인 - 로그인한 유저 정보 반환
    public Map<String, Object> loginRun() {
        System.out.println("이메일 입력 : ");
        String a = s.nextLine();
        System.out.println("비밀번호 입력 : ");
        String b = s.nextLine();
        return us.login(a, b);
    }

    // 회원명 수정
    public void nameRun(String userId) {
        System.out.println("변경할 이름 입력 : ");
        String a = s.nextLine();
        us.updateName(userId, a);
    }

    // 휴대전화 변경
    public void phoneRun(String userId) {
        System.out.println("변경할 번호 입력 : ");
        String a = s.nextLine();
        us.updatePhone(userId, a);
    }

    // 이메일 변경
    public void emailRun(String userId) {
        System.out.println("변경할 이메일 입력 : ");
        String a = s.nextLine();
        us.updateEmail(userId, a);
    }

    // 비밀번호 변경
    public void pwRun(String userId) {
        System.out.println("기존 비밀번호 입력 : ");
        String a = s.nextLine();
        System.out.println("변경할 비밀번호 입력 : ");
        String b = s.nextLine();
        us.updatePassword(userId, a, b);
    }
}