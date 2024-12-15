package com.example.jpa121.Repository;

import com.example.jpa121.Model.Course;
import com.example.jpa121.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Student,Integer> {
Student findStudentById(Integer id);

//    @Modifying
//    @Query(value = "DELETE FROM course_students WHERE student_id = :studentId", nativeQuery = true)
//    Student deleteStudentCourses( Integer studentId);

}
