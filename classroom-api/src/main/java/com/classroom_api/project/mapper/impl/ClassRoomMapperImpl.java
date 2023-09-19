package com.classroom_api.project.mapper.impl;

import com.classroom_api.project.entity.Classroom;
import com.classroom_api.project.entity.User;
import com.classroom_api.project.mapper.ClassRoomMapper;
import com.classroom_api.project.model.classroom.ClassroomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ClassRoomMapperImpl implements ClassRoomMapper {
    @Override
    public ClassroomResponseDTO toDTO(Classroom classRoom, User user) {
        return new ClassroomResponseDTO(classRoom.getId(), classRoom.getName(),user.getRole());
    }

    @Override
    public Classroom toEntity(ClassroomRequestDTO classRoomRequestDTO) {
        return new Classroom(classRoomRequestDTO.getName());
    }
}
