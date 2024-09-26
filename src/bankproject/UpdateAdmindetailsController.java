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
public class UpdateAdmindetailsController implements Initializable {

    @FXML
    private Button backbtn;
    
    String user_id_global;
    
    
     
     
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    int myIndex;
    
    
    @FXML
    private TableView<Updateadmindetails> table;
    @FXML
    private TableColumn<Updateadmindetails, String> user_name;
    @FXML
    private TableColumn<Updateadmindetails, String> address;
    @FXML
    private TableColumn<Updateadmindetails, String> contact;
    @FXML
    private TableColumn<Updateadmindetails, String> user_id;
    @FXML
    private TableColumn<Updateadmindetails, String> password;
    @FXML
    private TableColumn<Updateadmindetails, String> email;
    
    
    @FXML
    private TextField user_name1;
    @FXML
    private TextField address1;
    @FXML
    private TextField contact1;
    @FXML
    private TextField user_id1;
    @FXML
    private TextField password1;
    @FXML
    private TextField email1;
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
        table();

    }
    
    
    public void table()
      {
          Connect();
          ObservableList<Updateadmindetails> updateadmindetails = FXCollections.observableArrayList();
       try 
       {
           String accnt = user_id_global;
           pst = con.prepareStatement("SELECT * FROM admin_user WHERE user_id = ?");
           pst.setString(1, accnt);
           ResultSet rs = pst.executeQuery();
            {
              while (rs.next())
              {
                  Updateadmindetails st = new Updateadmindetails();
                  st.setuser_name(rs.getString("user_name"));
                  st.setaddress(rs.getString("address"));
                  st.setcontact(rs.getString("contact"));
                  st.setuser_id(rs.getString("user_id"));
                  st.setpassword(rs.getString("password"));
                  st.setemail(rs.getString("email"));
                  updateadmindetails.add(st);
             }
            } 
            table.setItems(updateadmindetails);
            user_name.setCellValueFactory(f -> f.getValue().user_nameProperty());
            address.setCellValueFactory(f -> f.getValue().addressProperty());
            contact.setCellValueFactory(f -> f.getValue().contactProperty());
            user_id.setCellValueFactory(f -> f.getValue().user_idProperty());
            password.setCellValueFactory(f -> f.getValue().passwordProperty());
            email.setCellValueFactory(f -> f.getValue().emailProperty());
                
               
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(UpdateAdmindetailsController.class.getName()).log(Level.SEVERE, null, ex);
       }
                table.setRowFactory( tv -> {
             TableRow<Updateadmindetails> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  table.getSelectionModel().getSelectedIndex();
         
                   
                   user_name1.setText(table.getItems().get(myIndex).getuser_name());
                   address1.setText(table.getItems().get(myIndex).getaddress());
                   contact1.setText(table.getItems().get(myIndex).getcontact());
                   user_id1.setText(table.getItems().get(myIndex).getuser_id());
                   password1.setText(table.getItems().get(myIndex).getpassword());
                   email1.setText(table.getItems().get(myIndex).getemail());
                   user_id1.setEditable(false);
                         
                           
                }
             });
                return myRow;
                   });
    
    
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
    
    @FXML
    private void updateBtn(ActionEvent event) {
        
        String suser_name1,saddress1,scontact1,suser_id1,spassword1,semail1;
        int scontact11;
        
         myIndex = table.getSelectionModel().getSelectedIndex();
         
       
           
            suser_name1 = user_name1.getText();
            saddress1 = address1.getText();
            scontact1 = contact1.getText();
            suser_id1 = user_id1.getText();
            spassword1 = password1.getText();
            semail1 = email1.getText();
            scontact11 = Integer.parseInt(scontact1);
        try 
        {
            pst = con.prepareStatement("update admin_user set user_name = ?,address = ? ,contact = ?, user_id = ?, password = ?, email = ? where user_id = ? ");
            pst.setString(1, suser_name1);
            pst.setString(2, saddress1);
            pst.setInt(3, scontact11);
            pst.setString(4, suser_id1);
            pst.setString(5, spassword1);
            pst.setString(6, semail1);
            pst.setString(7, suser_id1);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Admin Details");
        
        alert.setHeaderText("Update Admin Details");
        alert.setContentText("Update Successful!");
        alert.showAndWait();
                table();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(UpdateAdmindetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
    }   

    

}
