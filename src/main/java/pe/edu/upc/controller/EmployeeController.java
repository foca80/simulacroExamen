package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Employee;
import pe.edu.upc.service.IEmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private IEmployeeService eS;

	@GetMapping("/new")
	public String newEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee/employee";
	}

	@PostMapping("/save")
	public String saveEmployee(@Valid Employee employee, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "employee/employee";
		} else {
			int rpta = eS.insert(employee);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe un empleado con el DNI a registrar");
				return "/employee/employee";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}

		return "/employee/employee";
	}

	@GetMapping("/list")
	public String listEmployees(Model model) {
		try {
			model.addAttribute("employee", new Employee());
			model.addAttribute("listEmployees", eS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/employee/listEmployees";
	}

	@GetMapping("/listFind")
	public String listEmployeesFind(Model model) {
		try {
			model.addAttribute("employee", new Employee());
			model.addAttribute("listEmployees", eS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/employee/find";
	}

	@RequestMapping("/find")
	public String findByEmployee(Map<String, Object> model, @ModelAttribute Employee employee) throws ParseException {

		List<Employee> listEmployees;
		employee.setName(employee.getName());
		listEmployees = eS.findByName(employee.getName());
		if (listEmployees.isEmpty()) {
			listEmployees = eS.findByDni(employee.getName());
		}
	
		if (listEmployees.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listEmployees", listEmployees);
		return "employee/find";

	}
	@GetMapping("/listFindGross")
	public String listEmployeesFindGross(Model model) {
		try {
			model.addAttribute("employee", new Employee());
			model.addAttribute("listEmployees", eS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/employee/findGross";
	}
	@RequestMapping("/findGross")
	public String findByCategory(Map<String, Object> model, @ModelAttribute Employee employee) throws ParseException {

		List<Employee> listEmployees;
		employee.setGrossIncome(employee.getGrossIncome());
		listEmployees = eS.findByGrossIncome(employee.getGrossIncome());
		
	
		if (listEmployees.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listEmployees", listEmployees);
		return "employee/findGross";

	}
}
