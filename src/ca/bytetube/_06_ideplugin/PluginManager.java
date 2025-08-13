package ca.bytetube._06_ideplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginManager {

    private Map<String, Plugin> plugins;
    //<"FILE_SAVED",[Logger,CodeFormatter]>
    private Map<String, List<Plugin>> eventSubscribers;
    
    public PluginManager() {
        this.plugins = new HashMap<>();
        this.eventSubscribers = new HashMap<>();
    }

    public boolean registerPlugin(Plugin plugin) {

        String key = plugin.getName() + ":" + plugin.getVersion();

        if (plugins.containsKey(key)) {
            System.out.println("Plugin " + key + " already registered");
            return false;
        }
        if (!checkDependencies(plugin)) {
            System.out.println("Dependencies not satisfied for " + plugin.getName());
            return false;
        }

        plugins.put(key, plugin);
        System.out.println("Plugin " + key + " registered successfully");
        subscribeToEvents(plugin);
        return true;
    }

    public boolean unregisterPlugin(String name, String version) {

        String key = name + ":" + version;
        
        if (!plugins.containsKey(key)) {
            System.out.println("Plugin " + key + " not found");
            return false;
        }

        if (hasDependents(name)) {
            System.out.println("Cannot unregister " + key + " - other plugins depend on it");
            return false;
        }

        Plugin plugin = plugins.get(key);
        plugin.stop();
        plugins.remove(key);

        unsubscribeFromEvents(plugin);
        
        System.out.println("Plugin " + key + " unregistered successfully");
        return true;
    }

    public void startAllPlugins() {
        for (Plugin plugin : plugins.values()) {
            try {
                plugin.initialize();
                plugin.start();
                System.out.println("Plugin " + plugin.getName() + " started");
            } catch (Exception e) {
                System.err.println("Failed to start " + plugin.getName() + ": " + e.getMessage());
            }
        }
    }

    public void stopAllPlugins() {
        for (Plugin plugin : plugins.values()) {
            try {
                plugin.stop();
                System.out.println("Plugin " + plugin.getName() + " stopped");
            } catch (Exception e) {
                System.err.println("Failed to stop " + plugin.getName() + ": " + e.getMessage());
            }
        }
    }

    public List<Plugin> getAllPlugins() {
        return new ArrayList<>(plugins.values());
    }

    public List<Plugin> getPluginsByType(PluginType type) {
        List<Plugin> result = new ArrayList<>();
        for (Plugin plugin : plugins.values()) {
            if (plugin.getType() == type) {
                result.add(plugin);
            }
        }
        return result;
    }

    public void publishEvent(String eventType, String data) {

        List<Plugin> subscribers = eventSubscribers.get(eventType);
        if (subscribers != null) {
            for (Plugin plugin : subscribers) {
                try {
                    plugin.handleEvent(eventType, data);
                } catch (Exception e) {
                    System.err.println("Error handling event in " + plugin.getName() + ": " + e.getMessage());
                }
            }
        }

        List<Plugin> allSubscribers = eventSubscribers.get("ALL");
        if (allSubscribers != null) {
            for (Plugin plugin : allSubscribers) {
                try {
                    plugin.handleEvent(eventType, data);
                } catch (Exception e) {
                    System.err.println("Error handling event in " + plugin.getName() + ": " + e.getMessage());
                }
            }
        }
        
        System.out.println("Event published: " + eventType);
    }

    public void subscribe(Plugin plugin, String... eventTypes) {
        for (String eventType : eventTypes) {
            eventSubscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(plugin);
        }
    }
    

    private boolean checkDependencies(Plugin plugin) {
        List<String> dependencies = plugin.getDependencies();
        for (String dep : dependencies) {
            boolean found = false;

            for (Plugin p : plugins.values()) {
                if (p.getName().equals(dep)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDependents(String pluginName) {
        for (Plugin plugin : plugins.values()) {
            if (plugin.getDependencies().contains(pluginName)) {
                return true;
            }
        }
        return false;
    }


    private void subscribeToEvents(Plugin plugin) {
        switch (plugin.getType()) {
            case LOGGER:
                subscribe(plugin, "ALL");
                break;
            case SYNTAX_HIGHLIGHTER:
                subscribe(plugin, "FILE_OPENED", "FILE_SAVED");
                break;
            case CODE_FORMATTER:
                subscribe(plugin, "FILE_SAVED");
                break;
            case DEBUGGER:
                subscribe(plugin, "DEBUG_START", "DEBUG_STOP");
                break;
        }
    }
    
    private void unsubscribeFromEvents(Plugin plugin) {
        for (List<Plugin> subscribers : eventSubscribers.values()) {
            subscribers.remove(plugin);
        }
    }
}
