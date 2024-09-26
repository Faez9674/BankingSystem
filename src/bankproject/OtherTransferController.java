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
public class OtherTransferController implements Initializable {

    @FXML
    private TextField from_account_number;
    @FXML
    private TextField to_account_number;
    @FXML
    private TextField amount;
    @FXML
    private Button othertransferbtn;
    @FXML
    private Button backtranferbtn;
    
    String user_id_global;
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3;
    PreparedStatement pst4;
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
            String query = "SELECT * FROM account_details WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String acc_num = resultSet.getString("account_number");
                from_account_number.setText(acc_num);
                from_account_number.setEditable(false);
            } else {
                
            }
        } else {
//            
        }

    }
        

    @FXML
    private void othertransferBtn(ActionEvent event) throws IOException {
        String sfrom_account,sto_account, samount;
            sfrom_account = from_account_number.getText();
            sto_account = to_account_number.getText();
            samount = amount.getText();
            int sfrom_account_data = Integer.parseInt(sfrom_account);
            int sto_account_data = Integer.parseInt(sto_account);
            int samount_data = Integer.parseInt(samount);
            
        try 
        {
            
            pst1 = con.prepareStatement("SELECT * FROM account_details WHERE account_number = ?");
            pst1.setInt(1, sfrom_account_data);
            ResultSet resultSet = pst1.executeQuery();
            
            
            pst4 = con.prepareStatement("SELECT * FROM account_details WHERE account_number = ?");
            pst4.setInt(1, sto_account_data);
            ResultSet resultSet1 = pst4.executeQuery();
            
            
            if (resultSet.next()) {
                int account_balance_dat = resultSet.getInt("balance");
                int final_account_balance_dat = account_balance_dat-samount_data;
                
                pst = con.prepareStatement("insert into transfer_money(sender_account,receiver_acoount,amount)values(?,?,?)");
                pst.setInt(1, sfrom_account_data);
                pst.setInt(2, sto_account_data);
                pst.setInt(3, samount_data);
                pst.executeUpdate();
                
                pst2 = con.prepareStatement("UPDATE account_details SET balance = ? WHERE account_number = ?");
                pst2.setInt(1, final_account_balance_dat);
                pst2.setInt(2, sfrom_account_data);
                pst2.executeUpdate();
                
                if(resultSet1.next()){
                    int account_balance_dat1 = resultSet1.getInt("balance");
                    int final_account_balance_dat1 = account_balance_dat1+samount_data;
                    
                    pst3 = con.prepareStatement("UPDATE account_details SET balance = ? WHERE account_number = ?");
                    pst3.setInt(1, final_account_balance_dat1);
                    pst3.setInt(2, sto_account_data);
                    pst3.executeUpdate();
                    
                }else{
                    
                }
                

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Other Account Money Transfer");


                alert.setHeaderText("Other Account Money Transfer");
                alert.setContentText("Money Transfered Successfully!");

                alert.showAndWait();

//                Parent customerhome = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
//                Scene customerhomePage = new Scene(customerhome);
//                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//                appStage.setScene(customerhomePage);
//                appStage.show();


                FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerHome.fxml"));
                Parent root = loader.load();
                CustomerHomeController customerhomeController = loader.getController();
                customerhomeController.setUserDetails(user_id_global);
                Scene customerhomePage = new Scene(root);
                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

                appStage.setScene(customerhomePage);
                appStage.show();

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");


                alert.setHeaderText("Alert");
                alert.setContentText("Sender Account does not exist!");

                alert.showAndWait();

//                Parent customerhome = FXMLLoader.load(getClass().getResource("CustomerHome.fxml"));
//                Scene customerhomePage = new Scene(customerhome);
//                Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//                appStage.setScene(customerhomePage);
//                appStage.show();


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
        catch (SQLException ex)
        {
            Logger.getLogger(OtherTransferController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backtransferBtn(ActionEvent event) throws IOException, SQLException {
//        Parent transfermoney = FXMLLoader.load(getClass().getResource("TransferMoney.fxml"));
//        Scene transfermoneyPage = new Scene(transfermoney);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(transfermoneyPage);
//        appStage.show();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransferMoney.fxml"));
        Parent root = loader.load();
        TransferMoneyController transfermoneyController = loader.getController();
        transfermoneyController.setUserDetails1(user_id_global);
        Scene transfermoneyPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(transfermoneyPage);
        appStage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
    }
    
}
