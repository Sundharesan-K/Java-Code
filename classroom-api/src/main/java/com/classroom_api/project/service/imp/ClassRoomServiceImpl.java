package com.classroom_api.project.service.imp;

import com.classroom_api.project.entity.Classroom;
import com.classroom_api.project.entity.User;
import com.classroom_api.project.exception.EmailAlreadyExistsException;
import com.classroom_api.project.mapper.ClassRoomMapper;
import com.classroom_api.project.model.classroom.AddingUserToClassRoomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomRequestDTO;
import com.classroom_api.project.model.classroom.ClassroomResponseDTO;
import com.classroom_api.project.model.classroom.UserAddingToClassRoomStatusResponseDTO;
import com.classroom_api.project.repository.ClassroomMongo;
import com.classroom_api.project.repository.ClassroomRepository;
import com.classroom_api.project.repository.UserRepository;
import com.classroom_api.project.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.classroom_api.project.utils.SecurityUtils.getCurrentUserEmail;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {
    public static final String STATUS = "done";
    private final UserRepository userRepository;
    private final ClassRoomMapper classRoomMapper;
    private final ClassroomRepository classroomRepository;
    private final ClassroomMongo classroomMongo;

    @Override
    public ClassroomResponseDTO createClassRoom(ClassroomRequestDTO classroomRequestDTO) {
        String email = getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();
        Classroom classroom = classRoomMapper.toEntity(classroomRequestDTO);
        classroomRepository.save(classroom);
        user.addClassroom(classroom);
        userRepository.save(user);
        return classRoomMapper.toDTO(classroom, user);
    }

    @Override
    public List<ClassroomResponseDTO> getOwnedClassRooms() {
        String email = getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();
        return user.getClassrooms()
                .stream()
                .map(classroom -> classRoomMapper.toDTO(classroom, user)).toList();
    }

    @Override
    @Transactional
    public UserAddingToClassRoomStatusResponseDTO addMemberToClassRoom(String classroomId, AddingUserToClassRoomRequestDTO statusResponseDTO) {
        var member = userRepository.findByEmail(statusResponseDTO.getMemberEmail()).orElseThrow();
        var classroom = classroomRepository.findById(classroomId).orElseThrow();
        List<String> list = classroom.getMembers().stream().map(User::getEmail).filter(s -> s.equals(member.getEmail())).toList();
        if (list.isEmpty()){
            classroom.addMember(member);
            classroomRepository.save(classroom);
            return new UserAddingToClassRoomStatusResponseDTO(member.getEmail(),STATUS);
        }else {
            throw new EmailAlreadyExistsException("This "+member.getEmail()+" email already exists in classroom");
        }
    }
}

//    @Override
//    public List<ClassroomResponseDTO> getOwnedClassRoomUserMemberOf() {
//        String email = getCurrentUserEmail();
//        User user = userRepository.findByEmail(email).orElseThrow();
//        Optional<List<Classroom>> classrooms = classroomRepository.findByUserClassrooms(user);
//        return classrooms.orElseThrow()
//                .stream()
//                .map(classroomResponseDTO -> classRoomMapper.toDTO(classroomResponseDTO,user)).toList();
//    }

