package com.example.nationsleague.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team {
    private Integer id;
    private String name;
    private Double rankingPoints;
    private String flagPath;
}
