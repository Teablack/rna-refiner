package com.put.rnarefiner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.put.rnarefiner.taskmeta.TaskMeta;
import com.put.rnarefiner.persistence.dao.TaskRepository;
import org.json.JSONException;
import org.json.JSONObject;
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
    private void setup() throws JSONException {
        addressTemplate = String.format("http://localhost:%d", port) + "%s";

        JSONObject taskJsonWithEmail = new JSONObject();
        taskJsonWithEmail.put("email", "john.smith@email.com");

        JSONObject taskJsonWithoutEmail = new JSONObject();
        taskJsonWithoutEmail.put("email", null);
    }

    @Test
    public void postEmailAndReturnUUID() throws JsonProcessingException {
        String address = String.format(addressTemplate, "/api/email");

        TaskMeta requestedTaskMeta = new TaskMeta("john.smith@email.com", null);
        TaskMeta expectedTaskMeta = new TaskMeta("john.smith@email.com", "xxx");

        ObjectMapper objectMapper = new ObjectMapper();
        TaskMeta actualTaskMeta = this.restTemplate.postForObject(address, requestedTaskMeta, TaskMeta.class);
        assertEquals(expectedTaskMeta, actualTaskMeta);
    }

}