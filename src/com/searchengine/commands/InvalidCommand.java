package com.searchengine.commands;

public class InvalidCommand extends Command {
    @Override
    protected void onExecute(String[] parameters) {
        String message = "Invalid Command";
        System.out.println(message);
    }
}
