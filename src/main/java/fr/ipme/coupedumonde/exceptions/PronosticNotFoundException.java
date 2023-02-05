package fr.ipme.coupedumonde.exceptions;



public class PronosticNotFoundException extends RuntimeException {

    public PronosticNotFoundException(Long id, String requestType) {
        super("Can't " + requestType + " the requested group is not found. (PronosticID : " + id + ")");
    }

    public PronosticNotFoundException(Long id) {
        super("Can't process request, the requested group is not found. (PronosticID : " + id + ")");
    }
}
