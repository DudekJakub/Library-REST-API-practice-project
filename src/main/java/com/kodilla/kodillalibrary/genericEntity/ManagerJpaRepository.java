package com.kodilla.kodillalibrary.genericEntity;

import com.kodilla.kodillalibrary.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ManagerJpaRepository extends JpaRepository<Manager, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true)
    void modifyFirstnameColumn();
}
