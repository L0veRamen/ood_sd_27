package ca.bytetube._06_ideplugin;

import java.util.List;

public interface Plugin {
    String getName();

    String getVersion();

    PluginType getType();

    void initialize();

    void start();

    void stop();

    List<String> getDependencies();

    void handleEvent(String eventType, String data);

    boolean isRunning();
}
