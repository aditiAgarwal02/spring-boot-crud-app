package com.first.starting.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        String location
) {
    public run{
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Completed on must be after started on");
        }
    }

}
