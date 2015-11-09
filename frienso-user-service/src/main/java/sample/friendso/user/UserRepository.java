package sample.friendso.user;

/**
 * @author Lionel Stephane
 */
interface UserRepository extends CruRepository<User, Integer> {
    
    User findByEmail(String email);
    
    User findByEmailAndActive(String email, Boolean active);
    
    User findByEmailAndActiveTrue(String email);
    
    User findOneByIdAndActive(Integer id, Boolean active);
    
    User findOneByIdAndActiveTrue(Integer id);
    
}
