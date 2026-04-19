package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.util.DBConnect;
import java.sql.*;
import java.util.*;

public class CartRepository {

    // 내 장바구니 조회
    public List<Map<String, Object>> findByUserId(String userId) {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT C.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_PRICE " +
                "FROM CART C JOIN PRODUCTS P ON C.PRODUCT_ID = P.PRODUCT_ID " +
                "WHERE C.USER_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("productId", rs.getInt("PRODUCT_ID"));
                row.put("productName", rs.getString("PRODUCT_NAME"));
                row.put("productPrice", rs.getInt("PRODUCT_PRICE"));
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 장바구니 추가
    public boolean insert(String userId, int productId) {
        String sql = "INSERT INTO CART (USER_ID, PRODUCT_ID) VALUES (?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setInt(2, productId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 장바구니 삭제
    public boolean delete(String userId, int productId) {
        String sql = "DELETE FROM CART WHERE USER_ID = ? AND PRODUCT_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setInt(2, productId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}