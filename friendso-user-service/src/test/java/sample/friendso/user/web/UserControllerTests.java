package sample.friendso.user.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import java.util.Date;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sample.friendso.user.UserApp;
import sample.friendso.user.model.User;
import sample.friendso.user.repository.UserRepository;

//import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.springframework.test.web.servlet.ResultActions;
import static sample.friendso.user.web.UserController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        String email = "existsing@user.com";
        if (!userRepository.existsByEmail(email)) {
            //setup user
            user = new User(email, "exist_user", "ppp", "firstname", "lastname");
            user.setActive(Boolean.TRUE);
            user.setDateCreated(new Date());
            userRepository.save(user);
        } 
        else {
            user = userRepository.findByEmail(email);
        }
    }
    
    @Test
    public void findByEmail() throws Exception {
        System.out.println("test findByEmail");
        
        mvc.perform(get(REQ_MAPPING)
                .param("email", user.getEmail())
            )
           .andExpect(status().isOk())
           .andExpect(jsonPath("firstname", is(user.getFirstname())))
           .andExpect(jsonPath("lastname", is(user.getLastname())))
           .andExpect(jsonPath("email", is(user.getEmail())))
           .andExpect(jsonPath("active", is(true))) //json ignored
           .andExpect(jsonPath("dateCreated", notNullValue()))
        ;
        mvc.perform(get(REQ_MAPPING)
                .param("email", "xxx" + user.getEmail())
            )
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("firstname", nullValue()))
        ;
        mvc.perform(get(REQ_MAPPING))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("firstname", nullValue()))
        ;
    }
    
    @Test
    public void findByAliasName() throws Exception {
        System.out.println("test findByAliasName");
        
        mvc.perform(get(REQ_MAPPING + "/" + user.getAliasName()))
           .andExpect(status().isOk())
           .andExpect(jsonPath("firstname", is(user.getFirstname())))
           .andExpect(jsonPath("lastname", is(user.getLastname())))
           .andExpect(jsonPath("email", is(user.getEmail())))
        ;
        mvc.perform(get(REQ_MAPPING + "/xxx" + user.getAliasName()))
           .andExpect(status().isNotFound())
           .andExpect(jsonPath("firstname", nullValue()))
        ;
    }
        
    static final String[] REQUIRED_FIELDS = new String[]{"email", "firstname", "lastname", "password"};
    
    @Test
    public void createWithoutRequiredFields() throws Exception {
        System.out.println("test create without required fields");
        ResultActions ra = mvc.perform(post(REQ_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
            );
        ra.andExpect(status().isConflict());
        ra.andExpect(jsonPath("$.errors", notNullValue()));
        for(String s: REQUIRED_FIELDS){
            ra.andExpect(jsonPath("$.errors[?(@.target == \'"+ s +"\')]").exists());
        }
    }
        
    @Test
    public void create() throws Exception {
        System.out.println("test create");
        
        Map body = Maps.newHashMap();
        body.put("email", "my.mail@create.com");
        body.put("password", "my_password");
        body.put("firstname", "my_fn");
        body.put("lastname", "my_ln");
        
        //success create
        mvc.perform(post(REQ_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(body))
            )
           .andExpect(status().isCreated())
           .andExpect(jsonPath("email", equalTo(body.get("email"))))
           .andExpect(jsonPath("firstname", equalTo(body.get("firstname"))))
           .andExpect(jsonPath("lastname", equalTo(body.get("lastname"))))
           .andExpect(jsonPath("aliasName", equalTo(buildAliasName(body.get("firstname").toString(), body.get("lastname").toString()))))
        ;
        
        //different alias name as the previous already exists
        body.put("email", "my2.mail@test.com");
        mvc.perform(post(REQ_MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(body))
            )
           .andExpect(status().isCreated())
           .andExpect(jsonPath("email", equalTo(body.get("email"))))
           .andExpect(jsonPath("aliasName",
                   equalTo(buildAliasName(body.get("firstname").toString(), body.get("lastname").toString() + 1))))
        ;
    }
        
    @Test
    public void update() throws Exception {
        
    }
        
    @Test
    public void delete() throws Exception {
        
    }
    
    @Test
    public void generateAliasName(){
        System.out.println("test generateAliasName");
        
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        controller.setUserRepository(mockUserRepository);        
        UserCommand uc = new UserCommand();
        uc.setFirstname("first"); uc.setLastname("last");
        String builtAliasName = buildAliasName(uc.getFirstname(), uc.getLastname());
        
        Mockito.when(mockUserRepository.existsByAliasName(builtAliasName)).thenReturn(Boolean.FALSE);
        assertThat(controller.generateAliasName(uc, 0), is(builtAliasName));
        
        Mockito.when(mockUserRepository.existsByAliasName(builtAliasName)).thenReturn(Boolean.TRUE);
        assertThat(controller.generateAliasName(uc, 0), is(builtAliasName + 1));
        
        Mockito.when(mockUserRepository.existsByAliasName(builtAliasName+1)).thenReturn(Boolean.TRUE);
        assertThat(controller.generateAliasName(uc, 0), is(builtAliasName + 2));
        
        //and so on recursively, if you wish...
    }
    
    @Test
    public void _buildAliasName(){
        System.out.println("test buildAliasName");
        
        assertThat(buildAliasName("l", "n"), is("l_n"));
        assertThat(buildAliasName("l k", "n"), is("lk_n"));
        assertThat(buildAliasName("l k", "n   o"), is("lk_no"));
    }
}
