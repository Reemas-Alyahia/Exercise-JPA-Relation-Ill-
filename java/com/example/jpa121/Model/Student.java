package com.example.jpa121.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be Empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "age must be not null")
    @Column(columnDefinition = "int not null")
    private Integer age;


    @NotEmpty(message = "major cannot be Empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private String major;


    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "students")
    private Set<Course>courses;


}
