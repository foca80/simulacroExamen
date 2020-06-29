package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select count(e.dni) from Employee e where e.dni =:dni")
	public int searchDniEmployee(@Param("dni") String dni);

	@Query("select e from Employee e where e.name like %:name%")
	List<Employee> findByName(String name);

	@Query("select e from Employee e where e.dni like %:dni%")
	List<Employee> findByDni(String dni);

	@Query("select e from Employee e where e.grossIncome = :grossIncome")
	List<Employee> findBygrossIncome(Double grossIncome);
}