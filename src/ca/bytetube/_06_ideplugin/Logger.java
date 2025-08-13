package ca.bytetube._06_ideplugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Logger implements Plugin {
    private String name = "Logger";
    private String version = "1.0.0";
    private boolean running = false;
    private List<String> logEntries;
    //DEBUG < INFO < WARN < ERROR
    private String logLevel = "INFO";


    public Logger() {
        this.logEntries = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public PluginType getType() {
        return PluginType.LOGGER;
    }

    public void initialize() {
        System.out.println("Initializing Logger...");
    }

    public void start() {
        this.running = true;
        System.out.println("Logger started");
        this.log("INFO", "Logger plugin started", "SYSTEM");
    }

    public void stop() {
        this.running = false;
        System.out.println("Logger stopped");
        this.log("INFO", "Logger plugin stopped", "SYSTEM");
    }

    public List<String> getDependencies() {
        return new ArrayList();
    }

    @Override
    public void handleEvent(String eventType, String data) {
        if (!running) return;

        log("DEBUG", "Event: " + eventType + " - " + data, "SYSTEM");

        switch (eventType) {
            case "PLUGIN_REGISTERED":
                log("INFO", "Plugin registered: " + data, "SYSTEM");
                break;
            case "PLUGIN_UNREGISTERED":
                log("INFO", "Plugin unregistered: " + data, "SYSTEM");
                break;
            case "ERROR_OCCURRED":
                log("ERROR", "Error occurred: " + data, "SYSTEM");
                break;
        }
    }

    public boolean isRunning() {
        return this.running;
    }

    public void log(String level, String message, String source) {

        if (!shouldLog(level)) {
            return;
        }
        String timestamp = new Date().toString();
        String logEntry = "[" + timestamp + "] " + level + " [" + source + "]: " + message;
        logEntries.add(logEntry);
        System.out.println(logEntry);
        if (logEntries.size() > 100) {
            logEntries.remove(0);
        }
    }

    public void debug(String message, String source) {
        log("DEBUG", message, source);
    }

    public void info(String message, String source) {
        log("INFO", message, source);
    }

    public void warn(String message, String source) {
        log("WARN", message, source);
    }

    public void error(String message, String source) {
        log("ERROR", message, source);
    }


    private boolean shouldLog(String level) {
        Map logMap = Map.of("DEBUG", 0, "INFO", 1, "WARN", 2, "ERROR", 3);
        return (Integer) logMap.getOrDefault(level, 0) >= (Integer) logMap.getOrDefault(this.logLevel, 1);
    }


}
