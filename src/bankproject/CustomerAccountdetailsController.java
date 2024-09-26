/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bankproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CustomerAccountdetailsController implements Initializable {

    @FXML
    private Button backHome;
    @FXML
    private Label accountnumber;
    @FXML
    private Label accounttype;
    @FXML
    private Label balance;

    
    String user_id_global;
    /**
     * Initializes the controller class.
     */
    
    
    public void setUserDetails1(String user_id_val) throws SQLException, IOException {
        
//        String query = "SELECT * FROM customer_user WHERE user_id = ?";
//        PreparedStatement statement = con.prepareStatement(query);
//        statement.setString(1, user_id);
//        ResultSet resultSet = statement.executeQuery();
//        String userName = resultSet.getString("user_name");

        user_id_global = user_id_val;
        
        

        String url = "jdbc:mysql://localhost:3306/bankingsystem";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);
//        testData.setText(user_id_val);
        Statement statement = connection.createStatement();
        String query1 = "SELECT * FROM customer_user WHERE user_id = ?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
        preparedStatement1.setString(1, user_id_val);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        if (resultSet1.next()) {
            int user_id = resultSet1.getInt("id");
            String user_name = resultSet1.getString("user_name");
            String user_id_dat = String.valueOf(user_id);
            String query = "SELECT * FROM account_details WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String acc_num = resultSet.getString("account_number");
                String acc_type = resultSet.getString("account_type");
                String acc_bal = resultSet.getString("balance");
                accountnumber.setText(acc_num);
//                account_number.setEditable(false);
                accounttype.setText(acc_type);
//                customer_name.setEditable(false);
                balance.setText(acc_bal);
//                customer_id.setEditable(false);
            } else {
                
            }
        } else {
//            
        }
        
        
//        String userName = resultSet.getString("user_name");
//            String userName = resultSet.getString("name");
//            resultLabel.setText("User Name: " + userName);

                
        
//        textDatauser.setText("Welcome, " + userName);
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backhome(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
        Parent root = loader.load();
        CustomerHomeController customerhomeController = loader.getController();
        customerhomeController.setUserDetails(user_id_global);
        Scene customerhomePage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(customerhomePage);
        appStage.show();
    }
    
}
