package sample.friendso.user;

import java.util.Arrays;
import sample.friendso.user.model.User;
import sample.friendso.user.repository.UserRepository;
import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * @author Lionel Stephane
 */
//@EnableCircuitBreaker
//@EnableDiscoveryClient
@SpringBootApplication
public class UserApp /*extends RepositoryRestConfigurerAdapter*/ {

        /*
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(User.class);
	}
    */
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);  
    }
    
    @Bean
    CommandLineRunner bootstrap(final UserRepository userRepository){
        return new CommandLineRunner (){
            @Override
            public void run(String[] args) throws Exception{
                System.out.println("@@@% CommandLineRunner with arguments : " + Arrays.deepToString(args));
                User user = new User("test@user.com", "test_user","password", "test", "user");
                user.setActive(Boolean.TRUE);
                user.setDateCreated(new Date());
                userRepository.save(user);
            }
        };
    }
    
}