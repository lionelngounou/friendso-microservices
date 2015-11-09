package sample.friendso.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lionel Stephane
 */
@RestController
public class UserController {
    
    private UserRepository userRepository;
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(@RequestParam(value="email") String email){
        System.out.println("@@@%  find user by " + email);
        return userRepository.findByEmail(email);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
}
