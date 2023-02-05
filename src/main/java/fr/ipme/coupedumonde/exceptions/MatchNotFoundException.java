package fr.ipme.coupedumonde.exceptions;

public class MatchNotFoundException extends RuntimeException{

    public MatchNotFoundException(Long id, String requestType) {
        super("Can't " + requestType + " the requested group is not found. (MatchID : " + id + ")");
    }

    public MatchNotFoundException(Long id) {
        super("Can't process request, the requested group is not found. (MatchID : " + id + ")");
    }


}
