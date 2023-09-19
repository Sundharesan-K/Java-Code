package com.classroom_api.project.repository;

import com.classroom_api.project.entity.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepository extends MongoRepository<Classroom,String> {
    boolean findByIdAndId(String classroomId, String id);

    Optional<Classroom> findClassroomByMembers(String classroomId,String email);
//    Optional<List<Classroom>> findByUserClassrooms(User user);
}
