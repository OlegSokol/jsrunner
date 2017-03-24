package ua.jscript_runner.thread;

import ua.jscript_runner.entity.Script;

/**
 * Script executor that contains script and use multithreading to execute it.
 */
public interface ScriptExecutor {
    Script getScript();
}
