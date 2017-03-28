package ua.jscript_runner.thread.thread;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.Constant;
import ua.jscript_runner.thread.ScriptExecutor;

import javax.script.ScriptEngine;

public class ScriptExecutorThread extends ScriptExecutor implements Runnable {

    public ScriptExecutorThread(ScriptEngine engine, Script script) {
        super.engine = engine;
        this.script = script;
    }

    @Override
    public void run() {
        try {
            engine.getContext().setWriter(pw);
            script.setStatus(Constant.STATUS_RUNNING);
            Object result = getExecutionResult();
            script.setResult(result);
            script.setStatus(Constant.STATUS_FINISH);
        } catch (Throwable e) {
            script.setStatus(Constant.STATUS_INTERRUPT + ", cause: " + e);
            clear();
        }
    }
}
