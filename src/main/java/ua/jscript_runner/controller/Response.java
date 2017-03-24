package ua.jscript_runner.controller;

import org.springframework.hateoas.ResourceSupport;

public class Response extends ResourceSupport {
    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
