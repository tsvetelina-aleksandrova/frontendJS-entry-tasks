package com.task.simple.logger;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ConsoleLogger extends AbstractLogger {

    @Override
    Writer getOutputWriter() {
        return new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public static void main(String[] args) {
        new ConsoleLogger().log(1, "safas");
    }
}
