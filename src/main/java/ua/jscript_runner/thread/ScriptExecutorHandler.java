package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;

import java.util.List;

/**
 * Handler that manage script execution.
 */
public interface ScriptExecutorHandler {

    /**
     * Get all script executors.
     *
     * @return list of script executors.
     */
    List<ScriptExecutor> getAllScriptExecutors();

    /**
     * Add and start execute specified script.
     *
     * @param script specified script.
     * @return script executor.
     * @throws ScriptServiceException if compilation failed.
     */
    ScriptExecutor addAndExecuteScript(Script script) throws ScriptServiceException;

    /**
     * Stop execution script by specified id.
     *
     * @param scriptId specified id.
     * @throws ScriptServiceException if script executor not exist.
     */
    void stopExecutorScript(String scriptId) throws ScriptServiceException;

    /**
     * Get script executor by specified id.
     *
     * @param scriptId specified id.
     * @return script executor.
     */
    ScriptExecutor getScriptExecutorById(String scriptId) throws ScriptServiceException;
}
