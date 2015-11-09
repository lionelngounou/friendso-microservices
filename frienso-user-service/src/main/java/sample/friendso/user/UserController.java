package sample.friendso.user;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lionel Stephane
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    
    private UserRepository userRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public User getUser(@RequestParam String email){
        System.out.println("@@@% find user by email : " + email);
        return userRepository.findByEmailAndActiveTrue(email);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserForm userForm) {
        System.out.println("@@@% create user : " + userForm);
        User user = new User(userForm);
        user.setActive(Boolean.TRUE); 
        user.setDateCreated(new Date());
        return userRepository.save(user);
    }
    
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void update(@PathVariable(value="userId") Integer userId, @RequestBody UserForm userForm) {
        System.out.println("@@@% update user by id : " + userId + ", details : " + userForm);
        checkUserId(userId);
        User user = userRepository.findOneByIdAndActive(userId, Boolean.TRUE);
        checkUser(user);
        String firstname = userForm.getFirstname(), lastname = userForm.getLastname();
        if(firstname!=null)
            user.setFirstname(firstname);
        if(lastname!=null)
            user.setLastname(lastname);
        if(firstname!=null || lastname!=null)
            userRepository.save(user);
    }
    
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(value="userId") Integer userId){
        System.out.println("@@@% delete user by id : " + userId);
        checkUserId(userId);
        User user = userRepository.findOneByIdAndActive(userId, Boolean.TRUE);
        checkUser(user);
        user.setActive(Boolean.FALSE);
        userRepository.save(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    private void checkUser(User user){
        if(user==null)
            throw new ResourceNotFoundException("could not find user");
    }
    
    private void checkUserId(Integer userId){
        if(userId==null)
            throw new ResourceNotFoundException("could not find user : " + userId);
    }
    
}
