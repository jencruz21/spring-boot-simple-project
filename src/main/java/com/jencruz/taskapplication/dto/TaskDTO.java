package com.jencruz.taskapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class TaskDTO {

    private String id;
    @NotEmpty(message = "name should not be empty")
    private String taskName;
    @NotEmpty(message = "description should not be empty")
    private String description;
    @NotNull(message = "status should not be empty")
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
