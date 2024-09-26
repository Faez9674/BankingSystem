/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author Admin
 */
public class Customer {
    private final StringProperty customer_id;
    private final StringProperty customer_name;
    private final StringProperty account_type;
    private final StringProperty  account_number;
    private final StringProperty  balance;
     
    public Customer()
    {
        customer_id = new SimpleStringProperty(this, "customer_id");
        customer_name = new SimpleStringProperty(this, "customer_name");
        account_type = new SimpleStringProperty(this, "account_type");
        account_number = new SimpleStringProperty(this, "account_number");
        balance = new SimpleStringProperty(this, "balance");
    }
    public StringProperty customer_idProperty() { return customer_id; }
    public String getcustomer_id() { return customer_id.get(); }
    public void setcustomer_id(String newcustomer_id) { customer_id.set(newcustomer_id); }
    
    public StringProperty customer_nameProperty() { return customer_name; }
    public String getcustomer_name() { return customer_name.get(); }
    public void setcustomer_name(String newcustomer_name) { customer_name.set(newcustomer_name); }
    
    public StringProperty account_typeProperty() { return account_type; }
    public String getaccount_type() { return account_type.get(); }
    public void setaccount_type(String newaccount_type) { account_type.set(newaccount_type); }
    
    public StringProperty account_numberProperty() { return account_number; }
    public String getaccount_number() { return account_number.get(); }
    public void setaccount_number(String newaccount_number) { account_number.set(newaccount_number); }
    
    public StringProperty balanceProperty() { return balance; }
    public String getbalance() { return balance.get(); }
    public void setbalance(String newbalance) { balance.set(newbalance); }
}
