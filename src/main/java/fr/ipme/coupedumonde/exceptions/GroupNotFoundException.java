package fr.ipme.coupedumonde.exceptions;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(Long id, String requestType) {
        super("Can't " + requestType + " the requested group is not found. (GroupID : " + id + ")");
    }

    public GroupNotFoundException(Long id) {
        super("Can't process request, the requested group is not found. (GroupID : " + id + ")");
    }
}
