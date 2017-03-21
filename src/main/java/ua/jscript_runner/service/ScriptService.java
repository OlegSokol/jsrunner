package ua.jscript_runner.service;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.thread.ScriptExecutorThread;

import java.util.List;

public interface ScriptService {
    List<ScriptExecutorThread> getAll();
    void executeScript(Script script);
    void removeScript(String scriptId);
    ScriptExecutorThread getById(String scriptId);
}
