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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CheckTransfehistoryController implements Initializable {

    @FXML
    private Button backbtn;
    
    @FXML
    private TableView<Transferhistory> table;
    @FXML
    private TableColumn<Transferhistory, String> sendfrom;
    @FXML
    private TableColumn<Transferhistory, String> sendto;
    @FXML
    private TableColumn<Transferhistory, String> tranamount;
    
    
    String user_id_global;
    
    String user_account_global;
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3;
    PreparedStatement pst4;
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
                String acc_num = resultSet.getString("account_number");
                user_account_global = acc_num;
                table();
            } else {
                
            }
        } else {
//            
        }
        

    }
    
    
    
    public void table()
      {
          Connect();
          ObservableList<Transferhistory> transfers = FXCollections.observableArrayList();
       try 
       {
           int accnt = Integer.parseInt(user_account_global);
           pst = con.prepareStatement("SELECT * FROM transfer_money WHERE sender_account = ?");
           pst.setInt(1, accnt);
           ResultSet rs = pst.executeQuery();
            {
              while (rs.next())
              {
                  Transferhistory st = new Transferhistory();
                  st.setsendfrom(rs.getString("sender_account"));
                  st.setsendto(rs.getString("receiver_acoount"));
                  st.settranamount(rs.getString("amount"));
                  transfers.add(st);
             }
            } 
            table.setItems(transfers);
            sendfrom.setCellValueFactory(f -> f.getValue().sendfromProperty());
            sendto.setCellValueFactory(f -> f.getValue().sendtoProperty());
            tranamount.setCellValueFactory(f -> f.getValue().tranamountProperty());
                
               
       }
       
       catch (SQLException ex) 
       {
           Logger.getLogger(CheckTransfehistoryController.class.getName()).log(Level.SEVERE, null, ex);
       }
                table.setRowFactory( tv -> {
             TableRow<Transferhistory> myRow = new TableRow<>();
             myRow.setOnMouseClicked (event -> 
             {
                
             });
                return myRow;
                   });
    
    
      }
    
    
    @FXML
    private void backBtn(ActionEvent event) throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransferMoney.fxml"));
        Parent root = loader.load();
        TransferMoneyController transfermoneyController = loader.getController();
        transfermoneyController.setUserDetails1(user_id_global);
        Scene transfermoneyPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(transfermoneyPage);
        appStage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
        
    }    

    
    
}
