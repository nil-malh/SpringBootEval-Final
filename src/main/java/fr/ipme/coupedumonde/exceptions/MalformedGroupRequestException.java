package fr.ipme.coupedumonde.exceptions;

public class MalformedGroupRequestException extends RuntimeException{


    public MalformedGroupRequestException(Long id, String requestType) {
        super("Can't " + requestType + " from provided data, some information are missing (GroupID " + id + ")");
    }
    public MalformedGroupRequestException(String requestType) {
        super("Can't " + requestType + " from provided data, some information are missing (GroupID null");
    }

    public MalformedGroupRequestException(Long id) {
        super("Can't process request, from provided data, some information are missing. (GroupID : " + id + ")");
    }
}
