package com.put.rnarefiner.persistence.task;


import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {
}
