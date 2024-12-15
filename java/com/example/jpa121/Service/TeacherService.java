package com.example.jpa121.Service;

import com.example.jpa121.ApiResponse.ApiException;
import com.example.jpa121.DTO.CourseDTO;
import com.example.jpa121.DTO.TeacherDTO;
import com.example.jpa121.Model.Address;
import com.example.jpa121.Model.Course;
import com.example.jpa121.Model.Teacher;
import com.example.jpa121.Repository.AddressRepository;
import com.example.jpa121.Repository.CourseRepository;
import com.example.jpa121.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final CourseRepository courseRepository;

/// Old get
//    public List<Teacher> getALLTeacher(){
//        return teacherRepository.findAll();
//    }


public List<TeacherDTO> getALLTeacher(){
    List<Teacher>teachers=teacherRepository.findAll();

    List<TeacherDTO>teacherDTOS=new ArrayList<>();

    for(Teacher t:teachers){
        List<CourseDTO>courseDTOS=new ArrayList<>();
        for(Course c:t.getCourses()){
            CourseDTO courseDTO=new CourseDTO(c.getName());
            courseDTOS.add(courseDTO);
        }
        TeacherDTO teacherDTO=new TeacherDTO(t.getName(),t.getAge(),t.getEmail(),t.getSalary(),courseDTOS);
        teacherDTOS.add(teacherDTO);
    }
    return teacherDTOS;
}
    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Teacher teacher,Integer id){
        Teacher teacher1=teacherRepository.findTeacherById(id);
        if(teacher1==null){
            throw new ApiException("Teacher cannot found ");
        }
        teacher1.setAddress(teacher.getAddress());
        teacher1.setAge(teacher.getAge());
        teacher1.setName(teacher.getName());
        teacher1.setSalary(teacher.getSalary());
        teacher1.setEmail(teacher.getEmail());

        teacherRepository.save(teacher1);
    }

    public void deleteTeacher(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);
        if(teacher==null){
            throw new ApiException("Teacher cannot found ");
        }
        Address address=addressRepository.findAddressById(id);
        teacher.setAddress(null);

        addressRepository.delete(address);
        teacherRepository.delete(teacher);
    }

//Create endpoint that takes teacher id and return All
//teacher details
    public Teacher getByTeacherId(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);
        if(teacher==null){
            throw new ApiException("Teacher cannot found ");
        }
        return teacher;
    }

    //Create endpoint that take course id and return the teacher
 //name for that class
    public String returnCourseTeacher(Integer course_id){
        Course course=courseRepository.findCourseById(course_id);
        if(course==null){
            throw new ApiException("Course cannot found");
        }
        if(course.getTeacher()==null){
            throw new ApiException("Teacher cannot found");

        }
        return course.getTeacher().getName();
    }
}
