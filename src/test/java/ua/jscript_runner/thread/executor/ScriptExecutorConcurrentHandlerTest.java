package ua.jscript_runner.thread.executor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.jscript_runner.Application;
import ua.jscript_runner.Constant;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.thread.ScriptExecutor;
import ua.jscript_runner.thread.ScriptExecutorHandler;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ScriptExecutorConcurrentHandlerTest {

    @Resource(name = "executorservice")
    private ScriptExecutorHandler scriptExecutorHandler;

    @Test
    public void shouldAddAndExecuteScript() throws Exception {
        Script script = new Script();
        script.setId("1");
        script.setScript("print('test')");
        ScriptExecutor scriptExecutor = scriptExecutorHandler.addAndExecuteScript(script);
        assertThat("status should be 'FINISH'", Constant.STATUS_FINISH, is(scriptExecutor.getScript().getStatus()));
    }
}