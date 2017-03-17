package ua.jscript_runner.entity;

public class Script {
    private String id;
    private String script;
    private String status;
    private String consoleOutput;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(String consoleOutput) {
        this.consoleOutput = consoleOutput;
    }
}
