package com.example.lanpartyapi.repository;

import com.example.lanpartyapi.entity.TablePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TablePlanRepo extends JpaRepository<TablePlan, Integer> {
    Optional<TablePlan> findByName(String name);
}
