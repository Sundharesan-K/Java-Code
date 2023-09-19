package com.classroom_api.project.controller;

import com.classroom_api.project.model.classroom.AddingUserToClassRoomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomResponseDTO;
import com.classroom_api.project.model.classroom.UserAddingToClassRoomStatusResponseDTO;
import com.classroom_api.project.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassRoomService classRoomService;
    @PostMapping()
    public ResponseEntity<ClassroomResponseDTO> createClassroom(@RequestBody ClassroomRequestDTO classroomRequestDTO){
        ClassroomResponseDTO classroomResponseDTO = classRoomService.createClassRoom(classroomRequestDTO);
        return new ResponseEntity<>(classroomResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/owned")
    public ResponseEntity<List<ClassroomResponseDTO>> getOwnedClassRooms(){
        List<ClassroomResponseDTO> responseDTO = classRoomService.getOwnedClassRooms();
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

//    @GetMapping("/member-of")
//    public ResponseEntity<List<ClassroomResponseDTO>> getClassRoomUserMemberOf(){
//        List<ClassroomResponseDTO> responseDTO = classRoomService.getOwnedClassRoomUserMemberOf();
//        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
//    }

    @PostMapping("/{classroomId}/members")
    public ResponseEntity<UserAddingToClassRoomStatusResponseDTO> addMemberToClassRoom(@PathVariable String classroomId, @RequestBody AddingUserToClassRoomRequestDTO addingUserToClassRoomRequestDTO){
        UserAddingToClassRoomStatusResponseDTO classRoomStatusResponseDTO = classRoomService.addMemberToClassRoom(classroomId,addingUserToClassRoomRequestDTO);
        return new ResponseEntity<>(classRoomStatusResponseDTO,HttpStatus.CREATED);
    }
}
