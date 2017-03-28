package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;

import javax.script.ScriptEngine;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Script executor that contains script and use multithreading to execute it.
 */
public abstract class ScriptExecutor {
    protected Script script;
    protected StringWriter sw = new StringWriter();
    protected PrintWriter pw = new PrintWriter(sw);
    protected ScriptEngine engine;

    public Script getScript() {
        return script;
    }

    /**
     * Clear contents of a PrintWriter after writing.
     * For example after OutOfMemoryError.
     */
    protected void clear() {
        pw.flush();
        sw.getBuffer().setLength(0);
    }
}
