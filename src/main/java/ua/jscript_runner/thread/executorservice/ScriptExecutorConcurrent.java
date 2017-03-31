package ua.jscript_runner.thread.executorservice;

import ua.jscript_runner.Constant;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.thread.ScriptExecutor;

import javax.script.ScriptEngine;
import java.util.concurrent.Callable;

public class ScriptExecutorConcurrent extends ScriptExecutor implements Callable<ScriptExecutor> {
    private Thread currentThread;

    public ScriptExecutorConcurrent(Script script, ScriptEngine engine) {
        this.script = script;
        this.engine = engine;
        engine.getContext().setWriter(pw);
    }

    @Override
    public ScriptExecutor call() throws ScriptServiceException {
        try {
            currentThread = Thread.currentThread();
            script.setStatus(Constant.STATUS_RUNNING);
            Object executionResult = getExecutionResult();
            script.setStatus(Constant.STATUS_FINISH);
            script.setResult(executionResult);
        } catch (Throwable e) {
            script.setStatus(Constant.STATUS_INTERRUPT + ", cause: " + e);
            clear();
            throw new ScriptServiceException(e.toString());
        }
        return this;
    }

    public Thread getThread() {
        return this.currentThread;
    }
}
