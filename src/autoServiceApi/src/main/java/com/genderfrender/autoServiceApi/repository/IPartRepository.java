package com.genderfrender.autoServiceApi.repository;

import com.genderfrender.autoServiceApi.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartRepository extends JpaRepository<Part,Long>
{
}
