package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.domain.Manager;
import com.kodilla.kodillalibrary.genericEntity.ManagerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbServiceManager {

    @Autowired
    ManagerJpaRepository managerJpaRepository;

    public void modifyEntityColumn() {
        managerJpaRepository.modifyFirstnameColumn();
    }
}
