package ua.jscript_runner.service;

import org.springframework.stereotype.Service;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.thread.ScriptExecutor;
import ua.jscript_runner.thread.ScriptExecutorHandler;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    @Resource(name = "${handler}")
    private ScriptExecutorHandler executorHandler;

    @Override
    public List<ScriptExecutor> getAll() {
        return executorHandler.getAllScriptExecutors();
    }

    @Override
    public void executeScript(Script script) {
        executorHandler.addAndExecuteScript(script);
    }

    @Override
    public void removeScript(String scriptId) {
        executorHandler.stopExecutorScript(scriptId);
    }

    @Override
    public ScriptExecutor getById(String scriptId) {
        return executorHandler.getScriptExecutorById(scriptId);
    }
}
