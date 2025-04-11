package model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:8080/api/tables";
    private static String authHeader;

    public static boolean login(String username, String password) {
        HttpClient client = HttpClient.newHttpClient();
        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        authHeader = "Basic " + encodedCredentials;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/login"))
                .header("Authorization", authHeader)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Login response status: " + response.statusCode());
            System.out.println("Login response body: " + response.body());
            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }

    public static Map<String, String> getTableInfo() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Authorization", authHeader)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Table info status: " + response.statusCode());
            System.out.println("Table info body: " + response.body());
            if (response.statusCode() == 200) {
                return parseTableInfo(response.body());
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("error", "API returned " + response.statusCode());
                return error;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to fetch table info: " + e.getMessage());
            return error;
        }
    }

    public static List<Map<String, String>> getTableData(String tableName) {
        if (tableName == null || tableName.isEmpty()) {
            System.err.println("Error: tableName is null or empty");
            return new ArrayList<>();
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL.replace("tables", "table/" + tableName)))
                .header("Authorization", authHeader)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Table data status: " + response.statusCode());
            System.out.println("Table data body: " + response.body());
            if (response.statusCode() == 200) {
                return parseTableData(response.body());
            } else {
                throw new IOException("API returned " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching table data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static Map<String, String> parseTableInfo(String json) {
        Map<String, String> tableInfo = new HashMap<>();
        json = json.substring(1, json.length() - 1);
        if (json.isEmpty()) return tableInfo;

        String[] entries = json.split("},");
        for (String entry : entries) {
            entry = entry.replace("{", "").replace("}", "").trim();
            String[] keyValuePairs = entry.split(",");
            String name = "";
            String comment = "";
            for (String pair : keyValuePairs) {
                String[] parts = pair.split(":");
                String key = parts[0].replace("\"", "").trim();
                String value = parts[1].replace("\"", "").trim();
                if ("name".equals(key)) name = value;
                if ("comment".equals(key)) comment = value;
            }
            tableInfo.put(name, comment);
        }
        return tableInfo;
    }

    private static List<Map<String, String>> parseTableData(String json) {
        List<Map<String, String>> tableData = new ArrayList<>();
        json = json.substring(1, json.length() - 1);
        if (json.isEmpty()) return tableData;

        String[] rows = json.split("},");
        for (String row : rows) {
            row = row.replace("{", "").replace("}", "").trim();
            String[] keyValuePairs = row.split(",");
            Map<String, String> rowData = new HashMap<>();
            for (String pair : keyValuePairs) {
                String[] parts = pair.split(":");
                String key = parts[0].replace("\"", "").trim();
                String value = parts[1].replace("\"", "").trim();
                rowData.put(key, value);
            }
            tableData.add(rowData);
        }
        return tableData;
    }
   
    public static void addRow(String tableName, Map<String, Object> data) {
        System.out.println("Adding row to table: " + tableName);
        System.out.println("Data: " + data);
    }
    public static void updateRow(String tableName, String columnName, Object idValue, Map<String, Object> data) {
        System.out.println("Updating row in table: " + tableName);
        System.out.println("Column: " + columnName + ", ID: " + idValue);
        System.out.println("Data: " + data);
    }
    public static void deleteRow(String tableName, String columnName, Object idValue) {
        System.out.println("Deleting row from table: " + tableName + " where " + columnName + " = " + idValue);
    }
}