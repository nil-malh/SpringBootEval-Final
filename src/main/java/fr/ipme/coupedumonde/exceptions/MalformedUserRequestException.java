package fr.ipme.coupedumonde.exceptions;

public class MalformedUserRequestException extends RuntimeException{

    public MalformedUserRequestException(String requestType) {
        super("Can't " + requestType + " from provided data, some information are missing.");
    }
    public MalformedUserRequestException(Long id,String requestType) {
        super("Can't " + requestType + " from provided data, some information are missing. (UserID : " + id +").");
    }
    public MalformedUserRequestException() {
        super("Can't process request, from provided data, some information are missing.");
    }
}
