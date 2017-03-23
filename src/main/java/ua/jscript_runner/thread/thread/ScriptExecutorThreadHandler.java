package ua.jscript_runner.thread.thread;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.FailedCompilationScriptException;
import ua.jscript_runner.exception.NoSuchScriptException;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.thread.ScriptExecutor;
import ua.jscript_runner.thread.ScriptExecutorHandler;
import ua.jscript_runner.util.EngineManager;

import javax.script.ScriptEngine;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component("threadHandler")
public class ScriptExecutorThreadHandler implements ScriptExecutorHandler {
    private static final Logger LOG = Logger.getLogger(ScriptExecutorThreadHandler.class);

    @Autowired
    private EngineManager engineManager;
    private Map<String, Map<ScriptExecutorThread, Thread>> threads = new ConcurrentHashMap<>();

    @Override
    public List<ScriptExecutor> getAllScriptExecutors() {
        List<ScriptExecutorThread> scripts = new ArrayList<>();
        Collection<Map<ScriptExecutorThread, Thread>> values = threads.values();
        for (Map<ScriptExecutorThread, Thread> map : values) {
            scripts.addAll(map.keySet());
        }
        LOG.debug("Amount of threads: " + scripts.size() + ", was returned");
        return Collections.unmodifiableList(scripts);
    }

    @Override
    public void addAndExecuteScript(Script script) throws ScriptServiceException {
        ScriptEngine engine = engineManager.getEngine();
        if (!engineManager.compile(script.getScript(), engine)) {
            throw new FailedCompilationScriptException();
        }
        ScriptExecutorThread scriptExecutor = new ScriptExecutorThread(engine, script);
        Thread thread = new Thread(scriptExecutor);
        threads.put(script.getId(), Collections.singletonMap(scriptExecutor, thread));
        thread.start();
        LOG.debug("New thread" + thread.getName() + "start executing");
    }

    @Override
    public void stopExecutorScript(String scriptId) throws ScriptServiceException {
        Map<ScriptExecutorThread, Thread> scriptExecutor = threads.get(scriptId);
        if (scriptExecutor == null) {
            throw new NoSuchScriptException();
        }
        for (Thread thread : scriptExecutor.values()) {
            stopThread(thread);
        }
        threads.remove(scriptId);
        LOG.debug("ScriptExecutor with id: '" + scriptId + "' was removed from handler");
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

    private void stopThread(Thread thread) {
        LOG.debug("Start killing thread " + thread.getName());
        while (thread.isAlive()) {
            thread.stop();
        }
        LOG.debug("Thread " + thread.getName() + " was killed");
    }
}
