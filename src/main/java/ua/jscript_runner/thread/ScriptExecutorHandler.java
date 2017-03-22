package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;

import java.util.List;

public interface ScriptExecutorHandler {
    List<ScriptExecutor> getAllScriptExecutors();
    void addAndExecuteScript(Script script);
    void stopExecutorScript(String scriptId);
    ScriptExecutor getScriptExecutorById(String scriptId);
}
