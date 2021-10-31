package com.put.rnarefiner.persistence.entity;

import com.put.rnarefiner.enums.Status;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String email;
    private byte[] task;
    private byte[] solution;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(String email) {
        this.email = email;
        this.task = null;
        this.solution = null;
        this.status = Status.QUEUED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
