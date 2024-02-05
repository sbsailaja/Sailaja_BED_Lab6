package com.greatlearning.studentmgmt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentmgmt.entity.Student;
import com.greatlearning.studentmgmt.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController {
	
	@Autowired
	StudentService service;
	
	@RequestMapping("/list")
	public String getAllStudents(Model model) {
		List<Student> students = service.getAllStudents();
		model.addAttribute("students",students);
		return "students/list-students";
	}
	
	@GetMapping("/showFormForAdd")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student",student);
		return "students/student-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String updateStudent(Model model,@RequestParam("studentId") int studentId) {
		Student student = service.findById(studentId);
		model.addAttribute("student",student);
		return "students/student-form";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("studentId") int studentId) {
		service.deleteById(studentId);
		return "redirect:/students/list";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("student") Student student) {
		service.save(student);
		return "redirect:/students/list";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("course") String course,Model model) {
		List<Student> students = service.searchStudentByNameAndCourse(firstName, lastName, course);
		model.addAttribute("students",students);
		return "students/list-students";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have persmission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("students/403");
		return model;

	}
}
