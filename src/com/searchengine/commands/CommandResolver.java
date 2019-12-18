package com.searchengine.commands;

import java.util.HashMap;

public class CommandResolver {
    static final HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    public static void register(String key, ICommand command) {
        if (!commands.containsKey(key)) {
            commands.put(key, command);
        }
    }

    public static ICommand resolve(String key) {
        if (!commands.containsKey(key))
            return commands.get(Commands.COMMAND_SEARCH);

        return commands.get(key);
    }
}
