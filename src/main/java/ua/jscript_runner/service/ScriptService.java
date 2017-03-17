package ua.jscript_runner.service;

import ua.jscript_runner.entity.Script;
import ua.jscript_runner.thread.ScriptExecutor;

import java.util.List;

public interface ScriptService {
    List<ScriptExecutor> getAll();
    void executeSctipr(Script script);
    void removeScript(String scriptId);
    String getStatus(String scriptId);
    String getConsoleOutput(String scriptId);
}
