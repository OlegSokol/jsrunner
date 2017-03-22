package ua.jscript_runner.thread.thread;

import org.springframework.stereotype.Component;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.thread.Constant;
import ua.jscript_runner.thread.ScriptExecutor;
import ua.jscript_runner.thread.ScriptExecutorHandler;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component("threadHandler")
public class ScriptExecutorThreadHandler implements ScriptExecutorHandler {
    private Map<String, Map<ScriptExecutorThread, Thread>> threads = new ConcurrentHashMap<>();

    @Override
    public List<ScriptExecutor> getAllScriptExecutors() {
        List<ScriptExecutorThread> scripts = new ArrayList<>();
        Collection<Map<ScriptExecutorThread, Thread>> values = threads.values();
        for (Map<ScriptExecutorThread, Thread> map : values) {
            scripts.addAll(map.keySet());
        }
        return Collections.unmodifiableList(scripts);
    }

    @Override
    public void addAndExecuteScript(Script script) {
        if (compile(script.getScript())) {
            ScriptExecutorThread scriptExecutor = new ScriptExecutorThread(getEngine(), script);
            Thread thread = new Thread(scriptExecutor);
            threads.put(script.getId(), Collections.singletonMap(scriptExecutor, thread));
            thread.start();
        }
    }

    @Override
    public void stopExecutorScript(String scriptId) {
        Map<ScriptExecutorThread, Thread> scriptExecutor = threads.get(scriptId);
        for (Thread thread : scriptExecutor.values()) {
            stopThread(thread);
        }
        threads.remove(scriptId);
    }

    @Override
    public ScriptExecutorThread getScriptExecutorById(String scriptId) {
        ScriptExecutorThread scriptExecutor = null;
        Map<ScriptExecutorThread, Thread> scriptExecutorThreadMap = threads.get(scriptId);
        if (scriptExecutorThreadMap != null) {
            for (ScriptExecutorThread executor : scriptExecutorThreadMap.keySet()) {
                scriptExecutor = executor;
            }
        }
        return scriptExecutor;
    }

    private boolean compile(String script) {
        try {
            ((Compilable)getEngine()).compile(script);
            return true;
        } catch (ScriptException e) {
            return false;
        }
    }

    private void stopThread(Thread thread) {
        while (thread.isAlive()) {
            thread.stop();
        }
    }

    private ScriptEngine getEngine() {
        ScriptEngineManager factory = new ScriptEngineManager();
        return factory.getEngineByName(Constant.ENGINE);
    }
}
