package com.classroom_api.project.model.classroom;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddingUserToClassRoomRequestDTO {
    @NotNull
    @Size(min = 5,max = 20)
    private String memberEmail;
}
