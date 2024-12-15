package com.example.jpa121.Service;

import com.example.jpa121.ApiResponse.ApiException;
import com.example.jpa121.DTO.CourseDTO;
import com.example.jpa121.Model.Course;
import com.example.jpa121.Model.Student;
import com.example.jpa121.Model.Teacher;
import com.example.jpa121.Repository.CourseRepository;
import com.example.jpa121.Repository.StudentsRepository;
import com.example.jpa121.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    private final StudentsRepository studentsRepository;

    /// old get
//    public List<Course>getAllCourse(){
//        return courseRepository.findAll();
//    }

    public List<CourseDTO>getAllCourse(){
List<Course>courses=courseRepository.findAll();

List<CourseDTO>courseDTOS=new ArrayList<>();

for(Course c:courses){
CourseDTO courseDTO=new CourseDTO(c.getName());
courseDTOS.add(courseDTO);
}
return courseDTOS;
    }



    public void addNewCourse(Integer teacher_id,Course course){
        Teacher teacher=teacherRepository.findTeacherById(teacher_id);
        if(teacher==null){
            throw new ApiException("Teacher cannot found ");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);

    }
   public void updateCourse(Integer id,Course course){
        Course oldCourse=courseRepository.findCourseById(id);
        if(oldCourse==null){
            throw new ApiException("Course cannot found");
        }
        oldCourse.setName(course.getName());

        courseRepository.save(oldCourse);
}

public void deleteCourse(Integer id){
    Course course=courseRepository.findCourseById(id);
    if(course==null){
        throw new ApiException("Course cannot found");
    }
    courseRepository.delete(course);
}




}
