package fr.ipme.coupedumonde.exceptions;

public class UnauthorizedBetException extends RuntimeException{


    public UnauthorizedBetException(Long id) {
        super("Can't place a bet, you don't have the permission. (PronosticID : " + id + ")");
    }
}
