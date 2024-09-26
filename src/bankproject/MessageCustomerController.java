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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MessageCustomerController implements Initializable {

    @FXML
    private Button backbtn;
    
    
    String user_id_global;
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    int myIndex;
    int id;
    @FXML
    private TextArea text_message;
    @FXML
    private Button addbtn;
    @FXML
    private TextField customer_name;
    @FXML
    private TableView<MessageCustomer> table;
    @FXML
    private TableColumn<MessageCustomer, String> admin_name1;
    @FXML
    private TableColumn<MessageCustomer, String> message1;
    
    
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
            String query = "SELECT * FROM account_details WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String cust_name = resultSet.getString("customer_name");
                customer_name.setText(cust_name);
                customer_name.setEditable(false);
            } else {
                
            }
        } else {
//            
        }
        
        

        
    }

    /**
     * Initializes the controller class.
     */
        

    @FXML
    private void backBtn(ActionEvent event) throws IOException, SQLException {
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
    private void addBtn(ActionEvent event) throws IOException {
        
        String scustomer_name,smessage;
            scustomer_name = customer_name.getText();
            smessage = text_message.getText();
            
        try 
        {
            

            
          
            pst = con.prepareStatement("insert into message(customer_name,message)values(?,?)");
            pst.setString(1, scustomer_name);
            pst.setString(2, smessage);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");


            alert.setHeaderText("Message");
            alert.setContentText("Message Sent Successfully!");

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
        catch (SQLException ex)
        {
            Logger.getLogger(MessageCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void table()
      {
          Connect();
          ObservableList<MessageCustomer> messagecustomers = FXCollections.observableArrayList();
       try 
       {
           pst = con.prepareStatement("SELECT * FROM message_admin");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            MessageCustomer st = new MessageCustomer();
            st.setadmin_name(rs.getString("admin_name"));
            st.setmessage(rs.getString("message"));
            messagecustomers.add(st);
       }
    } 
                table.setItems(messagecustomers);
                admin_name1.setCellValueFactory(f -> f.getValue().admin_nameProperty());
                message1.setCellValueFactory(f -> f.getValue().messageProperty());
      
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(MessageCustomerController.class.getName()).log(Level.SEVERE, null, ex);
       }
                table.setRowFactory( tv -> {
             TableRow<MessageCustomer> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                
             });
                return myRow;
                   });
    
    
      }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        table();
    }
    
}
