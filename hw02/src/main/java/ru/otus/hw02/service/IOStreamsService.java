package ru.otus.hw02.service;

import java.io.PrintStream;

public class IOStreamsService implements IOService {
    private final PrintStream printStream;

    public IOStreamsService(PrintStream printStream) {

        this.printStream = printStream;
    }

    @Override
    public void printLine(String s) {
        printStream.println(s);
    }

    @Override
    public void printFormattedLine(String s, Object... args) {
        printStream.printf(s + "%n", args);
    }
}
