package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.repository.UsersRepository;
import java.util.Map;

public class UserService {

    private UsersRepository repo = new UsersRepository();

    // 회원가입
    public boolean register(String userId, String name, String password,
                            String phone, String email, int role) {
        if (repo.findByEmail(email) != null) {
            System.out.println("이미 사용 중인 이메일입니다.");
            return false;
        }
        boolean result = repo.insert(userId, name, password, email, phone, role);
        if (result) {
            System.out.println("회원가입이 완료되었습니다.");
        }
        return result;
    }

    // 로그인
    public Map<String, Object> login(String email, String password) {
        Map<String, Object> user = repo.findByEmail(email);

        // 유저가 없거나 비밀번호가 틀린 경우를 안전하게 처리
        if (user == null || !password.equals(user.get("pwd"))) {
            System.out.println("이메일 또는 비밀번호가 틀렸습니다.");
            return null;
        }

        System.out.println(user.get("userName") + "님 로그인 되셨습니다!");
        return user;
    }

    // 회원명 변경 (userId를 String으로 변경하여 Repository와 맞춤)
    public void updateName(String userId, String newName) {
        boolean result = repo.updateName(userId, newName);
        if (result) {
            System.out.println("회원명이 변경되었습니다.");
        } else {
            System.out.println("변경에 실패했습니다. (회원 정보 확인 필요)");
        }
    }

    // 비밀번호 변경 (기존 비밀번호 검증 로직 포함)
    public void updatePassword(String userId, String oldPw, String newPw) {
        Map<String, Object> user = repo.findById(userId);
        if (user == null) {
            System.out.println("회원을 찾을 수 없습니다.");
            return;
        }

        // DB에서 가져온 비밀번호와 입력한 기존 비밀번호 대조
        if (!user.get("pwd").equals(oldPw)) {
            System.out.println("기존 비밀번호가 틀렸습니다.");
            return;
        }

        boolean result = repo.updatePassword(userId, newPw);
        if (result) {
            System.out.println("비밀번호가 변경되었습니다.");
        }
    }
    // 휴대전화 변경
    public void updatePhone(String userId, String newPhone) {
        boolean result = repo.updatePhone(userId, newPhone);
        if (result) {
            System.out.println("휴대전화가 변경되었습니다.");
        } else {
            System.out.println("회원을 찾을 수 없습니다. (변경 실패)");
        }
    }

    // 이메일 변경
    public void updateEmail(String userId, String newEmail) {
        // 1. 변경하려는 새 이메일이 이미 존재하는지 중복 체크
        if (repo.findByEmail(newEmail) != null) {
            System.out.println("이미 사용 중인 이메일입니다.");
            return;
        }

        // 2. 중복이 아니면 변경 실행
        boolean result = repo.updateEmail(userId, newEmail);
        if (result) {
            System.out.println("이메일이 변경되었습니다.");
        } else {
            System.out.println("회원을 찾을 수 없습니다. (변경 실패)");
        }
    }

}