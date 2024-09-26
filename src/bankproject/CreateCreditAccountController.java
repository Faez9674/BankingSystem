/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bankproject;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CreateCreditAccountController implements Initializable {

    @FXML
    private Button accHomebtn;
    
    String user_id_global;
    @FXML
    private TextField cust_name;
    @FXML
    private TextField acc_num;
    @FXML
    private TextField bal_amount;
    @FXML
    private Button crtcreditbtn;
    
    
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

    }   

    @FXML
    private void accHomeBtn(ActionEvent event) throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
        Parent root = loader.load();
        AdminHomeController adminhomeController = loader.getController();
        adminhomeController.setUserDetails(user_id_global);
        Scene adminhomePage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(adminhomePage);
        appStage.show();
    }

    @FXML
    private void crtcreditBtn(ActionEvent event) throws IOException {
        String scustomer_name,saccount_number,samount;
        scustomer_name = cust_name.getText();
        saccount_number = acc_num.getText();
        samount = bal_amount.getText();
        int saccount_number_data = Integer.parseInt(saccount_number);
        int samount_data = Integer.parseInt(samount);
        
        try 
        {
            pst1 = con.prepareStatement("SELECT * FROM credit_account WHERE account_number = ?");
            pst1.setInt(1, saccount_number_data);
            ResultSet resultSet = pst1.executeQuery();
            
            if(resultSet.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");


                alert.setHeaderText("Alert");
                alert.setContentText("Account Already Exist!");

                alert.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
                Parent root = loader.load();
                AdminHomeController adminhomeController = loader.getController();
                adminhomeController.setUserDetails(user_id_global);
                Scene adminhomePage = new Scene(root);
                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

                appStage.setScene(adminhomePage);
                appStage.show();
            }else{
                pst = con.prepareStatement("insert into credit_account(customer_name,account_number,amount)values(?,?,?)");
                pst.setString(1, scustomer_name);
                pst.setInt(2, saccount_number_data);
                pst.setInt(3, samount_data);
                pst.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Create Credit Account");


                alert.setHeaderText("Create Credit Account");
                alert.setContentText("Credit Account Created Successfully!");

                alert.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
                Parent root = loader.load();
                AdminHomeController adminhomeController = loader.getController();
                adminhomeController.setUserDetails(user_id_global);
                Scene adminhomePage = new Scene(root);
                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

                appStage.setScene(adminhomePage);
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
        Connect();
    }
    
}
