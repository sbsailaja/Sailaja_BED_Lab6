package com.greatlearning.studentmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.studentmgmt.entity.Student;
import com.greatlearning.studentmgmt.repository.StudentsRepository;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentsRepository repository;
	@Override
	public List<Student> getAllStudents() {
		return repository.findAll();
	}
	@Override
	public void save(Student student) {
		repository.save(student);
	}
	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
	}
	@Override
	public Student findById(int id) {
		Optional<Student> optStudent = repository.findById(id);
		if(optStudent.isPresent()) {
			return optStudent.get();
		}else {
			throw new RuntimeException("Student Id is not present");
		}
	}
	@Override
	public List<Student> searchStudentByNameAndCourse(String firstName, String lastName, String course) {
		if(firstName.isEmpty() && lastName.isEmpty() && course.isEmpty()) {
			return getAllStudents();
		}
		return repository.findByFirstNameContainsAndLastNameContainsAndCourseContainsAllIgnoreCase(firstName, lastName, course);
	}

}
