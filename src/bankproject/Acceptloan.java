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
public class Acceptloan {
    
    private final StringProperty customer_name;
    private final StringProperty account_number;
    private final StringProperty loan_type;
    private final StringProperty loan_amount;
    private final StringProperty num_year;
    private final StringProperty status;
     
    public Acceptloan()
    {
        customer_name = new SimpleStringProperty(this, "customer_name");
        account_number = new SimpleStringProperty(this, "account_number");
        loan_type = new SimpleStringProperty(this, "loan_type");
        loan_amount = new SimpleStringProperty(this, "loan_amount");
        num_year = new SimpleStringProperty(this, "num_year");
        status = new SimpleStringProperty(this, "status");
    }
    public StringProperty customer_nameProperty() { return customer_name; }
    public String getcustomer_name() { return customer_name.get(); }
    public void setcustomer_name(String newcustomer_name) { customer_name.set(newcustomer_name); }
    
    public StringProperty account_numberProperty() { return account_number; }
    public String getaccount_number() { return account_number.get(); }
    public void setaccount_number(String newaccount_number) { account_number.set(newaccount_number); }
    
    public StringProperty loan_typeProperty() { return loan_type; }
    public String getloan_type() { return loan_type.get(); }
    public void setloan_type(String newloan_type) { loan_type.set(newloan_type); }
    
    
    
    
    public StringProperty loan_amountProperty() { return loan_amount; }
    public String getloan_amount() { return loan_amount.get(); }
    public void setloan_amount(String newloan_amount) { loan_amount.set(newloan_amount); }
    
    public StringProperty num_yearProperty() { return num_year; }
    public String getnum_year() { return num_year.get(); }
    public void setnum_year(String newnum_year) { num_year.set(newnum_year); }
    
    public StringProperty statusProperty() { return status; }
    public String getstatus() { return status.get(); }
    public void setstatus(String newstatus) { status.set(newstatus); }
    
}
