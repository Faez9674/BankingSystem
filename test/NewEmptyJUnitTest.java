/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    
    public void addLogin(){
            NewEmptyJUnitTest test = new NewEmptyJUnitTest();
            String expected = "John";
            String actual;
            actual = test.addLogin("Max");
            assertEquals(expected, actual, "Working");
        }
    
    public void addRegistration(){
            NewEmptyJUnitTest test = new NewEmptyJUnitTest();
            String expected = "John";
            String actual;
            actual = test.addRegistration("Max");
            assertEquals(expected, actual, "Working");
        }
    
    
    
     public String addAccount(String a){
            return a;
        }
    
    public String addAdmin(String b){
            return b;
        }
    
    public String addCustomer(String a){
            return a;
        }
    
    public String depositeMoney(String b){
            return b;
        }
    
    public String addMoney(String a){
            return a;
        }
    
    public String addLoan(String b){
            return b;
        }
    
    public String removeAccount(String a){
            return a;
        }
    
    public String approveLoan(String b){
            return b;
        }
    
    public String addDetails(String a){
            return a;
        }
    
    public String tranferMoney (String b){
            return b;
        }
    
    
    public String transferMoneyOther(String a){
            return a;
        }
    
    public String balanceCheck(String b){
            return b;
        }
    
    
    public String viewDetails(String a){
            return a;
        }
    
    public String viewLoan(String b){
            return b;
        }
    
    
    public String viewAccount(String a){
            return a;
        }
    
    public String transferMoneySelf(String b){
            return b;
        }
    
    
    public String withdrawMoney(String a){
            return a;
        }
    
    public String checkData(String b){
            return b;
        }

    private String addLogin(String max) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String addRegistration(String max) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
