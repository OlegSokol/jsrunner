package ua.jscript_runner.thread.thread;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.Constant;
import ua.jscript_runner.thread.ScriptExecutor;

import javax.script.ScriptEngine;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ScriptExecutorThread implements ScriptExecutor, Runnable {
    private Script script;
    private StringWriter sw = new StringWriter();
    private PrintWriter pw = new PrintWriter(sw);
    private ScriptEngine engine;

    public ScriptExecutorThread(ScriptEngine engine, Script script) {
        this.engine = engine;
        this.script = script;
    }

    public Script getScript() {
        return script;
    }

    @Override
    public void run() {
        try {
            engine.getContext().setWriter(pw);
            script.setStatus(Constant.STATUS_RUNNING);
            engine.eval(script.getScript());
            script.setStatus(Constant.STATUS_FINISH);
            script.setConsoleOutput(sw.getBuffer().toString());
        } catch (Throwable e) {
            script.setStatus(Constant.STATUS_INTERRUPT + ", cause: " + e);
            clear();
        }
    }

    /**
     * Clear contents of a PrintWriter after writing.
     * For example after OutOfMemoryError.
     */
    private void clear() {
        pw.flush();
        sw.getBuffer().setLength(0);
    }
}
