package com.greatlearning.studentmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.studentmgmt.entity.Student;

public interface StudentsRepository extends JpaRepository<Student, Integer>{
	//Customized Query through MethodName Signature or Using @Query annotation.
	List<Student> findByFirstNameContainsAndLastNameContainsAndCourseContainsAllIgnoreCase(String firstName,String lastName, String course);
}
