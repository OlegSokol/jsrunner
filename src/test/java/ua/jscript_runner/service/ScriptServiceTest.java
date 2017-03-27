package ua.jscript_runner.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.jscript_runner.Application;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.Constant;
import ua.jscript_runner.exception.ScriptServiceException;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ScriptServiceTest {
    @Autowired
    private ScriptService service;
    private String testUuidForAdd = "550e8400-e29b-41d4-a716-446655440000";
    private String testUuidForRemove = "550e8400-e29b-41d4-a716-446655440001";
    private String testUuidForGetById = "550e8400-e29b-41d4-a716-446655440002";
    private Script testScriptForAdd = new Script();
    private Script testScriptForRemove = new Script();
    private Script testScriptForGetById = new Script();
    private String testScript = "print('test')";

    @Before
    public void before() throws Exception {
        testScriptForAdd.setId(testUuidForAdd);
        testScriptForAdd.setScript(testScript);
        testScriptForRemove.setId(testUuidForRemove);
        testScriptForRemove.setScript(testScript);
        testScriptForGetById.setId(testUuidForGetById);
        testScriptForGetById.setScript(testScript);
        service.executeScript(testScriptForGetById);
        for (int i = 0; i < 8; i++) {
            Script jscript = new Script();
            jscript.setId(UUID.randomUUID().toString());
            jscript.setScript(testScript);
            service.executeScript(jscript);
        }
    }

    @Test
    public void shouldReturnAllScripts () throws Exception {
        assertTrue(service.getAll().size() > 0);
    }

    @Test
    public void shouldExecuteScript() throws Exception {
        service.executeScript(testScriptForAdd);
        Thread.sleep(500);
        assertEquals(Constant.STATUS_FINISH, service.getById(testUuidForAdd).getScript().getStatus());
    }

    @Test(expected = ScriptServiceException.class)
    public void shouldRemoveScript() throws Exception {
        service.executeScript(testScriptForRemove);
        service.removeScript(testUuidForRemove);
        service.getById(testUuidForRemove);
    }

    @Test
    public void shouldReturnScriptById() throws Exception {
        assertNotNull(service.getById(testUuidForGetById));
    }

}