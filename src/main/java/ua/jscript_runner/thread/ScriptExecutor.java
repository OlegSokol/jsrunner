package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ScriptExecutor implements Runnable {
    private Script script;
    private StringWriter sw = new StringWriter();
    private PrintWriter pw = new PrintWriter(sw);

    public ScriptExecutor(Script script) {
        this.script = script;
    }

    public Script getScript() {
        return script;
    }

    @Override
    public void run() {
        try {
            script.setStatus(Constant.STATUS_RUNNING);
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName(Constant.ENGINE);
            engine.getContext().setWriter(pw);
            engine.eval(script.getScript());
            script.setConsoleOutput(sw.getBuffer().toString());
            script.setStatus(Constant.STATUS_FINISH);
        } catch (Throwable e) {
            script.setStatus(Constant.STATUS_INTERRUPT);
        }
    }
}
