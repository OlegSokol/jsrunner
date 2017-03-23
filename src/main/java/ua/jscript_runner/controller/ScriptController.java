package ua.jscript_runner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.service.ScriptService;
import ua.jscript_runner.thread.ScriptExecutor;

import java.util.List;
import java.util.UUID;

@RestController
public class ScriptController {

    @Autowired
    private ScriptService service;

    @GetMapping("/all")
    public ResponseEntity<List<ScriptExecutor>> all() {
        List<ScriptExecutor> scriptExecutors = service.getAll();
        return ResponseEntity.accepted().body(scriptExecutors);
    }

    @PostMapping("/execute")
    public ResponseEntity executeScript(@RequestBody String script) {
        Script jScript = new Script();
        jScript.setId(UUID.randomUUID().toString());
        jScript.setScript(script);
        try {
            service.executeScript(jScript);
        } catch (ScriptServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity stopAndRemoveScript(@PathVariable String id) throws ScriptServiceException {
        service.removeScript(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/script/{id}")
    public ResponseEntity<ScriptExecutor> getScriptExecutorById(@PathVariable String id) {
        ScriptExecutor scriptExecutor = service.getById(id);
        return ResponseEntity.accepted().body(scriptExecutor);
    }

    @ExceptionHandler(ScriptServiceException.class)
    public ResponseEntity<?> exceptionHandler(ScriptServiceException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}
