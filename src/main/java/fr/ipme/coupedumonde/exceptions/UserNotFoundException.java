package fr.ipme.coupedumonde.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id,String requestType) {
        super("Can't " + requestType + " the requested user is not found. (UserID : " + id + ")");
    }
    public UserNotFoundException(Long id) {
        super("Can't process request, the requested user is not found. (UserID : " + id + ")");
    }
}
