package ua.jscript_runner.exception;

/**
 * Signals that compilation failed.
 */
public class FailedCompilationScriptException extends ScriptServiceException {
    private static final String DEFAULT_MSG = "Compilation failed";

    public FailedCompilationScriptException() {
        super(DEFAULT_MSG);
    }

    public FailedCompilationScriptException(String msg) {
        super(msg);
    }
}
