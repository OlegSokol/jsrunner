package ua.jscript_runner.service;

import org.springframework.stereotype.Service;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.thread.ScriptExecutor;
import ua.jscript_runner.thread.ScriptExecutorHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {

    @Resource(name = "${handler}")
    private ScriptExecutorHandler executorHandler;

    @Override
    public List<Script> getAll() {
        List<Script> scripts = new ArrayList<>();
        List<ScriptExecutor> executors = executorHandler.getAllScriptExecutors();
        for (ScriptExecutor executor : executors) {
            scripts.add(executor.getScript());
        }
        return scripts;
    }

    @Override
    public Script executeScript(Script script) throws Exception {
        ScriptExecutor scriptExecutor = executorHandler.addAndExecuteScript(script);
        return scriptExecutor.getScript();
    }

    @Override
    public void removeScript(String scriptId) throws ScriptServiceException {
        executorHandler.stopExecutorScript(scriptId);
    }

    @Override
    public Script getById(String scriptId) throws ScriptServiceException {
        ScriptExecutor scriptExecutor = executorHandler.getScriptExecutorById(scriptId);
        return scriptExecutor.getScript();
    }
}
