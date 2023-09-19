package com.classroom_api.project.model.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddingToClassRoomStatusResponseDTO {
    private String email;

    private String status;
}
