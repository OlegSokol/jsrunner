package ua.jscript_runner.service;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.thread.ScriptExecutor;

import java.util.List;

/**
 * Script service.
 */
public interface ScriptService {

    /**
     * Get all scripts.
     *
     * @return list of scripts.
     */
    List<ScriptExecutor> getAll();

    /**
     * Execute specified script.
     *
     * @param script specified script.
     * @return script executor.
     * @throws ScriptServiceException if compilation failed.
     */
    ScriptExecutor executeScript(Script script) throws ScriptServiceException;

    /**
     * Remove specified script by id.
     *
     * @param scriptId specified id.
     * @throws ScriptServiceException if script not exist by specified id.
     */
    void removeScript(String scriptId) throws ScriptServiceException;

    /**
     * Get script by id.
     *
     * @param scriptId specified id.
     * @return script executor.
     */
    ScriptExecutor getById(String scriptId);
}
