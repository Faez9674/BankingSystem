/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bankproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AccountDetailsController implements Initializable {

    @FXML
    private Button backHome;
    @FXML
    private Button createAccount;
    @FXML
    private TextField customer_name;
    @FXML
    private TextField customer_id;
    @FXML
    private TextField account_number;
    @FXML
    private TextField balance;
    @FXML
    private ChoiceBox<String> account_type;
    
    private String[] accountType = {"Everyday", "Saving", "Credit"};
    
    String user_id_global;
    
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    int myIndex;
    int id;
    

    /**
     * Initializes the controller class.
     */
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/bankingsystem","root","");
        } catch (ClassNotFoundException ex) {
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
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
                account_number.setText(acc_num);
                account_number.setEditable(false);
                customer_name.setText(user_name);
                customer_name.setEditable(false);
                customer_id.setText(user_id_dat);
                customer_id.setEditable(false);
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

    @FXML
    private void createaccount(ActionEvent event) throws IOException {
        String scustomer_name,scustomer_id,saccount_number,sbalance,saccount_type;
            scustomer_name = customer_name.getText();
            scustomer_id = customer_id.getText();
            saccount_number = account_number.getText();
            sbalance = balance.getText();
            saccount_type = account_type.getValue();
            int scustomer_id_data = Integer.parseInt(scustomer_id);
            int saccount_number_data = Integer.parseInt(saccount_number);
            int sbalance_data = Integer.parseInt(sbalance);
            
        try 
        {
            pst1 = con.prepareStatement("SELECT * FROM account_details WHERE account_number = ?");
            pst1.setInt(1, saccount_number_data);
            ResultSet resultSet = pst1.executeQuery();
            
            if(resultSet.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");


                alert.setHeaderText("Alert");
                alert.setContentText("Account Already Exist!");

                alert.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
                Parent root = loader.load();
                CustomerHomeController customerhomeController = loader.getController();
                customerhomeController.setUserDetails(user_id_global);
                Scene customerhomePage = new Scene(root);
                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

                appStage.setScene(customerhomePage);
                appStage.show();
            }else{
                pst = con.prepareStatement("insert into account_details(customer_name,customer_id,account_type,account_number,balance)values(?,?,?,?,?)");
                pst.setString(1, scustomer_name);
                pst.setInt(2, scustomer_id_data);
                pst.setString(3, saccount_type);
                pst.setInt(4, saccount_number_data);
                pst.setInt(5, sbalance_data);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Create New Account");


                alert.setHeaderText("Create New Account");
                alert.setContentText("Account Created Successfully!");

                alert.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
                Parent root = loader.load();
                CustomerHomeController customerhomeController = loader.getController();
                customerhomeController.setUserDetails(user_id_global);
                Scene customerhomePage = new Scene(root);
                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

                appStage.setScene(customerhomePage);
                appStage.show();
            }
            
            
//            Parent customerhome = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
//            Scene customerhomePage = new Scene(customerhome);
//            Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//            appStage.setScene(customerhomePage);
//            appStage.show();
            
            

           
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(AccountDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        account_type.getItems().addAll(accountType);
        Connect();
    }
    
}
