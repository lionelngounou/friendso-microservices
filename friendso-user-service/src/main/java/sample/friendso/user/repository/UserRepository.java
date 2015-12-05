package sample.friendso.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.friendso.user.model.User;

/**
 * @author Lionel Stephane
 */
public interface UserRepository extends CruRepository<User, Integer> {
    
    User findByEmail(String email);
    
    User findByEmailAndActive(String email, Boolean active);
    
    User findByEmailAndActiveTrue(String email);
    
    User findOneByIdAndActive(Integer id, Boolean active);
    
    User findOneByIdAndActiveTrue(Integer id);
    
    @Query("select count(u)>0 from User u where u.active = true and u.email = :email")
    boolean existsByEmail(@Param("email") String email);
    
}
