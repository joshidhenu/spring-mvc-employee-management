package com.stackcodie.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stackcodie.employeemanagement.beans.Employee;
import com.stackcodie.employeemanagement.dao.EmployeeDao;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao dao;
	
	@RequestMapping(value = "empform", method = RequestMethod.GET)
	public String showform(Model m) {
		m.addAttribute("command", new Employee());
		System.out.println("employee form");
		return "redirect:/empform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("employee") Employee employee) {
		dao.save(employee);
		return "redirect:/viewemp";
	}
	
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<Employee> list = dao.getEmployees();
		m.addAttribute("list",list);
		return "viewemp";
	}
}
