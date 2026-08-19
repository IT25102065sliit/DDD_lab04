package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModuleController {

    @FXML private TextField txtMcode;
    @FXML private TextField txtMname;
    @FXML private TextField txtDescription;
    @FXML private TextField txtCredits;
    @FXML private Label lblStatus;

    @FXML
    private void handleAdd() {
        String sql = "INSERT INTO Module (Mcode, Mname, M_description, NoOfCredits) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtMcode.getText().trim());
            stmt.setString(2, txtMname.getText().trim());
            stmt.setString(3, txtDescription.getText().trim());
            stmt.setInt(4, Integer.parseInt(txtCredits.getText().trim()));

            stmt.executeUpdate();
            lblStatus.setText("Module added successfully.");
            clearFields();
        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        String sql = "UPDATE Module SET Mname = ?, M_description = ?, NoOfCredits = ? WHERE Mcode = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtMname.getText().trim());
            stmt.setString(2, txtDescription.getText().trim());
            stmt.setInt(3, Integer.parseInt(txtCredits.getText().trim()));
            stmt.setString(4, txtMcode.getText().trim());

            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Module updated." : "No module found with that code.");
        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        String sql = "DELETE FROM Module WHERE Mcode = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtMcode.getText().trim());

            int rows = stmt.executeUpdate();
            lblStatus.setText(rows > 0 ? "Module deleted." : "No module found with that code.");
            if (rows > 0) clearFields();
        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtMcode.clear();
        txtMname.clear();
        txtDescription.clear();
        txtCredits.clear();
    }
}