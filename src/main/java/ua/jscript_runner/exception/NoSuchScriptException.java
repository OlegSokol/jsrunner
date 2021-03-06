package ua.jscript_runner.exception;

/**
 * Signals that the specified script not exist.
 */
public class NoSuchScriptException extends ScriptServiceException {
    private static final String DEFAULT_MSG = "Script not exist";

    public NoSuchScriptException() {
        super(DEFAULT_MSG);
    }

    public NoSuchScriptException(String msg) {
        super(msg);
    }
}
