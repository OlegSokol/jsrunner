package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ScriptExecutorHandler {
    private Map<String, Map<ScriptExecutor, Thread>> threads = new ConcurrentHashMap<>();

    public List<ScriptExecutor> getAllScriptExecutors() {
        List<ScriptExecutor> scripts = new ArrayList<>();
        Collection<Map<ScriptExecutor, Thread>> values = threads.values();
        for (Map<ScriptExecutor, Thread> map : values) {
            scripts.addAll(map.keySet());
        }
        return Collections.unmodifiableList(scripts);
    }

    public void addAndExecuteScript(Script script) {
        ScriptExecutor scriptExecutor = new ScriptExecutor(script);
        Thread thread = new Thread(scriptExecutor);
        threads.put(script.getId(), Collections.singletonMap(scriptExecutor, thread));
        thread.start();
    }

    public void stopExecutorScript(String scriptId) {
        Map<ScriptExecutor, Thread> scriptExecutor = threads.get(scriptId);
        for (Thread thread : scriptExecutor.values()) {
            stopThread(thread);
        }
        threads.remove(scriptId);
    }

    public ScriptExecutor getScriptExecutorById(String scriptId) {
        ScriptExecutor scriptExecutor = null;
        Map<ScriptExecutor, Thread> scriptExecutorThreadMap = threads.get(scriptId);
        if (scriptExecutorThreadMap != null) {
            for (ScriptExecutor executor : scriptExecutorThreadMap.keySet()) {
                scriptExecutor = executor;
            }
        }
        return scriptExecutor;
    }

    private void stopThread(Thread thread) {
        while (thread.isAlive()) {
            thread.stop();
        }
    }
}
