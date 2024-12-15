package com.example.jpa121.Controller;

import com.example.jpa121.ApiResponse.ApiResponse;
import com.example.jpa121.DTO.StudentDTO;
import com.example.jpa121.Model.Student;
import com.example.jpa121.Model.Teacher;
import com.example.jpa121.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudent(){
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student){
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getID(@PathVariable Integer id){
        return ResponseEntity.status(200).body(studentService.getByStudentId(id));
    }

    @PutMapping("/assign/{course_id}/{student_id}")
    public ResponseEntity assignCourses(@PathVariable Integer course_id,@PathVariable Integer student_id){
        studentService.assignCoursesToStudent(course_id,student_id);
        return ResponseEntity.status(200).body(new ApiResponse("assign"));

    }

    @PutMapping("/chang/{student_id}/{major}")
public ResponseEntity changMajor(@PathVariable Integer student_id,@PathVariable String major){
        studentService.changeStudentMajor(student_id, major);
    return ResponseEntity.status(200).body(new ApiResponse("Done Changing"));
}

@GetMapping("/list/{course_id}")
public ResponseEntity returnListOfStudent(@PathVariable Integer course_id){
        List<StudentDTO>studentDTOS=studentService.getListOfStudent(course_id);
    return ResponseEntity.status(200).body(studentDTOS);
}
}
