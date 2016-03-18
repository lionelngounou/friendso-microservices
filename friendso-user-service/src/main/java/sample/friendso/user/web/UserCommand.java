package sample.friendso.user.web;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 * @author Lionel Stephane
 */
public class UserCommand implements Serializable{

    public UserCommand() {
    }    
    
    @Email
    @Size(min = 1, max = 255)
    private String email;
    
    @NotNull
    @Size(min = 1, max = 100)
    private String firstname; 
    
    @NotNull
    @Size(min = 1, max = 100)
    private String lastname;
    
    private String aliasName;
    
    private String password;

    @Override
    public String toString() {
        return "UserCommand {" + "email=" + email + ", password=" + password + ", aliasName=" + aliasName + ", firstname=" + firstname + ", lastname=" + lastname + '}';
    }
        
    public boolean isEmpty(){
        if(firstname!=null)
            return false;
        if(lastname!=null)
            return false;
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
