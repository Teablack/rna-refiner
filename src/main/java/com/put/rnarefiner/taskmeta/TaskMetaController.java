package com.put.rnarefiner.taskmeta;

import com.put.rnarefiner.persistence.task.Task;
import com.put.rnarefiner.persistence.task.TaskRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskMetaController {

    final TaskRepository taskRepository;

    public TaskMetaController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping(value = "/api/email", consumes = "application/json", produces = "application/json")
    public TaskMeta postTaskMeta(@RequestBody TaskMeta taskMeta) {
        Task addedTask = taskRepository.save(new Task(taskMeta.getEmail()));
        return new TaskMeta(addedTask.getId(), addedTask.getEmail());
    }

}
