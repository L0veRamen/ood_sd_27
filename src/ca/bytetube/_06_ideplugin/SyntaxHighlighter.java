package ca.bytetube._06_ideplugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SyntaxHighlighter implements Plugin {

    private String name = "SyntaxHighlighter";
    private String version = "1.0.0";
    private boolean running = false;
    private Set<String> supportedLanguages;


    public SyntaxHighlighter() {
        this.supportedLanguages = new HashSet<>();
        supportedLanguages.add("java");
        supportedLanguages.add("python");
        supportedLanguages.add("javascript");
    }

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
        return PluginType.SYNTAX_HIGHLIGHTER;
    }

    @Override
    public void initialize() {
        System.out.println("Initializing Syntax Highlighter...");
    }

    @Override
    public void start() {
        running = true;
        System.out.println("Syntax Highlighter started");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("Syntax Highlighter stopped");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public List<String> getDependencies() {
        return new ArrayList<>();
    }

    @Override
    public void handleEvent(String eventType, String data) {
        if (!running) return;

        switch (eventType) {
            case "FILE_OPENED":
                handleFileOpened(data);
                break;
            case "FILE_SAVED":
                handleFileSaved(data);
                break;
        }
    }

    private void handleFileOpened(String fileName) {
        System.out.println("Syntax Highlighter: File opened - " + fileName);
        highlightSyntax(fileName, "java");
    }

    private void handleFileSaved(String fileName) {
        System.out.println("Syntax Highlighter: File saved - " + fileName);
    }


    public String highlightSyntax(String code, String language) {
        if (!isLanguageSupported(language)) {
            return code;
        }
        System.out.println("Highlighting " + language + " code...");
        String highlighted = code;

        String[] keywords = {"public", "private", "class", "static", "void", "int", "String"};
        for (String keyword : keywords) {

            highlighted = highlighted.replaceAll("\\b" + keyword + "\\b",
                    "<span class='keyword'>" + keyword + "</span>");
        }
        highlighted = highlighted.replaceAll("//.*", "<span class='comment'>$0</span>");

        return highlighted;
    }

    public boolean isLanguageSupported(String language) {
        return supportedLanguages.contains(language);
    }

    public List<String> getSupportedLanguages() {
        return new ArrayList<>(supportedLanguages);
    }

    public void addLanguageSupport(String language, List<String> keywords, String commentPattern) {
        supportedLanguages.add(language);
        System.out.println("Added language support for: " + language);
    }
}