package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Employee;
import pe.edu.upc.repository.IEmployeeRepository;
import pe.edu.upc.service.IEmployeeService;

@Repository
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private IEmployeeRepository eR;

	@Transactional
	@Override
	public Integer insert(Employee employee) {
		int rpta = eR.searchDniEmployee(employee.getDni());//0 si no existe el dni
		if (rpta == 0) {
			eR.save(employee);
		}
		return rpta;
	}

	@Override
	public List<Employee> findByName(String name) {
		// TODO Auto-generated method stub
//		return eR.findByName(name);

		List<Employee> lista = eR.findByName(name);

		for (Employee e : lista) {

			if (e.getGrossIncome() * 12 > 29400) {

				e.setNetIncome(e.getGrossIncome() - (0.27 * e.getGrossIncome()));

			} else {

				e.setNetIncome(e.getGrossIncome() - 0.12 * e.getGrossIncome());

			}
		}

		return lista;
	}

	@Override
	public List<Employee> findByDni(String dni) {
		// TODO Auto-generated method stub
		// return eR.findByDni(dni);
		List<Employee> lista = eR.findByDni(dni);

		for (Employee e : lista) {

			if (e.getGrossIncome() * 12 > 29400) {

				e.setNetIncome(e.getGrossIncome() - (0.27 * e.getGrossIncome()));

			} else {

				e.setNetIncome(e.getGrossIncome() - 0.12 * e.getGrossIncome());

			}
		}

		return lista;
	}

	@Override
	public List<Employee> list() {
		List<Employee> lista = eR.findAll();

		for (Employee e : lista) {

			if (e.getGrossIncome() * 12 > 29400) {

				e.setNetIncome(e.getGrossIncome() - (0.27 * e.getGrossIncome()));

			} else {

				e.setNetIncome(e.getGrossIncome() - 0.12 * e.getGrossIncome());

			}
		}

		return lista;
	}

	@Override
	public List<Employee> findByGrossIncome(Double grossIncome) {
		List<Employee> lista = eR.findBygrossIncome(grossIncome);

		for (Employee e : lista) {

			if (e.getGrossIncome() * 12 > 29400) {

				e.setNetIncome(e.getGrossIncome() - (0.27 * e.getGrossIncome()));

			} else {

				e.setNetIncome(e.getGrossIncome() - 0.12 * e.getGrossIncome());

			}
		}

		return lista;
	}

}
