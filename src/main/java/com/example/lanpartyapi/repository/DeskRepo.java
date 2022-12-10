package com.example.lanpartyapi.repository;

import com.example.lanpartyapi.entity.Desk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DeskRepo extends JpaRepository<Desk, Integer> {


}
