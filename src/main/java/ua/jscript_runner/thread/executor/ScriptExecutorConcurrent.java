package ua.jscript_runner.thread.executor;

import ua.jscript_runner.Constant;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.thread.ScriptExecutor;

import javax.script.ScriptEngine;
import java.util.concurrent.Callable;

public class ScriptExecutorConcurrent extends ScriptExecutor implements Callable<ScriptExecutor> {

    public ScriptExecutorConcurrent(Script script, ScriptEngine engine) {
        this.script = script;
        this.engine = engine;
    }

    @Override
    public ScriptExecutor call() throws ScriptServiceException {
        try {
            engine.getContext().setWriter(pw);
            script.setStatus(Constant.STATUS_RUNNING);
            engine.eval(script.getScript());
            script.setStatus(Constant.STATUS_FINISH);
            script.setConsoleOutput(sw.getBuffer().toString());
        } catch (Throwable e) {
            script.setStatus(Constant.STATUS_INTERRUPT + ", cause: " + e);
            clear();
            throw new ScriptServiceException(e);
        }
        return this;
    }

    public Thread getThread() {
        return Thread.currentThread();
    }
}