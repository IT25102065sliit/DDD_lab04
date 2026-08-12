package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController {

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtAge;
    @FXML private TextField txtGpa;
    @FXML private TextField txtAddress;
    @FXML private Label lblStatus;

    @FXML
    private void handleAdd() {
        String sql = "INSERT INTO Student (ID, Name, Age, GPA, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(txtId.getText()));
            stmt.setString(2, txtName.getText());
            stmt.setInt(3, Integer.parseInt(txtAge.getText()));
            stmt.setDouble(4, Double.parseDouble(txtGpa.getText()));
            stmt.setString(5, txtAddress.getText());

            stmt.executeUpdate();
            lblStatus.setText("Student added successfully.");
            clearFields();

        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        String sql = "UPDATE Student SET Name = ?, Age = ?, GPA = ?, Address = ? WHERE ID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtName.getText());
            stmt.setInt(2, Integer.parseInt(txtAge.getText()));
            stmt.setDouble(3, Double.parseDouble(txtGpa.getText()));
            stmt.setString(4, txtAddress.getText());
            stmt.setInt(5, Integer.parseInt(txtId.getText()));

            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Student updated." : "No student found with that ID.");

        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        String sql = "DELETE FROM Student WHERE ID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(txtId.getText()));

            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Student deleted." : "No student found with that ID.");
            if (rows > 0) clearFields();

        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAge.clear();
        txtGpa.clear();
        txtAddress.clear();
    }
}