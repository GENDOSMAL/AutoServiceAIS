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
}
