package com.classroom_api.project.service;

import com.classroom_api.project.model.classroom.AddingUserToClassRoomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomResponseDTO;
import com.classroom_api.project.model.classroom.UserAddingToClassRoomStatusResponseDTO;

import java.util.List;

public interface ClassRoomService {
    ClassroomResponseDTO createClassRoom(ClassroomRequestDTO classroomRequestDTO);

    List<ClassroomResponseDTO> getOwnedClassRooms();

    UserAddingToClassRoomStatusResponseDTO addMemberToClassRoom(String classroomId, AddingUserToClassRoomRequestDTO statusResponseDTO);


//    List<ClassroomResponseDTO> getOwnedClassRoomUserMemberOf();
}
