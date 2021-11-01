package com.put.rnarefiner.taskmeta;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

@Lazy
@TestConfiguration
public class TaskMetaControllerTestConfiguration {

    @LocalServerPort
    private int port;

    private String addressApiEmail;
    private TaskMeta taskMetaNullEmail;
    private TaskMeta taskMetaNotNullEmail;

    @PostConstruct
    public void setup() {
        String addressTemplate = String.format("http://localhost:%d", port) + "%s";

        addressApiEmail = String.format(addressTemplate, "/api/email");

        taskMetaNotNullEmail = new TaskMeta(null, "name.sirname@example.com");
        taskMetaNullEmail = new TaskMeta(null, null);
    }

    @Bean
    public String addressApiEmail() {
        return addressApiEmail;
    }

    @Bean TaskMeta taskMetaNullEmail() {
        return taskMetaNullEmail;
    }

    @Bean TaskMeta taskMetaNotNullEmail() {
        return taskMetaNotNullEmail;
    }

}



