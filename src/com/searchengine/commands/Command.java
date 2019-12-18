package com.searchengine.commands;

public abstract class Command implements ICommand {
    final Object[] constructorParameters;
    protected Object[] getConstructorParameters() {
        return this.constructorParameters;
    }

    Command() {
        this.constructorParameters = new Object[] {};
    }

    Command(Object[] constructorParameters) {
        this.constructorParameters = constructorParameters;
    }

    @Override
    public final void execute(String[] commandParameters) {
        this.onExecute(commandParameters);
    }

    protected abstract void onExecute(String[] commandParameters);
}
