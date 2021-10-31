package com.put.rnarefiner.persistence.entity;

import com.put.rnarefiner.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String email;
    private byte[] task;
    private byte[] solution;

    @Enumerated(EnumType.STRING)
    private Status status;

}
