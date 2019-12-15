package com.searchengine.commands;

public class QuitCommand extends Command {
    @Override
    protected void onExecute(String[] inputParameters) {
        Integer code = 0;
        System.exit(code);
    }
}
