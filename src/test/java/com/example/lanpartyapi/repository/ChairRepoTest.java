package com.example.lanpartyapi.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.Desk;
import com.example.lanpartyapi.repository.ChairRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
public class ChairRepoTest {

    static int chairInt1;
    static int chairInt2;
    public static ChairRepo cR;
    static int chairCounter;

    @BeforeAll
    static void chairSetup(@Autowired ChairRepo chairRepo){
        cR = chairRepo;
        cR.deleteAll();

        Chair chair = new Chair();
        chairCounter++;
        Chair chair2 = new Chair();
        chairCounter++;
        chairInt1 = cR.save(chair).getChair_id();
        chairInt2 = cR.save(chair2).getChair_id();
    }

    @Test
    void testForChairs(){
        List<Chair> chairs = cR.findAll();
        assertEquals(chairInt1, chairs.get(0).getChair_id());
        assertEquals(chairInt2, chairs.get(1).getChair_id());
        assertEquals(chairCounter, chairs.size());
    }
}