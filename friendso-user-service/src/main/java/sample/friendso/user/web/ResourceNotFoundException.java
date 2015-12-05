package sample.friendso.user.web;

/**
 * @author lionel ngounou
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
    }
    
}
