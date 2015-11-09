package sample.friendso.user;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Lionel Stephane
 */
public class UserForm implements Serializable{

    public UserForm() {
    }    
    
    private String firstname, lastname, email;
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
}
