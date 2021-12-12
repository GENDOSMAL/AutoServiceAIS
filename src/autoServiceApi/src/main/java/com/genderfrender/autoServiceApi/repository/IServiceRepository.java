package com.genderfrender.autoServiceApi.repository;

import com.genderfrender.autoServiceApi.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceRepository  extends JpaRepository<Service,Long>
{
}
