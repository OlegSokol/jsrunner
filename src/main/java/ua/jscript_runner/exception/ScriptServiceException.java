package ua.jscript_runner.exception;

/**
 * Signals that an attempt to use the script service has failed.
 */
public class ScriptServiceException extends Exception {
    private static final String DEFAULT_MSG = "Attempt to use script service was failed";

    public ScriptServiceException() {
        super(DEFAULT_MSG);
    }

    public ScriptServiceException(String msg) {
        super(msg);
    }

    public ScriptServiceException(Throwable throwable) {
        super(throwable);
    }
}
