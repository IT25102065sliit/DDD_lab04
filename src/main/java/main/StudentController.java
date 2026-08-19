package main;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController {

    @FXML private TextField txtSid;
    @FXML private TextField txtSname;
    @FXML private TextField txtAddress;
    @FXML private DatePicker dpDob;
    @FXML private TextField txtNic;
    @FXML private TextField txtCid;
    @FXML private Label lblStatus;

    @FXML
    private void handleAdd() {
        String sql = "INSERT INTO Student (SID, Sname, Address, dob, NIC, CID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtSid.getText().trim());
            stmt.setString(2, txtSname.getText().trim());
            stmt.setString(3, txtAddress.getText().trim());
            stmt.setDate(4, Date.valueOf(dpDob.getValue()));
            stmt.setString(5, txtNic.getText().trim());
            stmt.setString(6, txtCid.getText().trim());

            stmt.executeUpdate();
            lblStatus.setText("Student added successfully.");
            clearFields();
        } catch (SQLException | NumberFormatException | NullPointerException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        String sql = "UPDATE Student SET Sname = ?, Address = ?, dob = ?, NIC = ?, CID = ? WHERE SID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtSname.getText().trim());
            stmt.setString(2, txtAddress.getText().trim());
            stmt.setDate(3, Date.valueOf(dpDob.getValue()));
            stmt.setString(4, txtNic.getText().trim());
            stmt.setString(5, txtCid.getText().trim());
            stmt.setString(6, txtSid.getText().trim());

            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Student updated." : "No student found with that ID.");
        } catch (SQLException | NumberFormatException | NullPointerException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
    String sql = "DELETE FROM Student WHERE SID = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, txtSid.getText().trim());

        int rows = stmt.executeUpdate();

        lblStatus.setText(
            rows > 0 ? "Student deleted." : "No student found with that ID."
        );

        if (rows > 0) {
            clearFields();
        }

    } catch (SQLException e) {
        lblStatus.setText("Error: " + e.getMessage());
    }
}

    private void clearFields() {
        txtSid.clear();
        txtSname.clear();
        txtAddress.clear();
        dpDob.setValue(null);
        txtNic.clear();
        txtCid.clear();
    }
}