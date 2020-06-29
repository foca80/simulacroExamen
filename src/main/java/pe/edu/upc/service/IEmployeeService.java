package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Employee;

public interface IEmployeeService {

	public Integer insert(Employee employee);

	List<Employee> list();

	public List<Employee> findByName(String name);

	public List<Employee> findByDni(String dni);

	public List<Employee> findByGrossIncome(Double grossIncome);

}
