package com.example.lanpartyapi.dto;

import com.example.lanpartyapi.entity.TablePlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TablePlanRequest {
    int id;
    String name;


    public static TablePlan getTablePlanEntity(TablePlanRequest t){
        return new TablePlan(t.getName());
    }

    public TablePlanRequest(TablePlan t){
        this.name = t.getName();
        this.id =t.getTableplan_id();
    }
}
