package com.put.rnarefiner.taskmeta;

import com.put.rnarefiner.persistence.dao.TaskRepository;
import com.put.rnarefiner.persistence.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TaskMetaController {

    @Autowired
    TaskRepository taskRepository;

    @PostMapping(value = "/api/email", consumes = "application/json", produces = "application/json")
    public TaskMeta postTaskMeta(@RequestBody TaskMeta taskMeta) {
        Task addedTask = taskRepository.save(new Task(taskMeta.getEmail()));
        return new TaskMeta(addedTask.getId(), addedTask.getEmail());
    }

}
