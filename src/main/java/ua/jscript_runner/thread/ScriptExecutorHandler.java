package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;

import java.util.List;

public interface ScriptExecutorHandler {
    List<ScriptExecutor> getAllScriptExecutors();
    ScriptExecutor addAndExecuteScript(Script script) throws ScriptServiceException;
    void stopExecutorScript(String scriptId) throws ScriptServiceException;
    ScriptExecutor getScriptExecutorById(String scriptId);
}
