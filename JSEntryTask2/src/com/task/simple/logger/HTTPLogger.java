package com.task.simple.logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPLogger extends AbstractLogger {
    private URL logUrl;

    public HTTPLogger(final URL logUrl) {
        this.logUrl = logUrl;
    }

    @Override
    Writer getOutputWriter() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) logUrl.openConnection();
        OutputStream out = connection.getOutputStream();
        return new BufferedWriter(new OutputStreamWriter(out));
    }
}
