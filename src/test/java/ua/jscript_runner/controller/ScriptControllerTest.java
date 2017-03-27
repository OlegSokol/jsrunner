package ua.jscript_runner.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.FailedCompilationScriptException;
import ua.jscript_runner.exception.NoSuchScriptException;
import ua.jscript_runner.service.ScriptService;
import ua.jscript_runner.thread.ScriptExecutor;
import ua.jscript_runner.thread.thread.ScriptExecutorThread;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ScriptController.class)
public class ScriptControllerTest {
    private String testId = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScriptService service;

    @Test
    public void shouldGetAllScripts() throws Exception {
        Script script = new Script();
        script.setScript("print('test')");
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        ScriptExecutor scriptExecutor = new ScriptExecutorThread(engine, script);
        List<ScriptExecutor> scriptExecutors = new ArrayList<>();
        scriptExecutors.add(scriptExecutor);

        given(this.service.getAll()).willReturn(scriptExecutors);
        mockMvc.perform(get("/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldStartExecuteScript() throws Exception {
        Script script = new Script();
        script.setScript("print('test')");
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        ScriptExecutor scriptExecutor = new ScriptExecutorThread(engine, script);
        given(this.service.executeScript(any(Script.class))).willReturn(scriptExecutor);
        mockMvc.perform(post("/execute")
                .content("print('test')"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        given(this.service.executeScript(any(Script.class))).willThrow(FailedCompilationScriptException.class);
        mockMvc.perform(post("/execute")
                .content("print('test')"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldRemoveScriptExecutor() throws Exception {
        Mockito.doNothing().when(service).removeScript(testId);
        mockMvc.perform(delete("/remove/" + testId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestWhenScriptNotExist() throws Exception {
        Mockito.doThrow(NoSuchScriptException.class).when(service).removeScript(testId);
        mockMvc.perform(delete("/remove/" + testId))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetScriptById() throws Exception {
        Script script = new Script();
        script.setScript("print('test')");
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("nashorn");
        ScriptExecutor scriptExecutor = new ScriptExecutorThread(engine, script);
        given(this.service.getById(testId)).willReturn(scriptExecutor);

        mockMvc.perform(get("/script/" + testId))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestWhenScriptNotExistById() throws Exception {
        given(this.service.getById(testId)).willThrow(NoSuchScriptException.class);
        mockMvc.perform(get("/script/" + testId))
                .andExpect(status().isBadRequest());
    }
}