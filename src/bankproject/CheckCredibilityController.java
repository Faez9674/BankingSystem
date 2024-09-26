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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;



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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javafx.scene.control.TableRow;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CheckCredibilityController implements Initializable {

    @FXML
    private Button accHomebtn;
    
    
    String user_id_global;
    @FXML
    private TableView<Customer> table;
    @FXML
    private TableColumn<Customer, String> customer_id;
    @FXML
    private TableColumn<Customer, String> customer_name;
    @FXML
    private TableColumn<Customer, String> account_type;
    @FXML
    private TableColumn<Customer, String> account_number;
    @FXML
    private TableColumn<Customer, String> balance;

    /**
     * Initializes the controller class.
     */
    
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    int myIndex;
    int id;
    
    
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
    
    
    
    public void table()
      {
          Connect();
          ObservableList<Customer> customers = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("SELECT * FROM account_details");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Customer st = new Customer();
            st.setcustomer_id(rs.getString("customer_id"));
            st.setcustomer_name(rs.getString("customer_name"));
            st.setaccount_type(rs.getString("account_type"));
            st.setaccount_number(rs.getString("account_number"));
            st.setbalance(rs.getString("balance"));
            customers.add(st);
       }
    } 
                table.setItems(customers);
                customer_id.setCellValueFactory(f -> f.getValue().customer_idProperty());
                customer_name.setCellValueFactory(f -> f.getValue().customer_nameProperty());
                account_type.setCellValueFactory(f -> f.getValue().account_typeProperty());
                account_number.setCellValueFactory(f -> f.getValue().account_numberProperty());
                balance.setCellValueFactory(f -> f.getValue().balanceProperty());
                
               
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(CheckCredibilityController.class.getName()).log(Level.SEVERE, null, ex);
       }
                table.setRowFactory( tv -> {
             TableRow<Customer> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                
             });
                return myRow;
                   });
    
    
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        table();
    } 
    
}
