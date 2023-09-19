package com.classroom_api.project.model.classroom;

import com.classroom_api.project.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponseDTO {
    private String id;
    private String name;
    private Role role;

    public ClassroomResponseDTO(String id, String name){
        this.id = id;
        this.name = name;
    }
}
