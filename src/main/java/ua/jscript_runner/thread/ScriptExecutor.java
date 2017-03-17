package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ScriptExecutor implements Runnable {
    private Script script;

    public ScriptExecutor(Script script) {
        this.script = script;
    }

    public Script getScript() {
        return script;
    }

    @Override
    public void run() {
        try {
            script.setStatus("run");
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("nashorn");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            engine.getContext().setWriter(pw);
            engine.eval(script.getScript());
            script.setConsoleOutput(sw.getBuffer().toString());
            script.setStatus("done");
        } catch (Throwable e) {
            script.setStatus(e.getMessage());
        }
    }
}
