package com.put.rnarefiner.taskmeta;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskMeta {

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String uuid;

}
