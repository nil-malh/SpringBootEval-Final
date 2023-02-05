package fr.ipme.coupedumonde.exceptions;

public class MalformedPronosticRequestException extends RuntimeException {


    public MalformedPronosticRequestException(Long id, String requestType) {
        super("Can't " + requestType + " from provided data, some information are missing (GroupID " + id + ")");
    }
    public MalformedPronosticRequestException(String requestType) {
        super("Can't " + requestType + " from provided data, some information are missing (GroupID null");
    }

    public MalformedPronosticRequestException(Long id) {
        super("Can't process request, from provided data, some information are missing. (GroupID : " + id + ")");
    }

}
