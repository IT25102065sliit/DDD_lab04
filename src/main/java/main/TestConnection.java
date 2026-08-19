package main;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = Database.getConnection();
            System.out.println("Database Connected Successfully!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}