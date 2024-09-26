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
import javafx.scene.control.Alert;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class TransferMoneyController implements Initializable {

    @FXML
    private Button backHome;
    @FXML
    private Button selfBtn;
    @FXML
    private Button otherBtn;
    
    @FXML
    private Button chktranhistbtn;
    
    String user_id_global;
    
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
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
    private void selfbtn(ActionEvent event) throws IOException, SQLException {
//        Parent selftransfer = FXMLLoader.load(getClass().getResource("SelfTransfer.fxml"));
//        Scene selftransferPage = new Scene(selftransfer);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(selftransferPage);
//        appStage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelfTransfer.fxml"));
        Parent root = loader.load();
        SelfTransferController selftransferController = loader.getController();
        selftransferController.setUserDetails1(user_id_global);
        Scene selftransferPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(selftransferPage);
        appStage.show();
    }

    @FXML
    private void otherbtn(ActionEvent event) throws IOException, SQLException {
//        Parent othertransfer = FXMLLoader.load(getClass().getResource("OtherTransfer.fxml"));
//        Scene othertransferPage = new Scene(othertransfer);
//        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        
//        appStage.setScene(othertransferPage);
//        appStage.show();
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OtherTransfer.fxml"));
        Parent root = loader.load();
        OtherTransferController othertransferController = loader.getController();
        othertransferController.setUserDetails1(user_id_global);
        Scene othertransferPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(othertransferPage);
        appStage.show();
    }
    
    
    @FXML
    private void chktranhistBtn(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckTransfehistory.fxml"));
        Parent root = loader.load();
        CheckTransfehistoryController checkTransfehistoryController = loader.getController();
        checkTransfehistoryController.setUserDetails1(user_id_global);
        Scene checkTransfehistoryPage = new Scene(root);
        Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        appStage.setScene(checkTransfehistoryPage);
        appStage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Connect();
    }  

    
    
}
