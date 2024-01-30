package com.stackcodie.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "empform";
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
	
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id,Model m) {
		Employee emp = dao.getEmployeeById(id);
		m.addAttribute("command",emp);
		return "empeditform";
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("employee")Employee employee) {
		dao.update(employee);
		return "redirect:/viewemp";
	}
	
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewemp";
	}
}
