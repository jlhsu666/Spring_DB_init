package com.example.h2_initdb;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class DatabaseInitializer {

    @PostConstruct
    public void initDatabase() {
        String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        String createTableSql = "CREATE TABLE IF NOT EXISTS users (" +
        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
        "name VARCHAR(255) NOT NULL, " +
        "email VARCHAR(255) NOT NULL)";

        String insertTestDataSql = "INSERT INTO users (name, email) VALUES " + 
        "('John Doe', 'john@example.com'), " + 
        "('Alen Chen', 'alen@test.com'), " + 
        "('Hellen Liu', 'hellen@gmail.com')";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSql);
            System.out.println("表格建立成功");

            stmt.execute(insertTestDataSql);
            System.out.println("測試資料插入成功");            

        } catch (Exception e) {
            System.err.println("初始化資料庫失敗：" + e.getMessage());
        }        

    }
}
