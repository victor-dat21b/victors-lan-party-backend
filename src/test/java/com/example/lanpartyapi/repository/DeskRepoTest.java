package com.example.lanpartyapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.lanpartyapi.entity.Desk;
import com.example.lanpartyapi.repository.DeskRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class DeskRepoTest {
    static int deskId1;
    static int deskId2;

    static int deskSize;
    public static DeskRepo tR;

    @BeforeAll
    static void beforeEachTest(@Autowired DeskRepo deskRepo){
        tR = deskRepo;
        tR.deleteAll();

        Desk desk = new Desk();
        Desk desk2 = new Desk();
        deskId1 = tR.save(desk).getDesk_id();
        deskSize++;
        deskId2 = tR.save(desk2).getDesk_id();
        deskSize++;


    }


    @Test
    void findDesks(){
        List<Desk> desks = tR.findAll();

        desks.forEach(desk -> System.out.println(desk.getDesk_id()));


        assertEquals(deskId1, desks.get(0).getDesk_id());
        assertEquals(deskId2, desks.get(1).getDesk_id());
        assertEquals(deskSize, desks.size());
    }

}
