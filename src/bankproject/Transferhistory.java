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
public class Transferhistory {
    
    private final StringProperty sendfrom;
    private final StringProperty sendto;
    private final StringProperty tranamount;
     
    public Transferhistory()
    {
        sendfrom = new SimpleStringProperty(this, "sendfrom");
        sendto = new SimpleStringProperty(this, "sendto");
        tranamount = new SimpleStringProperty(this, "tranamount");
    }
    public StringProperty sendfromProperty() { return sendfrom; }
    public String getsendfrom() { return sendfrom.get(); }
    public void setsendfrom(String newcustomer_id) { sendfrom.set(newcustomer_id); }
    
    public StringProperty sendtoProperty() { return sendto; }
    public String getsendto() { return sendto.get(); }
    public void setsendto(String newcustomer_name) { sendto.set(newcustomer_name); }
    
    public StringProperty tranamountProperty() { return tranamount; }
    public String gettranamount() { return tranamount.get(); }
    public void settranamount(String newaccount_type) { tranamount.set(newaccount_type); }
    
}
