package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseController {

    @FXML private TextField txtCid;
    @FXML private TextField txtCname;
    @FXML private TextField txtDescription;
    @FXML private TextField txtFee;
    @FXML private Label lblStatus;

    @FXML
    private void handleAdd() {
        String sql = "INSERT INTO Course (CID, Cname, C_description, C_fee) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(txtCid.getText().trim()));
            stmt.setString(2, txtCname.getText().trim());
            stmt.setString(3, txtDescription.getText().trim());
            stmt.setDouble(4, Double.parseDouble(txtFee.getText().trim()));

            stmt.executeUpdate();
            lblStatus.setText("Course added successfully.");
            clearFields();
        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        String sql = "UPDATE Course SET Cname = ?, C_description = ?, C_fee = ? WHERE CID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtCname.getText().trim());
            stmt.setString(2, txtDescription.getText().trim());
            stmt.setDouble(3, Double.parseDouble(txtFee.getText().trim()));
            stmt.setInt(4, Integer.parseInt(txtCid.getText().trim()));

            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Course updated." : "No course found with that ID.");
        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        String sql = "DELETE FROM Course WHERE CID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(txtCid.getText().trim()));

            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Course deleted." : "No course found with that ID.");
            if (rows > 0) clearFields();
        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtCid.clear();
        txtCname.clear();
        txtDescription.clear();
        txtFee.clear();
    }
}