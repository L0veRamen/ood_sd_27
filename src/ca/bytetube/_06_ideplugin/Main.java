package ca.bytetube._06_ideplugin;

import java.io.PrintStream;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        System.out.println("=== Simple IDE Plugin System Demo ===\n");
        PluginManager pluginManager = new PluginManager();
        Logger logger = new Logger();
        SyntaxHighlighter syntaxHighlighter = new SyntaxHighlighter();
        CodeFormatter codeFormatter = new CodeFormatter();
        Debugger debugger = new Debugger();

        System.out.println("\n1. Registering plugins...");
        pluginManager.registerPlugin(logger);
        pluginManager.registerPlugin(syntaxHighlighter);
        pluginManager.registerPlugin(codeFormatter);
        pluginManager.registerPlugin(debugger);

        System.out.println("\n2. Starting all plugins...");
        pluginManager.startAllPlugins();

        System.out.println("\n3. Demonstrating Syntax Highlighter...");
        String string = "public class HelloWorld { public static void main(String[] args) { // This is a comment System.out.println(\"Hello\"); } }";
        String highlighted = syntaxHighlighter.highlightSyntax(string, "java");
        System.out.println("Original: " + string);
        System.out.println("Highlighted: " + highlighted);

        System.out.println("\n4. Demonstrating Code Formatter...");
        String s = "public class Test{public static void main(String[] args){System.out.println(\"Hello\");}}";
        String formatted = codeFormatter.formatCode(s, "java");
        System.out.println("Unformatted: " + s);
        System.out.println("Formatted: " + formatted);

        System.out.println("\n5. Demonstrating Logger...");
        logger.info("This is an info message", "Main");
        logger.warn("This is a warning message", "Main");
        logger.error("This is an error message", "Main");

        System.out.println("\n6. Demonstrating Debugger...");
        debugger.startDebugging("TestProgram.java", "java");
        debugger.addBreakpoint("TestProgram.java", 10);
        debugger.addBreakpoint("TestProgram.java", 15);
        System.out.println("Breakpoints: " + String.valueOf(debugger.getBreakpoints()));

        System.out.println("\n7. Demonstrating Event System...");
        pluginManager.publishEvent("FILE_OPENED", "TestFile.java");
        pluginManager.publishEvent("FILE_SAVED", "TestFile.java");
        pluginManager.publishEvent("DEBUG_START", "TestProgram.java");

        System.out.println("\n8. Demonstrating Plugin Management...");
        System.out.println("All plugins:");

        for (Plugin plugin : pluginManager.getAllPlugins()) {
            PrintStream var10000 = System.out;
            String var10001 = plugin.getName();
            var10000.println("  " + var10001 + " v" + plugin.getVersion() + " (" + String.valueOf(plugin.getType()) + ") - Running: " + plugin.isRunning());
        }

        System.out.println("\nPlugins by type:");

        for(PluginType var13 : PluginType.values()) {
            List var14 = pluginManager.getPluginsByType(var13);
            if (!var14.isEmpty()) {
                PrintStream var17 = System.out;
                String var18 = String.valueOf(var13);
                var17.println("  " + var18 + ": " + var14.size() + " plugin(s)");
            }
        }

        System.out.println("\n9. Demonstrating Configuration...");
        debugger.setConfiguration("autoAttach", true);

        System.out.println("\n10. Demonstrating Plugin Unregistration...");
        pluginManager.unregisterPlugin("CodeFormatter", "1.0.0");
        pluginManager.unregisterPlugin("SyntaxHighlighter", "1.0.0");
        pluginManager.unregisterPlugin("Debugger", "1.0.0");
        pluginManager.unregisterPlugin("Logger", "1.0.0");
        System.out.println("\nRemaining plugins: " + pluginManager.getAllPlugins().size());

    }


}
