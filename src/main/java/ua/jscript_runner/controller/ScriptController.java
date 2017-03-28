package ua.jscript_runner.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.jscript_runner.Constant;
import ua.jscript_runner.entity.Script;
import ua.jscript_runner.exception.ScriptServiceException;
import ua.jscript_runner.service.ScriptService;

import java.util.*;

@RestController
public class ScriptController {

    @Autowired
    private ScriptService service;

    @GetMapping("/all")
    public ResponseEntity<List<Response>> all() throws ScriptServiceException {
        List<Response> scriptResponses = new ArrayList<>();
        List<Script> scripts = service.getAll();
        for (Script script : scripts) {
            Response responseBody = new Response();
            responseBody.setContent(script);
            scriptResponses.add(responseBody);
            setLinks(responseBody, script);
        }
        return new ResponseEntity<>(scriptResponses, HttpStatus.OK);
    }

    @PostMapping("/execute")
    public ResponseEntity<Response> executeScript(@RequestBody String script) throws Exception {
        Response responseBody = new Response();
        Script jScript = new Script();
        jScript.setId(UUID.randomUUID().toString());
        jScript.setScript(script);
        Script scriptObj = service.executeScript(jScript);
        responseBody.setContent(scriptObj);
        setLinks(responseBody, scriptObj);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity stopAndRemoveScript(@PathVariable String id) throws ScriptServiceException {
        service.removeScript(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/script/{id}")
    public ResponseEntity<Response> getScriptExecutorById(@PathVariable String id) throws ScriptServiceException {
        Response responseBody = new Response();
        Script script = service.getById(id);
        responseBody.setContent(script);
        setLinks(responseBody, script);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> exceptionHandler(Exception e) {
        Map<String,String> responseBody = new HashMap<>();
        if (e instanceof ScriptServiceException) {
            responseBody.put("code", Constant.CUSTOMER_SIDE);
        } else {
            responseBody.put("code", Constant.SERVER_SIDE);
        }
        responseBody.put("message", e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    private void setLinks(Response response, Script script) throws ScriptServiceException {
        response.add(linkTo(methodOn(ScriptController.class).getScriptExecutorById(script.getId())).withRel("getScriptById"));
        response.add(linkTo(methodOn(ScriptController.class).stopAndRemoveScript(script.getId())).withRel("stopAndRemoveScriptById"));
    }
}
