package com.greatlearning.studentmgmt.service;

import java.util.List;

import com.greatlearning.studentmgmt.entity.Student;

public interface StudentService {
	public List<Student> getAllStudents();
	public void save(Student student); // shared by insert and update
	public void deleteById(int id);
	public Student findById(int id);
	public List<Student> searchStudentByNameAndCourse(String firstName,String lastName, String course);
}
