package com.jencruz.taskapplication.tasks;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
