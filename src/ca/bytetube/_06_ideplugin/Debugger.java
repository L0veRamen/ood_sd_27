package ca.bytetube._06_ideplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Debugger implements Plugin {
    private String name = "Debugger";
    private String version = "1.0.0";
    private boolean running = false;
    private boolean isAttached = false;
    private List<String> breakpoints = new ArrayList();
    private String currentLanguage;
    private Map<String, Object> configuration = new HashMap();

    public Debugger() {
        this.configuration.put("enabled", true);
        this.configuration.put("autoAttach", false);
        this.configuration.put("breakOnException", true);
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public PluginType getType() {
        return PluginType.DEBUGGER;
    }

    public void initialize() {
        System.out.println("Initializing Debugger...");
    }

    public void start() {
        this.running = true;
        System.out.println("Debugger started");
    }

    public void stop() {
        this.running = false;
        this.isAttached = false;
        System.out.println("Debugger stopped");
    }

    public List<String> getDependencies() {
        ArrayList var1 = new ArrayList();
        var1.add("Logger");
        return var1;
    }

    public void handleEvent(String var1, String var2) {
        if (this.running) {
            switch (var1) {
                case "DEBUG_START" -> this.startDebugging(var2, "java");
                case "DEBUG_STOP" -> this.stopDebugging();
            }

        }
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean startDebugging(String var1, String var2) {
        if (this.isAttached) {
            System.out.println("Debugger already attached");
            return false;
        } else {
            System.out.println("Starting debug session for: " + var1 + " (language: " + var2 + ")");
            this.currentLanguage = var2;
            this.isAttached = true;
            System.out.println("Debug session started successfully");
            return true;
        }
    }

    public void stopDebugging() {
        if (!this.isAttached) {
            System.out.println("No active debug session");
        } else {
            System.out.println("Stopping debug session...");
            this.isAttached = false;
            this.currentLanguage = null;
        }
    }

    public void addBreakpoint(String var1, int var2) {
        String var3 = var1 + ":" + var2;
        this.breakpoints.add(var3);
        System.out.println("Breakpoint added at " + var3);
    }

    public void removeBreakpoint(String var1, int var2) {
        String var3 = var1 + ":" + var2;
        this.breakpoints.remove(var3);
        System.out.println("Breakpoint removed at " + var3);
    }

    public List<String> getBreakpoints() {
        return new ArrayList(this.breakpoints);
    }

    public void continueExecution() {
        if (!this.isAttached) {
            System.out.println("No active debug session");
        } else {
            System.out.println("Continuing execution...");
        }
    }

    public void stepOver() {
        if (!this.isAttached) {
            System.out.println("No active debug session");
        } else {
            System.out.println("Stepping over...");
        }
    }

    public void stepInto() {
        if (!this.isAttached) {
            System.out.println("No active debug session");
        } else {
            System.out.println("Stepping into...");
        }
    }

    public boolean isAttached() {
        return this.isAttached;
    }

    public void setConfiguration(String var1, Object var2) {
        this.configuration.put(var1, var2);
        switch (var1) {
            case "enabled" -> System.out.println("Debugger: " + ((Boolean)var2 ? "enabled" : "disabled"));
            case "autoAttach" -> System.out.println("Auto attach: " + ((Boolean)var2 ? "enabled" : "disabled"));
            case "breakOnException" -> System.out.println("Break on exception: " + ((Boolean)var2 ? "enabled" : "disabled"));
        }

    }

}

