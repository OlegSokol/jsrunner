package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ScriptExecutorHandler {
    private Map<String, Map<ScriptExecutorThread, Thread>> threads = new ConcurrentHashMap<>();

    public List<ScriptExecutorThread> getAllScriptExecutors() {
        List<ScriptExecutorThread> scripts = new ArrayList<>();
        Collection<Map<ScriptExecutorThread, Thread>> values = threads.values();
        for (Map<ScriptExecutorThread, Thread> map : values) {
            scripts.addAll(map.keySet());
        }
        return Collections.unmodifiableList(scripts);
    }

    public void addAndExecuteScript(Script script) {
        if (compile(script.getScript())) {
            ScriptExecutorThread scriptExecutor = new ScriptExecutorThread(getEngine(), script);
            Thread thread = new Thread(scriptExecutor);
            threads.put(script.getId(), Collections.singletonMap(scriptExecutor, thread));
            thread.start();
        }
    }

    public void stopExecutorScript(String scriptId) {
        Map<ScriptExecutorThread, Thread> scriptExecutor = threads.get(scriptId);
        for (Thread thread : scriptExecutor.values()) {
            stopThread(thread);
        }
        threads.remove(scriptId);
    }

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
