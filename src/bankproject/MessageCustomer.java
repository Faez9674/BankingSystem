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
public class MessageCustomer {
    
    private final StringProperty admin_name;
    private final StringProperty message;
     
    public MessageCustomer()
    {
        admin_name = new SimpleStringProperty(this, "admin_name");
        message = new SimpleStringProperty(this, "message");

    }
    public StringProperty admin_nameProperty() { return admin_name; }
    public String getadmin_name() { return admin_name.get(); }
    public void setadmin_name(String newadmin_name) { admin_name.set(newadmin_name); }
    
    public StringProperty messageProperty() { return message; }
    public String getmessage() { return message.get(); }
    public void setmessage(String newmessage) { message.set(newmessage); }
    
   
}
