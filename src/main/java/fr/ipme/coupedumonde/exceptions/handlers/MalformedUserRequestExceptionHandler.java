package fr.ipme.coupedumonde.exceptions.handlers;


import fr.ipme.coupedumonde.exceptions.MalformedUserRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MalformedUserRequestExceptionHandler {


    @ResponseBody
    @ExceptionHandler(MalformedUserRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String malformedUserCreationRequestHandler(MalformedUserRequestException e){
        return e.getMessage();
    }

}
