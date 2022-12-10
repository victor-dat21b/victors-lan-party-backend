package com.example.lanpartyapi.dto;

import com.example.lanpartyapi.entity.TablePlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TablePlanResponse {

    int tableplan_id;
    String name;

    public TablePlanResponse(TablePlan t) {

        this.tableplan_id = t.getTableplan_id();
        this.name = t.getName();


    }


}
