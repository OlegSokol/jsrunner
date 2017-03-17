package ua.jscript_runner.controller;

public class ResponseBody {
    private String id;
    private String responseStatus;
    private Object content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
