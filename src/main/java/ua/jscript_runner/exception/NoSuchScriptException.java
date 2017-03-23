package ua.jscript_runner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such script")
public class NoSuchScriptException extends ScriptServiceException {
    private static final String DEFAULT_MSG = "Script not exist";

    public NoSuchScriptException() {
        super(DEFAULT_MSG);
    }

    public NoSuchScriptException(String msg) {
        super(msg);
    }
}
