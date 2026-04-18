package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.util.DBConnect;
import java.sql.*;
import java.util.*;

public class UsersRepository {

    public Map<String, Object> findByEmail(String email) {
        // 컬럼명을 USER_PWD, USER_ROLE로 정확히 매칭
        String sql = "SELECT USER_ID, USER_NAME, USER_PWD, USER_EMAIL, USER_PHONE, USER_ROLE FROM USERS WHERE USER_EMAIL = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("userId", rs.getString("USER_ID")); // ID가 String(예: 'a')이므로 getString
                user.put("userName", rs.getString("USER_NAME"));
                user.put("pwd", rs.getString("USER_PWD")); // USER_PWD로 수정
                user.put("userEmail", rs.getString("USER_EMAIL"));
                user.put("userPhone", rs.getString("USER_PHONE"));
                user.put("role", rs.getInt("USER_ROLE")); // USER_ROLE로 수정
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ID로 유저 찾기 (userId가 String인 경우를 대비해 매개변수 타입 확인 필요)
    public Map<String, Object> findById(String userId) {
        String sql = "SELECT USER_ID, USER_NAME, USER_PWD, USER_EMAIL, USER_PHONE, USER_ROLE FROM USERS WHERE USER_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("userId", rs.getString("USER_ID"));
                user.put("userName", rs.getString("USER_NAME"));
                user.put("pwd", rs.getString("USER_PWD"));
                user.put("userEmail", rs.getString("USER_EMAIL"));
                user.put("userPhone", rs.getString("USER_PHONE"));
                user.put("role", rs.getInt("USER_ROLE"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 회원가입
    public boolean insert(String userId, String userName, String pwd,
                          String email, String phone, int role) {
        String sql = "INSERT INTO USERS (USER_ID, USER_NAME, USER_PWD, USER_EMAIL, USER_PHONE, USER_ROLE) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setString(2, userName);
            pstmt.setString(3, pwd);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setInt(6, role); // int로 수정

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 회원명 변경
    public boolean updateName(String userId, String newName) {
        String sql = "UPDATE USERS SET USER_NAME = ? WHERE USER_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, userId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 휴대전화 변경
    public boolean updatePhone(String userId, String newPhone) {
        String sql = "UPDATE USERS SET USER_PHONE = ? WHERE USER_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPhone);
            pstmt.setString(2, userId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 이메일 변경
    public boolean updateEmail(String userId, String newEmail) {
        String sql = "UPDATE USERS SET USER_EMAIL = ? WHERE USER_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setString(2, userId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 비밀번호 변경
    public boolean updatePassword(String userId, String newPw) {
        String sql = "UPDATE USERS SET USER_PWD = ? WHERE USER_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPw);
            pstmt.setString(2, userId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
