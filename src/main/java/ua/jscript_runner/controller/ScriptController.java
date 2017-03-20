package ua.jscript_runner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.service.ScriptService;

import java.util.UUID;

@RestController
public class ScriptController {

    @Autowired
    private ScriptService service;

    @GetMapping("/all")
    public ResponseBody getAll() {
        ResponseBody responseBody = new ResponseBody();
        responseBody.setId(UUID.randomUUID().toString());
        responseBody.setContent(service.getAll());
        responseBody.setResponseStatus("OK");
        return responseBody;
    }

    @PostMapping("/execute")
    public ResponseBody executeScript(@RequestBody String script) {
        ResponseBody response = new ResponseBody();
        response.setId(UUID.randomUUID().toString());
        Script jScript = new Script();
        jScript.setId(UUID.randomUUID().toString());
        jScript.setScript(script);
        service.executeScript(jScript);
        response.setResponseStatus("OK");
        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseBody stopAndRemoveScript(@PathVariable String id) {
        ResponseBody responseBody = new ResponseBody();
        service.removeScript(id);
        return responseBody;
    }

    @GetMapping("/script/{id}")
    public ResponseBody getScriptExecutorById(@PathVariable String id) {
        ResponseBody response = new ResponseBody();
        response.setId(UUID.randomUUID().toString());
        response.setContent(service.getById(id));
        return response;
    }

}
