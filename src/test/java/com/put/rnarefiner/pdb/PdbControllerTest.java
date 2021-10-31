package com.put.rnarefiner.pdb;

import com.put.rnarefiner.persistence.task.Task;
import com.put.rnarefiner.persistence.task.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(PdbControllerTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PdbControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private String addressApiPdbTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MockMultipartFile pdb;

    @BeforeEach
    public void reload() {
        taskRepository.deleteAll();
    }

    @Test
    public void addFileToDatabaseAndReturnCode200ForExistingUUID() {
        UUID addedTaskUUID = taskRepository.save(new Task()).getId();
        String addressApiPdb = String.format(addressApiPdbTemplate, addedTaskUUID);

        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(addressApiPdb, pdb, String.class);

        assertTrue(responseEntity.getStatusCode().is2xxSuccessful()); //TODO
    }

}