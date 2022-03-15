package com.test.SpringBootExceptionHandling.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.SpringBootExceptionHandling.entity.Student;
import com.test.SpringBootExceptionHandling.exception.StudentNotFoundException;
import com.test.SpringBootExceptionHandling.repository.StudentRepository;

@RestController
@RequestMapping("/v1")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/student/{id}")
	ResponseEntity<Student> getStudent(@PathVariable("id") Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if(!student.isPresent()) {
			throw new StudentNotFoundException("StudentId : "+studentId);
		}
		return new ResponseEntity<Student>(student.get(), HttpStatus.OK) ;
	}
	
}
