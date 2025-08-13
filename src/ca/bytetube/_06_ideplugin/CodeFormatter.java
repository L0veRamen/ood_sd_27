package ca.bytetube._06_ideplugin;

import java.util.ArrayList;
import java.util.List;


public class CodeFormatter implements Plugin {

    private String name = "CodeFormatter";
    private String version = "1.0.0";
    private boolean running = false;
    private boolean autoFormatOnSave = true;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public PluginType getType() {
        return PluginType.CODE_FORMATTER;
    }

    @Override
    public void initialize() {
        System.out.println("Initializing Code Formatter...");
    }

    @Override
    public void start() {
        running = true;
        System.out.println("Code Formatter started");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("Code Formatter stopped");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public List<String> getDependencies() {
        List<String> deps = new ArrayList<>();
        deps.add("SyntaxHighlighter");
        return deps;
    }

    @Override
    public void handleEvent(String eventType, String data) {
        if (!running) return;
        switch (eventType) {
            case "FILE_SAVED":
                if (autoFormatOnSave) {
                    handleFileSaved(data);
                }
                break;
        }

    }


    private void handleFileSaved(String fileName) {
        System.out.println("Code Formatter: Auto-formatting file - " + fileName);
        formatCode(fileName, "java");
    }


    public String formatCode(String code, String language) {
        System.out.println("Formatting " + language + " code...");

        String formatted = code;
        formatted = formatted.replaceAll(";", ";\n");
        formatted = formatted.replaceAll("\\{", " {\n");
        formatted = formatted.replaceAll("\\}", "}\n");
        formatted = formatted.replaceAll("=", " = ");
        formatted = formatted.replaceAll("\\+", " + ");

        return formatted;
    }


}
