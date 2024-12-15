package com.example.jpa121.Service;

import com.example.jpa121.ApiResponse.ApiException;
import com.example.jpa121.DTO.CourseDTO;
import com.example.jpa121.DTO.StudentDTO;
import com.example.jpa121.Model.Course;
import com.example.jpa121.Model.Student;
import com.example.jpa121.Repository.CourseRepository;
import com.example.jpa121.Repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentsRepository studentsRepository;

    private final CourseRepository courseRepository;


    public List<StudentDTO>getAllStudents(){
   List<Student>students=studentsRepository.findAll();

   List<StudentDTO>studentDTOS=new ArrayList<>();

   for(Student s:students){
List<CourseDTO>courseDTOS=new ArrayList<>();
for(Course c:s.getCourses()){
CourseDTO courseDTO=new CourseDTO(c.getName());
courseDTOS.add(courseDTO);
}
StudentDTO studentDTO=new StudentDTO(s.getName(),s.getAge(),s.getMajor(),courseDTOS);

studentDTOS.add(studentDTO);
   }
   return studentDTOS;
    }

    public void addStudent(Student student){
        studentsRepository.save(student);

    }


    public Student getByStudentId(Integer id){
        Student student=studentsRepository.findStudentById(id);
        if(student==null){
            throw new ApiException("cannot found id");
        }
       return student;
    }

////// ///assign

public void assignCoursesToStudent(Integer course_id,Integer student_id){
     Course course=courseRepository.findCourseById(course_id);
     Student student=studentsRepository.findStudentById(student_id);

     if(course==null||student==null){
        throw new ApiException("Cannot assign");
     }

     student.getCourses().add(course);
     course.getStudents().add(student);

     studentsRepository.save(student);
     courseRepository.save(course);

}

/// Create endpoint that takes student id and major and change the student major
/// (changing the major will drop all the cousres that the student attended to )


//    public void changingMajor(Integer student_id,String major) {
//        Student student = studentsRepository.findStudentById(student_id);
//
//        if (student == null) {
//            throw new ApiException("Cannot found the Student");
//        }
//        student.getCourses().clear();
//        student.setMajor(major);
//
//        studentsRepository.save(student);
//
//    }

public void changeStudentMajor(Integer studentId, String newMajor) {

    Student student =studentsRepository.findStudentById(studentId);
    if(student==null){
        throw new ApiException("Cannot found the Student");
    }
    studentsRepository.deleteStudentCourses(studentId);


    student.setMajor(newMajor);


    studentsRepository.save(student);
}

    //Create endpoint that takes course id and return the student list
    public List<StudentDTO> getListOfStudent(Integer course_id) {

        Course course = courseRepository.findCourseById(course_id);
        if(course==null){
            throw new ApiException(" Cannot find the Course");
        }


        List<Student> students = new ArrayList<>(course.getStudents());

        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student s : students) {
            List<CourseDTO> courseDTOS = new ArrayList<>();
            for (Course c : s.getCourses()) {
                CourseDTO courseDTO = new CourseDTO(c.getName());
                courseDTOS.add(courseDTO);
            }

            StudentDTO studentDTO = new StudentDTO(s.getName(), s.getAge(), s.getMajor(), courseDTOS);
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }



    }
