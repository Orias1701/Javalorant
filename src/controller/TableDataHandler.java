package controller;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableDataHandler extends BaseHandler implements TableDataInterface {
    private final TableDataOperations operations = new TableDataOperations();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String tableName = path.startsWith("/api/table/") ? path.substring("/api/table/".length()) : "";
        String[] pathParts = tableName.split("/");

        if (!authenticate(exchange)) {
            return;
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            if (!DatabaseUtil.isTableExists(conn, pathParts[0])) {
                sendResponse(exchange, 400, "Yêu cầu không hợp lệ: Bảng không tồn tại");
                return;
            }

            switch (method) {
                case "GET":
                    handleGet(exchange, conn, pathParts[0]);
                    break;
                case "POST":
                    operations.handlePost(exchange, conn, pathParts[0]);
                    break;
                case "PUT":
                    if (pathParts.length < 2) {
                        sendResponse(exchange, 400, "Yêu cầu không hợp lệ: Thiếu giá trị khóa chính");
                        return;
                    }
                    operations.handlePut(exchange, conn, pathParts[0], pathParts[1]);
                    break;
                case "DELETE":
                    if (pathParts.length < 2) {
                        sendResponse(exchange, 400, "Yêu cầu không hợp lệ: Thiếu giá trị khóa chính");
                        return;
                    }
                    operations.handleDelete(exchange, conn, pathParts[0], pathParts[1]);
                    break;
                default:
                    sendResponse(exchange, 405, "Phương thức không được phép");
                    break;
            }
        } catch (Exception e) {
            LogHandler.logError("Server error: " + e.getMessage(), e);
            sendResponse(exchange, 500, "Lỗi server: " + e.getMessage());
        }
    }

    @Override
    public void handleGet(HttpExchange exchange, Connection conn, String tableName) throws Exception {
        String keyColumn = DatabaseUtil.getKeyColumn(conn, tableName);
        LogHandler.logInfo("Khóa chính Handler: " + keyColumn);

        // Lấy metadata cột theo thứ tự ORDINAL_POSITION
        List<Map<String, String>> columns = new ArrayList<>();
        String query = "SELECT COLUMN_NAME AS name, COLUMN_COMMENT AS comment " +
                      "FROM INFORMATION_SCHEMA.COLUMNS " +
                      "WHERE TABLE_NAME = ? ORDER BY ORDINAL_POSITION";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tableName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> column = new LinkedHashMap<>();
                    String name = rs.getString("name");
                    String comment = rs.getString("comment") != null ? rs.getString("comment") : "";
                    column.put("name", name);
                    column.put("comment", comment);
                    columns.add(column);
                }
            }
        }

        // Tạo JSON cho columns
        String columnsJson = JsonUtil.buildColumnsJsonFromList(columns);

        // Lấy dữ liệu bảng
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName);
             ResultSet rs = stmt.executeQuery()) {
            String dataJson = JsonUtil.buildDataJson(rs);
            String json = "{\"keyColumn\":\"" + (keyColumn != null ? keyColumn : "") + "\",\"columns\":" + columnsJson + ",\"data\":" + dataJson + "}";
            sendResponse(exchange, 200, json);
        }
    }

    @Override
    public void handlePost(HttpExchange exchange, Connection conn, String tableName) throws Exception {
        operations.handlePost(exchange, conn, tableName);
    }

    @Override
    public void handlePut(HttpExchange exchange, Connection conn, String tableName, String keyValue) throws Exception {
        operations.handlePut(exchange, conn, tableName, keyValue);
    }

    @Override
    public void handleDelete(HttpExchange exchange, Connection conn, String tableName, String keyValue) throws Exception {
        operations.handleDelete(exchange, conn, tableName, keyValue);
    }
}