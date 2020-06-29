package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "Debe ingresar Nombre*")
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Size(min = 8, max = 8, message = "El DNI tiene que ser de 8 digitos")
	@NotEmpty(message = "Debe ingresar DNI*")
	@Column(name = "dni", nullable = false, length = 8)
	private String dni;

	@Min(1000)
	@Max(7000)
	@Column(name = "grossIncome", nullable = false)
	private double grossIncome;

	@Transient // es un valor/atributo pero no es persistente
	private double netIncome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(double grossIncome) {
		this.grossIncome = grossIncome;
	}

	public double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}

}
