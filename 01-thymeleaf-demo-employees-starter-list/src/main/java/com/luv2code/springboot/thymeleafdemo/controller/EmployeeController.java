package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService ES;
	public EmployeeController(EmployeeService thisES){
		ES =thisES;
	}
	// load employee data

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
        List<Employee>theEmployees=ES.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Employee theEmployee =new Employee();
		theModel.addAttribute("employee", theEmployee);
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee theEmployee){
		ES.save(theEmployee);
        return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){

      Employee theEmployee=ES.findById(theId);
	  theModel.addAttribute("employee",theEmployee);
	  return "employees/employee-form";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId){
		ES.deleteById(theId);
		return "redirect:/employees/list";
	}

}









