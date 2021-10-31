package com.put.rnarefiner.taskmeta;

import com.put.rnarefiner.persistence.task.Task;
import com.put.rnarefiner.persistence.task.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TaskMetaControllerTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskMetaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private String addressApiEmail;

    @Autowired
    private TaskMeta taskMetaNullEmail;

    @Autowired
    private TaskMeta taskMetaNotNullEmail;

    @Resource
    private TaskRepository taskRepository;

    @BeforeEach
    public void reload() {
        taskRepository.deleteAll();
    }

    @Test
    public void postTaskMetaWithNullEmailAndReturnSameMetaWithUUID() {
        TaskMeta actualTaskMeta = this.restTemplate.postForObject(addressApiEmail, taskMetaNullEmail, TaskMeta.class);

        assertNotNull(actualTaskMeta.getUuid());
    }

    @Test
    public void postTaskMetaWithEmailAndReturnSameMetaWithUUID() {
        TaskMeta actualTaskMeta = this.restTemplate.postForObject(addressApiEmail, taskMetaNotNullEmail, TaskMeta.class);

        assertEquals(taskMetaNotNullEmail.getEmail(), actualTaskMeta.getEmail());
        assertNotNull(actualTaskMeta.getUuid());
    }

    @Test
    public void postTaskMetaWithEmailAndAddItToDatabaseAndReturnSameUuidAsInTheDatabase() {
        TaskMeta actualTaskMeta = this.restTemplate.postForObject(addressApiEmail, taskMetaNullEmail, TaskMeta.class);
        Task addedTaskToDB = taskRepository.findAll().iterator().next();

        assertEquals(addedTaskToDB.getId(), actualTaskMeta.getUuid());
    }

}