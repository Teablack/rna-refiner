package com.put.rnarefiner.taskmeta;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskMetaController {

    @PostMapping(value = "/api/email", consumes = "application/json", produces = "application/json")
    public TaskMeta postTaskMeta(@RequestBody TaskMeta taskMeta) {
        return new TaskMeta(taskMeta.getEmail(), "xxx");
    }

}
