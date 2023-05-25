package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();
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
    public static String getPaymentStatusByDebitCard() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnection()) {
            var debitStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return debitStatus;
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;

    }


    @SneakyThrows
    public static String getPaymentStatusByCreditCard() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (val conn = getConnection()) {
            val creditStatus = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return creditStatus;
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static Integer getPaymentAmount() {
        String amountSQL = "SELECT amount FROM payment_entity limit 1;";
        try (var conn = getConnection()) {
            var amount = runner.query(conn, amountSQL, new ScalarHandler<Integer>());
            return amount;
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConnection();
        runner.execute(conn, "DELETE FROM order_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM credit_request_entity");

    }
}