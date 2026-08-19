package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OffersController {

    @FXML private TextField txtCid;
    @FXML private TextField txtMcode;
    @FXML private TextField txtYear;
    @FXML private TextField txtSemester;
    @FXML private Label lblStatus;

    @FXML
    private void handleAdd() {
        String sql = "INSERT INTO Offers (CID, Mcode, Accadamic_year, Semester) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtCid.getText().trim());
            stmt.setString(2, txtMcode.getText().trim());
            stmt.setString(3, txtYear.getText().trim());
            stmt.setInt(4, Integer.parseInt(txtSemester.getText().trim()));

            stmt.executeUpdate();

            lblStatus.setText("Offer mapping added successfully.");
            clearFields();

        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        String sql = "DELETE FROM Offers WHERE CID = ? AND Mcode = ? AND Accadamic_year = ? AND Semester = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, txtCid.getText().trim());
            stmt.setString(2, txtMcode.getText().trim());
            stmt.setString(3, txtYear.getText().trim());
            stmt.setInt(4, Integer.parseInt(txtSemester.getText().trim()));

            int rows = stmt.executeUpdate();

            lblStatus.setText(
                rows > 0
                    ? "Offer mapping deleted."
                    : "No matching offer record found."
            );

            if (rows > 0) {
                clearFields();
            }

        } catch (SQLException | NumberFormatException e) {
            lblStatus.setText("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtCid.clear();
        txtMcode.clear();
        txtYear.clear();
        txtSemester.clear();
    }
}