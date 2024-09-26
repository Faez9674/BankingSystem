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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AcceptlLoanrequestController implements Initializable {

    @FXML
    private Button backbtn;
    
    
    String user_id_global;
     
     
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    int myIndex;
    int id;
    
    
    @FXML
    private TableView<Acceptloan> table;
    @FXML
    private TableColumn<Acceptloan, String> customer_name;
    @FXML
    private TableColumn<Acceptloan, String> account_number;
    @FXML
    private TableColumn<Acceptloan, String> loan_type;
    @FXML
    private TableColumn<Acceptloan, String> loan_amount;
    @FXML
    private TableColumn<Acceptloan, String> num_year;
    @FXML
    private TableColumn<Acceptloan, String> status;
    @FXML
    private TextField customer_name1;
    @FXML
    private TextField account_number1;
    @FXML
    private TextField loan_type1;
    @FXML
    private TextField loan_amount1;
    @FXML
    private TextField num_year1;
    @FXML
    private Button updatebtn;
    
    
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
    
    
    /**
     * Initializes the controller class.
     */
     

    @FXML
    private void backBtn(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
        Parent root = loader.load();
        AdminHomeController adminhomeController = loader.getController();
        adminhomeController.setUserDetails(user_id_global);
        Scene adminhomePage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(adminhomePage);
        appStage.show();
    }
    
    
    public void table()
      {
          Connect();
          ObservableList<Acceptloan> acceptloans = FXCollections.observableArrayList();
       try 
       {
//           String accnt = "Pending";
           pst = con.prepareStatement("SELECT * FROM loan");
//           pst.setString(1, accnt);
           ResultSet rs = pst.executeQuery();
            {
              while (rs.next())
              {
                  Acceptloan st = new Acceptloan();
                  st.setcustomer_name(rs.getString("customer_name"));
                  st.setaccount_number(rs.getString("account_number"));
                  st.setloan_type(rs.getString("loan_type"));
                  st.setloan_amount(rs.getString("loan_amount"));
                  st.setnum_year(rs.getString("num_year"));
                  st.setstatus(rs.getString("status"));
                  acceptloans.add(st);
             }
            } 
            table.setItems(acceptloans);
            customer_name.setCellValueFactory(f -> f.getValue().customer_nameProperty());
            account_number.setCellValueFactory(f -> f.getValue().account_numberProperty());
            loan_type.setCellValueFactory(f -> f.getValue().loan_typeProperty());
            loan_amount.setCellValueFactory(f -> f.getValue().loan_amountProperty());
            num_year.setCellValueFactory(f -> f.getValue().num_yearProperty());
            status.setCellValueFactory(f -> f.getValue().statusProperty());
                
               
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(AcceptlLoanrequestController.class.getName()).log(Level.SEVERE, null, ex);
       }
                table.setRowFactory( tv -> {
             TableRow<Acceptloan> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();
         
                   
                   customer_name1.setText(table.getItems().get(myIndex).getcustomer_name());
                   customer_name1.setEditable(false);
                   account_number1.setText(table.getItems().get(myIndex).getaccount_number());
                   account_number1.setEditable(false);
                   loan_type1.setText(table.getItems().get(myIndex).getloan_type());
                   loan_type1.setEditable(false);
                   loan_amount1.setText(table.getItems().get(myIndex).getloan_amount());
                   loan_amount1.setEditable(false);
                   num_year1.setText(table.getItems().get(myIndex).getnum_year());
                   num_year1.setEditable(false);
                   
                 
                         
                           
                }
             });
                return myRow;
                   });
    
    
      }
    
    
    @FXML
    private void updateBtn(ActionEvent event) {
        
        String scustomer_name1,saccount_number1,sloan_type1,sloan_amount1,snum_year1;
        int saccount_number11, sloan_amount11, snum_year11;
        
         myIndex = table.getSelectionModel().getSelectedIndex();
            scustomer_name1 = customer_name1.getText();
            
            
            saccount_number1 = account_number1.getText();
            saccount_number11 = Integer.parseInt(saccount_number1);
            
            
            sloan_type1 = loan_type1.getText();
            
            
            sloan_amount1 = loan_amount1.getText();
            sloan_amount11 = Integer.parseInt(sloan_amount1);
            
            
            snum_year1 = num_year1.getText();
            snum_year11 = Integer.parseInt(snum_year1);
            
            
            String sstatus = "Approved";
        try 
        {
            pst = con.prepareStatement("update loan set customer_name = ?,account_number = ? ,loan_type = ?, loan_amount = ?, num_year = ?, status = ? where account_number = ? ");
            pst.setString(1, scustomer_name1);
            pst.setInt(2, saccount_number11);
            pst.setString(3, sloan_type1);
            pst.setInt(4, sloan_amount11);
            pst.setInt(5, snum_year11);
            pst.setString(6, sstatus);
            pst.setInt(7, saccount_number11);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loan Accept Details");
        
        alert.setHeaderText("Loan Accept Details");
        alert.setContentText("Loan Accepted Successful!");
        alert.showAndWait();
                table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(AcceptlLoanrequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        table();
    }   

    
    
}
