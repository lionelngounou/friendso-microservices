package sample.friendso.user.web;

import sample.friendso.user.repository.UserRepository;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sample.friendso.user.model.User;
import static sample.friendso.user.web.ControllerExceptionAdvice.FAIL_MAP;
import static sample.friendso.user.web.ControllerExceptionAdvice.SUCCESS_MAP;

/**
 * @author Lionel Stephane
 */
@RequestMapping(value = "/api/v1/users")
@RestController
public class UserController {
    
    /*-----------------------INIT--------------------*/
    
    static Log log = LogFactory.getLog(UserController.class.getName());
    
    private UserRepository userRepository;    

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /*-----------------------API--------------------*/
        
    @RequestMapping(method = RequestMethod.GET)
    public User find(@RequestParam(required = false) String email){
        log.info("find user by email : " + email);
        return check(userRepository.findByEmailAndActiveTrue(check(email)));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@Valid @RequestBody UserCommand userCommand, BindingResult result) {
        log.info("create user with details : " + userCommand);
        
        //validation handling
        if(validate(userCommand, result, true).hasErrors())
            return new ResponseEntity(getFailureMap(result), HttpStatus.CONFLICT);
        
        //set props and persists
        User user = new User(userCommand);
        user.setActive(Boolean.TRUE); 
        user.setDateCreated(new Date());
        userRepository.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map> update(@PathVariable(value="id") Integer id, @Valid @RequestBody UserCommand userCommand, BindingResult result) {        
        log.info("update user by id : " + id + ", with details : " + userCommand);
        
        //validation handling
        if(validate(userCommand, result, false).hasErrors())
            return new ResponseEntity(getFailureMap(result), HttpStatus.CONFLICT);
        User user = check(userRepository.findOneByIdAndActiveTrue(check(id)));
        
        //set props and persists
        user.setFirstname(userCommand.getFirstname());
        user.setLastname(userCommand.getLastname());
        userRepository.save(user);
        return new ResponseEntity(SUCCESS_MAP, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public Map delete(@PathVariable(value="id") Integer id){
        log.info("delete user by id : " + id);
        
        User user = check(userRepository.findOneByIdAndActiveTrue(check(id)));
        user.setActive(Boolean.FALSE);
        userRepository.save(user);
        return SUCCESS_MAP;
    }
    
    /*-----------------------VALIDATION--------------------*/
    
    private User check(User user){
        if(user==null)
            throw new ResourceNotFoundException("could not find user");
        return user;
    }
    
    private Integer check(Integer userId){
        if(userId==null)
            throw new ResourceNotFoundException("could not find user : " + userId);
        return userId;
    }
    
    private String check(String email){
        if(email==null)
            throw new ResourceNotFoundException("could not find user with email : " + email);
        return email;
    }
    
    private Map getFailureMap(BindingResult result){
        Map<String, Object> em = new LinkedHashMap(FAIL_MAP);
        em.put("errors", ValidationError.getAll(result));
        return em;
    }  
    
    //todo : defer this to a validator class
    private BindingResult validate(UserCommand userCommand, BindingResult result, boolean isNew){
        if(userCommand.isEmpty()){
            result.reject("details.required", "details required");
        }
        if(isNew){
            if(userCommand.getEmail()==null)
                result.rejectValue("email", "NotNull.userCommand.email", "may not be null"); 
            else if(userRepository.existsByEmail(userCommand.getEmail()))
                result.rejectValue("email", "Unique.userCommand.email", "already exists");   
        }   
        return result;
    }  
    
}
