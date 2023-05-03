package data;

import lombok.SneakyThrows;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private SQLHelper() {
    }

    private static Connection getConnection() throws SQLException {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    @SneakyThrows
    public static String getPaymentStatusByDebitCard(String paymentId) {
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static String getPaymentStatusByCreditCard(String paymentId) {
        String statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static String getPaymentId() {
        String paymentId = null;
        val idSQL = "SELECT payment_id FROM order_entity ORDER BY id DESC LIMIT 1;";
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(idSQL)) {
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    paymentId = rs.getString("payment_id");
                }
            }
        }
        return paymentId;
    }

    @SneakyThrows
    public static String getPaymentAmount(String paymentId) {
        String amountSQL = "SELECT amount FROM payment_entity limit 1;";
        String amount = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(amountSQL)) {
            statusStmt.setString(1, paymentId);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    amount = rs.getString("amount");
                }
            }
        }
        return amount;
    }
}