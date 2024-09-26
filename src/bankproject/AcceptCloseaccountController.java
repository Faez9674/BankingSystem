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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class AcceptCloseaccountController implements Initializable {

    @FXML
    private Button homebtn;
    
    @FXML
    private TableView<Acceptcloseaccount> table;
    @FXML
    private TableColumn<Acceptcloseaccount, String> account_number;
    @FXML
    private TableColumn<Acceptcloseaccount, String> reason;
    @FXML
    private TableColumn<Acceptcloseaccount, String> status;
    
    
    
    String user_id_global;
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    int myIndex;
    int id;
    
    @FXML
    private TextField account_number1;
    @FXML
    private TextField reason1;
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
    private void homeBtn(ActionEvent event) throws IOException, SQLException {
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
          ObservableList<Acceptcloseaccount> acceptcloseaccount = FXCollections.observableArrayList();
       try 
       {
//           String accnt = "Pending";
           pst = con.prepareStatement("SELECT * FROM close_acount");
//           pst.setString(1, accnt);
           ResultSet rs = pst.executeQuery();
            {
              while (rs.next())
              {
                  Acceptcloseaccount st = new Acceptcloseaccount();
                  st.setaccount_number(rs.getString("account_number"));
                  st.setreason(rs.getString("reason"));
                  st.setstatus(rs.getString("status"));
                  acceptcloseaccount.add(st);
             }
            } 
            table.setItems(acceptcloseaccount);
            account_number.setCellValueFactory(f -> f.getValue().account_numberProperty());
            reason.setCellValueFactory(f -> f.getValue().reasonProperty());
            status.setCellValueFactory(f -> f.getValue().statusProperty());
                
               
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(AcceptCloseaccountController.class.getName()).log(Level.SEVERE, null, ex);
       }
                table.setRowFactory( tv -> {
             TableRow<Acceptcloseaccount> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();
         
                   account_number1.setText(table.getItems().get(myIndex).getaccount_number());
                   account_number1.setEditable(false);
                   reason1.setText(table.getItems().get(myIndex).getreason());
                   reason1.setEditable(false);
                   
                 
                         
                           
                }
             });
                return myRow;
                   });
    
    
      }
    
    
    @FXML
    private void updateBtn(ActionEvent event) {
        
        String saccount_number1,sreason;
        int saccount_number11;
        
         myIndex = table.getSelectionModel().getSelectedIndex();
            
            
            saccount_number1 = account_number1.getText();
            saccount_number11 = Integer.parseInt(saccount_number1);

            sreason = reason1.getText();

            String sstatus = "Approved";
        try 
        {
            pst = con.prepareStatement("update close_acount set account_number = ? ,reason = ?, status = ? where account_number = ? ");
            pst.setInt(1, saccount_number11);
            pst.setString(2, sreason);
            pst.setString(3, sstatus);
            pst.setInt(4, saccount_number11);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Close Account Acceptance");
        
        alert.setHeaderText("Close Account Acceptance");
        alert.setContentText("Account Closed Successful!");
        alert.showAndWait();
                table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(AcceptCloseaccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        table();
    } 

    
    
}
