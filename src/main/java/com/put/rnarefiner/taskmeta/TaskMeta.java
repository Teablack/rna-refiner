package com.put.rnarefiner.taskmeta;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskMeta {

    @Getter
    @Setter
    private UUID uuid;

    @Getter
    @Setter
    private String email;


}
