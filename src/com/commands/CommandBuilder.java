package com.commands;

public class CommandBuilder implements ICommandBuilder {
    Object[] constructorParameters = new Object[] {};
    String commandName;

    @Override
    public ICommandBuilder constructorParameters(Object[] parameters) {
        this.constructorParameters = parameters;
        return this;
    }
    @Override
    public ICommandBuilder commandName(String commandName) {
        this.commandName = commandName;
        return this;
    }
    @Override
    public ICommand build() {
        ICommand command;

        try {
            switch (this.commandName) {
                case Commands.COMMAND_QUIT:
                    command = new QuitCommand();
                    break;
                case Commands.COMMAND_SEARCH:
                    command = new SearchCommand(this.constructorParameters);
                    break;
                default:
                    command = new InvalidCommand();
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            command = new InvalidCommand();
        }

        return command;
    }
}
