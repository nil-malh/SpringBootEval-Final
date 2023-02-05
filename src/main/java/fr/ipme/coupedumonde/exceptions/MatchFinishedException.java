package fr.ipme.coupedumonde.exceptions;

public class MatchFinishedException extends RuntimeException {


    public MatchFinishedException(Long id) {
        super("The match is already finished, you can't place a bet on it. (MatchID : " + id + ").");
    }
}
