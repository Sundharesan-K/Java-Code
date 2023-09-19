package com.classroom_api.project.mapper;

import com.classroom_api.project.entity.Classroom;
import com.classroom_api.project.entity.User;
import com.classroom_api.project.model.classroom.ClassroomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomResponseDTO;

public interface ClassRoomMapper {
     ClassroomResponseDTO toDTO(Classroom classRoom, User user);

     Classroom toEntity(ClassroomRequestDTO classRoomRequestDTO);
}
