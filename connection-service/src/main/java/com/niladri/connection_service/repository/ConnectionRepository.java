package com.niladri.connection_service.repository;

import com.niladri.connection_service.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Person,Long> {
}
