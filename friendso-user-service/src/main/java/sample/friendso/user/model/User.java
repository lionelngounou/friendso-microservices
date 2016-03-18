package sample.friendso.user.model;

import sample.friendso.user.web.UserCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;

/**
 * @author Lionel Stephane
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Email
    @NotNull
    @Size(min = 1, max = 255)
    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(unique = true, nullable = false, updatable = false)
    private String aliasName;
    
    private String password;
    
    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String firstname; 
    
    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String lastname;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssz")
    private Date dateCreated; 
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssz" /*"dd/MM/yyyy HH:mm a z"*/, timezone = "GMT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @Type(type = "yes_no")
    private Boolean active = Boolean.FALSE;

    public User() {
    }

    public User(String email, String aliasName, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.aliasName = aliasName;
    }
    
    public User(UserCommand userCommand) {
        this(userCommand.getEmail(), userCommand.getAliasName() , userCommand.getPassword(), userCommand.getFirstname(), userCommand.getLastname());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 7 * hash + Objects.hashCode(this.id);
        hash = 7 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return "US-0-" + id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private static final long serialVersionUID = -891L;
    
}
