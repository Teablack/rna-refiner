package com.put.rnarefiner.controller;

import com.put.rnarefiner.persistence.dao.TaskRepository;
import com.put.rnarefiner.persistence.entity.Task;
import com.put.rnarefiner.taskmeta.TaskMeta;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RnaRefinerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Resource
    private TaskRepository taskRepository;

    private String addressTemplate;

    @BeforeEach
    private void setup() {
        addressTemplate = String.format("http://localhost:%d", port) + "%s";
        taskRepository.deleteAll();
    }

    @Test
    public void postTaskMetaWithNullEmailAndReturnSameMetaWithUUID() {
        String address = String.format(addressTemplate, "/api/email");
        TaskMeta requestedTaskMeta = new TaskMeta(null, null);

        TaskMeta actualTaskMeta = this.restTemplate.postForObject(address, requestedTaskMeta, TaskMeta.class);
        assertNotNull(actualTaskMeta.getUuid());
    }

    @Test
    public void postTaskMetaWithEmailAndReturnSameMetaWithUUID() {
        String address = String.format(addressTemplate, "/api/email");

        String email = "john.smith@email.com";
        TaskMeta requestedTaskMeta = new TaskMeta(null, email);

        TaskMeta actualTaskMeta = this.restTemplate.postForObject(address, requestedTaskMeta, TaskMeta.class);
        assertEquals(email, actualTaskMeta.getEmail());
        assertNotNull(actualTaskMeta.getUuid());
    }

    @Test
    public void postTaskMetaWithEmailAndAddItToDatabaseAndReturnSameUuidAsInTheDatabase() {
        String address = String.format(addressTemplate, "/api/email");

        String email = "john.smith@email.com";
        TaskMeta requestedTaskMeta = new TaskMeta(null, email);

        TaskMeta actualTaskMeta = this.restTemplate.postForObject(address, requestedTaskMeta, TaskMeta.class);

        Task addedTaskToDB = taskRepository.findAll().iterator().next();
        assertEquals(addedTaskToDB.getId(), actualTaskMeta.getUuid());
    }
}