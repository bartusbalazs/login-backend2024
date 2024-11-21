package com.example.nationsleague.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TournamentGroup {
    private Integer id;
    private String name;
    private List<Team> teams;
}
