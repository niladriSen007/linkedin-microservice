package com.niladri.connection_service.repository;

import com.niladri.connection_service.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Person,Long> {

    @Query(value = "SELECT c.connection_user_id FROM connections c WHERE c.user_id = :userId",nativeQuery = true)
    List<Long> findAllConnectionOfUser(@Param("userId") Long userId);

    boolean existsByUserIdAndConnectionUserId(Long userId, Long connectionUserId);
}
