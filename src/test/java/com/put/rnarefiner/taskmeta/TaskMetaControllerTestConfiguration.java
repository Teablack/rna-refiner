package com.put.rnarefiner.taskmeta;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TaskMetaControllerTestConfiguration {

    @LocalServerPort
    private int port;

    final String addressApiEmail;
    final TaskMeta taskMetaNullEmail;
    final TaskMeta taskMetaNotNullEmail;

    public TaskMetaControllerTestConfiguration() {
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
        return taskMetaNotNullEmail;
    }

    @Bean TaskMeta taskMetaNotNullEmail() {
        return taskMetaNotNullEmail;
    }

}
