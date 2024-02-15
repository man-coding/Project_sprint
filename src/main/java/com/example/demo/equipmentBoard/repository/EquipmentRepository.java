package com.example.demo.equipmentBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.equipmentBoard.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
