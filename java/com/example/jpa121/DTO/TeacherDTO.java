package com.example.jpa121.DTO;

import com.example.jpa121.Model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor

public class TeacherDTO {


    private String name;


    private Integer age;


    private String email;


    private Integer salary;



    private List<CourseDTO> course;


}
