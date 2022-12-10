package com.example.lanpartyapi.repository;

import com.example.lanpartyapi.entity.LanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanUserRepository extends JpaRepository<LanUser, String> {
}
