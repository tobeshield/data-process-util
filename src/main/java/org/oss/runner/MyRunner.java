package org.oss.runner;

import org.oss.processor.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private DataProcessor dataProcessor;

    @Override
    public void run(String... args) throws Exception {
        dataProcessor.run();
    }

}
