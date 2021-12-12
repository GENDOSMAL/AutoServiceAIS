package com.genderfrender.autoServiceApi.repository;

import com.genderfrender.autoServiceApi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,Long>
{
	
}