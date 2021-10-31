package com.put.rnarefiner.persistence.dao;


import com.put.rnarefiner.persistence.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<Task, UUID> {
}
