package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "api/v1/")
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService= studentService;
	}
	
	@GetMapping(path= "student")
	public List<Student> getStudents(){
		return studentService.getStudents();
	}
	
	@GetMapping(path= "student2")
	public Long getStudentsCount(){
		return studentService.getStudentCount();
	}
	
	@PostMapping(path= "student")
	public Student setStudent(@RequestBody Student student) {
		return studentService.setStudent(student);
	}
	
	@DeleteMapping(path="student/{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long id ) {
		studentService.deleteStudent(id);
	}
	
	@PutMapping(path="student/{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long id, 
			@RequestParam(required=false) String firstName,
			@RequestParam(required=false) String email) {
		studentService.updateStudent(id, firstName, email);
	}
}
