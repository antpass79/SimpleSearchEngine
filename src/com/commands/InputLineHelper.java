package com.commands;

public class InputLineHelper {
    public static String extractCommandName(String line) {
        return line.equals(Commands.COMMAND_QUIT) ? Commands.COMMAND_QUIT : "";
    }

    public static String[] extractCommandParameters(String line) {
        return line.equals(Commands.COMMAND_QUIT) ? new String[]{} : line.split(" ");
    }
}
