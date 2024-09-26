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
public class MessageAdmin {
    
    private final StringProperty customer_name;
    private final StringProperty message;
     
    public MessageAdmin()
    {
        customer_name = new SimpleStringProperty(this, "customer_name");
        message = new SimpleStringProperty(this, "message");

    }
    public StringProperty customer_nameProperty() { return customer_name; }
    public String getcustomer_name() { return customer_name.get(); }
    public void setcustomer_name(String newcustomer_name) { customer_name.set(newcustomer_name); }
    
    public StringProperty messageProperty() { return message; }
    public String getmessage() { return message.get(); }
    public void setmessage(String newmessage) { message.set(newmessage); }
    
    
}
