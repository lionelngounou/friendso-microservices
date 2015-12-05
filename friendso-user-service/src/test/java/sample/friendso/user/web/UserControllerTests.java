package sample.friendso.user.web;

import java.util.Date;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import sample.friendso.user.UserApp;
import sample.friendso.user.model.User;
import sample.friendso.user.repository.UserRepository;

/**
 * @author lionel stephane
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApp.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class UserControllerTests {
    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private UserRepository userRepository;
    
    private MockMvc mvc;
    
    @InjectMocks
    UserController controller;
    
    User user;
    
    private final static String REQ_MAPPING = "/api/v1/users";
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        //setup user
        user = new User("existsing@user.com", "ppp", "firstname", "lastname");
        user.setActive(Boolean.TRUE);
        user.setDateCreated(new Date());
        userRepository.save(user);
    }
    
    @Test
    public void testFind() throws Exception {
        mvc.perform(get(REQ_MAPPING)
                .param("email", user.getEmail())
            )
           .andExpect(status().isOk())
           .andExpect(jsonPath("firstname", is(user.getFirstname())))
           .andExpect(jsonPath("lastname", is(user.getLastname())))
           .andExpect(jsonPath("email", is(user.getEmail())))
           .andExpect(jsonPath("active", equalTo(null))) //json ignored
           .andExpect(jsonPath("dateCreated", notNullValue()))
        ;
        mvc.perform(get(REQ_MAPPING)
                .param("email", "xxx" + user.getEmail())
            )
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("firstname", equalTo(null)))
        ;
        mvc.perform(get(REQ_MAPPING))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("firstname", equalTo(null)))
        ;
    }
        
    @Test
    public void testCreate() throws Exception {
        mvc.perform(post(REQ_MAPPING)
                .param("firstname", "my_fn")
            )
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("firstname", equalTo(null)))
        ;
    }
        
    @Test
    public void testUpdate() throws Exception {
        
    }
        
    @Test
    public void testDelete() throws Exception {
        
    }
    
}
