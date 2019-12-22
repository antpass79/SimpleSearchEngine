package com.commands;

public interface ICommandBuilder {
    ICommandBuilder constructorParameters(Object[] parameters);
    ICommandBuilder commandName(String inputLine);
    ICommand build();
}
