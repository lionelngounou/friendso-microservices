/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.friendso.user.web;

import com.google.common.base.Strings;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author lionel stephane
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class UserCommandTests {
    
    private Validator validator;
    UserCommand userCommand;
    
    @Before
    public void setUp() {
        userCommand = new UserCommand();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    private boolean hasError(String propertyPath, Class annotationClass){
        for(ConstraintViolation<UserCommand> cv : validator.validateProperty(userCommand, propertyPath)){
            //System.out.printf("\n>>>>>>> property of %s value is : %s", cv.getPropertyPath(), cv.getInvalidValue());
            if(cv.getPropertyPath().toString().equalsIgnoreCase(propertyPath) && annotationClass.isInstance(cv.getConstraintDescriptor().getAnnotation()))
                return true;
        }
        return false;
    }
           
    @Test
    public void testEmailValidation() {
        System.out.println("test email validation");
        String prop = "email";
        userCommand.setEmail("");
        assertTrue(hasError(prop, Size.class));
        userCommand.setEmail("1");
        assertFalse(hasError(prop, Size.class));
        userCommand.setEmail(Strings.padStart("", 255, '1'));
        assertFalse(hasError(prop, Size.class));
        userCommand.setEmail(Strings.padStart("", 256, '1'));
        assertTrue(hasError(prop, Size.class));
        
        assertTrue(hasError(prop, Email.class));
    }
    
    @Test
    public void testFirstnameValidation() {
        String prop = "firstname";
        userCommand.setFirstname(null);
        assertTrue(hasError(prop, NotNull.class));
        userCommand.setFirstname("");
        assertTrue(hasError(prop, Size.class));
        userCommand.setFirstname("1");
        assertFalse(hasError(prop, Size.class));
        userCommand.setFirstname(Strings.padStart("", 100, '1'));
        assertFalse(hasError(prop, Size.class));
        userCommand.setFirstname(Strings.padStart("", 101, '1'));
        assertTrue(hasError(prop, Size.class));
    }
    
    @Test
    public void testLastnameValidation() {
        String prop = "lastname";
        userCommand.setLastname(null);
        assertTrue(hasError(prop, NotNull.class));
        userCommand.setLastname("");
        assertTrue(hasError(prop, Size.class));
        userCommand.setLastname("1");
        assertFalse(hasError(prop, Size.class));
        userCommand.setLastname(Strings.padStart("", 100, '1'));
        assertFalse(hasError(prop, Size.class));
        userCommand.setLastname(Strings.padStart("", 101, '1'));
        assertTrue(hasError(prop, Size.class));
    }
    
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        
        userCommand.setEmail("....");
        userCommand.setFirstname("....");
        userCommand.setLastname("....");
        assertFalse(userCommand.isEmpty());
        
        userCommand.setEmail(null);
        assertFalse(userCommand.isEmpty());
        
        userCommand.setFirstname(null);
        assertFalse(userCommand.isEmpty());
        
        userCommand.setLastname(null);
        assertTrue(userCommand.isEmpty());
    }
    
}
