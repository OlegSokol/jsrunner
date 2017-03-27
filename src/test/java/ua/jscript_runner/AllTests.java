package ua.jscript_runner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import ua.jscript_runner.controller.ScriptControllerTest;
import ua.jscript_runner.service.ScriptServiceTest;
import ua.jscript_runner.util.EngineManagerTest;

@RunWith(Suite.class)
@SpringBootTest(classes = Application.class)
@Suite.SuiteClasses({ ScriptControllerTest.class, ScriptServiceTest.class, EngineManagerTest.class} )
public class AllTests {
    @Test
    public void mockTest() {
    }
}
