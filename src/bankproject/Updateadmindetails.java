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
public class Updateadmindetails {
    
    private final StringProperty user_name;
    private final StringProperty address;
    private final StringProperty contact;
    private final StringProperty user_id;
    private final StringProperty password;
    private final StringProperty email;
     
    public Updateadmindetails()
    {
        user_name = new SimpleStringProperty(this, "user_name");
        address = new SimpleStringProperty(this, "address");
        contact = new SimpleStringProperty(this, "contact");
        user_id = new SimpleStringProperty(this, "user_id");
        password = new SimpleStringProperty(this, "password");
        email = new SimpleStringProperty(this, "email");
    }
    public StringProperty user_nameProperty() { return user_name; }
    public String getuser_name() { return user_name.get(); }
    public void setuser_name(String newuser_name) { user_name.set(newuser_name); }
    
    public StringProperty addressProperty() { return address; }
    public String getaddress() { return address.get(); }
    public void setaddress(String newaddress) { address.set(newaddress); }
    
    public StringProperty contactProperty() { return contact; }
    public String getcontact() { return contact.get(); }
    public void setcontact(String newcontact) { contact.set(newcontact); }
    
    
    
    
    public StringProperty user_idProperty() { return user_id; }
    public String getuser_id() { return user_id.get(); }
    public void setuser_id(String newuser_id) { user_id.set(newuser_id); }
    
    public StringProperty passwordProperty() { return password; }
    public String getpassword() { return password.get(); }
    public void setpassword(String newpassword) { password.set(newpassword); }
    
    public StringProperty emailProperty() { return email; }
    public String getemail() { return email.get(); }
    public void setemail(String newemail) { email.set(newemail); }
    
}
