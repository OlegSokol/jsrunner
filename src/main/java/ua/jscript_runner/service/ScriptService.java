package ua.jscript_runner.service;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;

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
    List<Script> getAll();

    /**
     * Execute specified script.
     *
     * @param script specified script.
     * @return script executorservice.
     * @throws ScriptServiceException if compilation failed.
     */
    Script executeScript(Script script) throws Exception;

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
     * @return script executorservice.
     */
    Script getById(String scriptId) throws ScriptServiceException;
}
