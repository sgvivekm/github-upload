package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	public Long getStudentCount(){
		return studentRepository.count();
	}
	
	public Student setStudent(Student student) {
		Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("Email exists");
		}
		return studentRepository.save(student);
	
	}

	public void deleteStudent(Long id) {
		boolean exist=studentRepository.existsById(id);
		if(!exist) {
			throw new IllegalStateException("student id :"+id+" does not exists");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student= studentRepository.findById(id)
				.orElseThrow(()-> new IllegalStateException("id does not exist"));
		if(name != null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		if(email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentByEmail= studentRepository.findStudentByEmail(email);
			if(studentByEmail.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			student.setEmail(email);
		}
		
	}
	
	
}
