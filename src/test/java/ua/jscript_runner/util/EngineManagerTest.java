package ua.jscript_runner.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.jscript_runner.Application;

import javax.script.ScriptEngine;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EngineManagerTest {
    @Autowired
    private EngineManager engineManager;

    @Test
    public void shouldGetEngine() throws Exception {
        assertNotNull(engineManager.getEngine());
    }

    @Test
    public void shouldCompileFailed() throws Exception {
        ScriptEngine engine = engineManager.getEngine();
        String wrongScript = "-";
        assertFalse(engineManager.compile(wrongScript, engine));
    }

    @Test
    public void shouldCompileSuccess() {
        ScriptEngine engine = engineManager.getEngine();
        String wrongScript = "print('test')";
        assertTrue(engineManager.compile(wrongScript, engine));
    }
}