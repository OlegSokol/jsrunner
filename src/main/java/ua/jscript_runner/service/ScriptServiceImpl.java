package ua.jscript_runner.service;

import org.springframework.stereotype.Service;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
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
    public ScriptExecutor executeScript(Script script) throws Exception {
        return executorHandler.addAndExecuteScript(script);
    }

    @Override
    public void removeScript(String scriptId) throws ScriptServiceException {
        executorHandler.stopExecutorScript(scriptId);
    }

    @Override
    public ScriptExecutor getById(String scriptId) throws ScriptServiceException {
        return executorHandler.getScriptExecutorById(scriptId);
    }
}
