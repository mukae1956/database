package kr.co.javaex.sec23.repository;

import kr.co.javaex.sec23.util.DBConnect;
import java.sql.*;
import java.util.*;

public class ProductRepository {

    // 전체 상품 조회
    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_EXPLAIN, PRODUCT_PRICE, PRODUCT_STOCK, PRODUCT_STATUS FROM PRODUCT";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("productId", rs.getInt("PRODUCT_ID"));
                row.put("productName", rs.getString("PRODUCT_NAME"));
                row.put("productExplain", rs.getString("PRODUCT_EXPLAIN"));
                row.put("productPrice", rs.getInt("PRODUCT_PRICE"));
                row.put("productStock", rs.getInt("PRODUCT_STOCK"));
                row.put("productStatus", rs.getString("PRODUCT_STATUS"));
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 상품 추가
    public boolean insert(int productId, String name, String explain, int price, int stock) {
        String sql = "INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME, PRODUCT_EXPLAIN, PRODUCT_PRICE, PRODUCT_STOCK, PRODUCT_STATUS) VALUES (?, ?, ?, ?, ?, '정상')";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            pstmt.setString(2, name);
            pstmt.setString(3, explain);
            pstmt.setInt(4, price);
            pstmt.setInt(5, stock);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 상품 수정
    public boolean update(int productId, String newName, String newExplain, int newPrice, int newStock) {
        String sql = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_EXPLAIN = ?, PRODUCT_PRICE = ?, PRODUCT_STOCK = ? WHERE PRODUCT_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newExplain);
            pstmt.setInt(3, newPrice);
            pstmt.setInt(4, newStock);
            pstmt.setInt(5, productId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 상품 삭제
    public boolean delete(int productId) {
        String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 상태 변경
    public boolean updateStatus(int productId, String status) {
        String sql = "UPDATE PRODUCT SET PRODUCT_STATUS = ? WHERE PRODUCT_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, productId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 재고 변경
    public boolean updateStock(int productId, int newStock) {
        String sql = "UPDATE PRODUCT SET PRODUCT_STOCK = ? WHERE PRODUCT_ID = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newStock);
            pstmt.setInt(2, productId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}