package com.genderfrender.autoServiceApi.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Getter
@Setter
@ToString
@Entity
public class Employee
{
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private long Id;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "name")
	private String name;

	@Column(name = "patronymics")
	private String patronymics;
	
	@Column(name = "specialization")
	private String specialization;
	
	@Column(name = "is_work_now", nullable = false)
	private boolean isWorkNow;
	
	@Column(name = "salary_per_point", nullable = false)
	private float salaryPerPoint;

	public long getId()
	{
		return Id;
	}

	public void setId(long id)
	{
		Id = id;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPatronymics()
	{
		return patronymics;
	}

	public void setPatronymics(String patronymics)
	{
		this.patronymics = patronymics;
	}

	public String getSpecialization()
	{
		return specialization;
	}

	public void setSpecialization(String specialization)
	{
		this.specialization = specialization;
	}

	public boolean getIsWorkNow()
	{
		return isWorkNow;
	}

	public void setWorkNow(boolean workNow)
	{
		isWorkNow = workNow;
	}

	public float getSalaryPerPoint()
	{
		return salaryPerPoint;
	}

	public void setSalaryPerPoint(float salaryPerPoint)
	{
		this.salaryPerPoint = salaryPerPoint;
	}
}
