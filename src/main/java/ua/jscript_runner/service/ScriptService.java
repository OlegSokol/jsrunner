package ua.jscript_runner.service;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.thread.ScriptExecutor;

import java.util.List;

public interface ScriptService {
    List<ScriptExecutor> getAll();
    ScriptExecutor executeScript(Script script) throws ScriptServiceException;
    void removeScript(String scriptId) throws ScriptServiceException;
    ScriptExecutor getById(String scriptId);
}
