package sample.friendso.user.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.Date;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sample.friendso.user.UserApp;
import sample.friendso.user.model.User;

/**
 * @author lionel stephane
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UserApp.class)
@ActiveProfiles("test")
public class UserRepositoryTests {
    
    private User user;
    
    @Autowired 
    private UserRepository userRepository;
    
    static final String ORIGINAL_EMAIL = "email@test.com";
    static final String ORIGINAL_ALIAS = "user_test";
    static final String ORIGINAL_PASSWORD = "password"; 
    static final String ORIGINAL_FIRSTNAME = "u_firstname";
    static final String ORIGINAL_LASTNAME = "u_lastname";
    
    @Before
    public void setUp() {
        user = new User(ORIGINAL_EMAIL, ORIGINAL_ALIAS, ORIGINAL_PASSWORD,  ORIGINAL_FIRSTNAME, ORIGINAL_LASTNAME);
        user.setActive(Boolean.TRUE);
        user.setDateCreated(new Date());
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindByEmail() {
        System.out.println("findByEmail");
        
        assertThat(userRepository.findByEmail(save().getEmail()), is(user));
        assertNull(userRepository.findByEmail("null.".concat(ORIGINAL_EMAIL)));
    }

    @Test
    public void testFindByEmailAndActive() {
        System.out.println("findByEmailAndActive");
        
        assertThat(userRepository.findByEmailAndActive(save().getEmail(), Boolean.TRUE), is(user));
        assertNull(userRepository.findByEmailAndActive(deactivate().getEmail(), Boolean.TRUE));
    }

    @Test
    public void testFindByEmailAndActiveTrue() {
        System.out.println("findByEmailAndActiveTrue");
        
        assertThat(userRepository.findByEmailAndActiveTrue(save().getEmail()), is(user));
        assertNull(userRepository.findByEmailAndActiveTrue(deactivate().getEmail()));
    }

    @Test
    public void testFindOneByIdAndActive() {
        System.out.println("findOneByIdAndActive");
        
        assertThat(userRepository.findOneByIdAndActive(save().getId(), Boolean.TRUE), is(user));
        assertNull(userRepository.findOneByIdAndActive(deactivate().getId(), Boolean.TRUE));
    }

    @Test
    public void testFindOneByIdAndActiveTrue() {
        System.out.println("findOneByIdAndActiveTrue");
        
        assertThat(userRepository.findOneByIdAndActiveTrue(save().getId()), is(user));
        assertNull(userRepository.findOneByIdAndActiveTrue(deactivate().getId()));
    }

    @Test
    public void testExistsByEmail() {
        System.out.println("existsByEmail");
        
        assertTrue(userRepository.existsByEmail(save().getEmail()));
        assertFalse(userRepository.existsByEmail(deactivate().getEmail()));
    }
    
    @Test
    public void testExistsByAliasName() {
        System.out.println("existsByAliasName");
        
        assertTrue(userRepository.existsByAliasName(save().getAliasName()));
        assertFalse(userRepository.existsByAliasName(deactivate().getAliasName()));
    }
    
    private User deactivate(){
        user.setActive(Boolean.FALSE);
        return save();
    }
    
    private User save(){
        return userRepository.save(user);
    }
    
}
