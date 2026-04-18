package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.util.DBConnect;
import java.sql.*;
import java.util.*;

public class OrderRepository {

    // 주문 추가
    public boolean insert(int orderId, int productId, int orderStock) {
        String sql = "INSERT INTO C_ORDER (ORDER_ID, PRODUCT_ID, ORDER_STOCK) VALUES (?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, orderStock);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 전체 주문 조회
    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT O.ORDER_ID, O.PRODUCT_ID, P.PRODUCT_NAME, O.ORDER_STOCK " +
                "FROM C_ORDER O JOIN PRODUCT P ON O.PRODUCT_ID = P.PRODUCT_ID";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("orderId", rs.getInt("ORDER_ID"));
                row.put("productId", rs.getInt("PRODUCT_ID"));
                row.put("productName", rs.getString("PRODUCT_NAME"));
                row.put("orderStock", rs.getInt("ORDER_STOCK"));
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}