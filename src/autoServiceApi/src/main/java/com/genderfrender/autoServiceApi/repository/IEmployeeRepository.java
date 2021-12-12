package com.genderfrender.autoServiceApi.repository;

import com.genderfrender.autoServiceApi.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee,Long>
{
}
