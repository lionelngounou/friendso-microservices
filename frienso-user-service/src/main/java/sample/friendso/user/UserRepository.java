package sample.friendso.user;

/**
 * @author Lionel Stephane
 */
interface UserRepository extends CruRepository<User, Integer> {
    
    User findByEmail(String email);
    
}
