package com.put.rnarefiner.taskmeta;

import com.put.rnarefiner.persistence.task.TaskRepository;
import com.put.rnarefiner.persistence.task.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import javax.annotation.Resource;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskMetaControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Resource
    private TaskRepository taskRepository;

    private String addressTemplate;

    @BeforeEach
    private void reload() {
        addressTemplate = String.format("http://localhost:%d", port) + "%s";
        taskRepository.deleteAll();
    }

    @Test
    public void postTaskMetaWithNullEmailAndReturnSameMetaWithUUID() {
        TaskMeta requestedTaskMeta = new TaskMeta(null, null);
        String address = String.format(addressTemplate, "/api/email");

        TaskMeta actualTaskMeta = this.restTemplate.postForObject(address, requestedTaskMeta, TaskMeta.class);

        assertNotNull(actualTaskMeta.getUuid());
    }

    @Test
    public void postTaskMetaWithEmailAndReturnSameMetaWithUUID() {
        String email = "john.smith@email.com";
        TaskMeta requestedTaskMeta = new TaskMeta(null, email);
        String address = String.format(addressTemplate, "/api/email");

        TaskMeta actualTaskMeta = this.restTemplate.postForObject(address, requestedTaskMeta, TaskMeta.class);

        assertEquals(email, actualTaskMeta.getEmail());
        assertNotNull(actualTaskMeta.getUuid());
    }

    @Test
    public void postTaskMetaWithEmailAndAddItToDatabaseAndReturnSameUuidAsInTheDatabase() {
        String email = "john.smith@email.com";
        TaskMeta requestedTaskMeta = new TaskMeta(null, email);
        String address = String.format(addressTemplate, "/api/email");

        TaskMeta actualTaskMeta = this.restTemplate.postForObject(address, requestedTaskMeta, TaskMeta.class);
        Task addedTaskToDB = taskRepository.findAll().iterator().next();

        assertEquals(addedTaskToDB.getId(), actualTaskMeta.getUuid());
    }

}