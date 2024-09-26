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
public class Acceptcloseaccount {
    
    private final StringProperty account_number;
    private final StringProperty reason;
    private final StringProperty status;
     
    public Acceptcloseaccount()
    {
        account_number = new SimpleStringProperty(this, "account_number");
        reason = new SimpleStringProperty(this, "reason");
        status = new SimpleStringProperty(this, "status");
    }
    public StringProperty account_numberProperty() { return account_number; }
    public String getaccount_number() { return account_number.get(); }
    public void setaccount_number(String newaccount_number) { account_number.set(newaccount_number); }
    
    public StringProperty reasonProperty() { return reason; }
    public String getreason() { return reason.get(); }
    public void setreason(String newreason) { reason.set(newreason); }
    
    public StringProperty statusProperty() { return status; }
    public String getstatus() { return status.get(); }
    public void setstatus(String newstatus) { status.set(newstatus); }
    
}
