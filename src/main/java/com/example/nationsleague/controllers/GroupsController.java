package com.example.nationsleague.controllers;

import com.example.nationsleague.domain.Team;
import com.example.nationsleague.domain.TournamentGroup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupsController {

    @GetMapping("/api/groups")
    public List<TournamentGroup> getTeams() {
        return List.of(
                new TournamentGroup(0, "Group A", List.of(
                    new Team(0, "Poland", Double.valueOf("1523.53"), "https://www.worldometers.info/img/flags/pl-flag.gif"),
                    new Team(1, "Portugal", Double.valueOf("1741.43"), "https://www.worldometers.info/img/flags/po-flag.gif"),
                    new Team(2, "Croatia", Double.valueOf("1701.31"), "https://www.worldometers.info/img/flags/hr-flag.gif"),
                    new Team(3, "Scotland", Double.valueOf("1472.3"), "https://flagpedia.net/data/flags/h80/gb-sct.webp")
                )),
                new TournamentGroup(1, "Group B", List.of(
                    new Team(4, "France", Double.valueOf("1854.91"), "https://www.worldometers.info/img/flags/fr-flag.gif"),
                    new Team(5, "Italy", Double.valueOf("1714.29"), "https://www.worldometers.info/img/flags/it-flag.gif"),
                    new Team(6, "Belgium", Double.valueOf("1772.44"), "https://www.worldometers.info/img/flags/be-flag.gif"),
                    new Team(7, "Israel", Double.valueOf("1312.54"), "https://www.worldometers.info/img/flags/is-flag.gif")
                )),
                new TournamentGroup(2, "Group C", List.of(
                    new Team(8, "Germany", Double.valueOf("1686.02"), "https://www.worldometers.info/img/flags/gm-flag.gif"),
                    new Team(9, "Netherlands", Double.valueOf("1758.51"), "https://www.worldometers.info/img/flags/nl-flag.gif"),
                    new Team(10, "Hungary", Double.valueOf("1517.77"), "https://www.worldometers.info/img/flags/hu-flag.gif"),
                    new Team(11, "Bosnia and Herzegovina", Double.valueOf("1332.3"), "https://www.worldometers.info/img/flags/bk-flag.gif")
                )),
                new TournamentGroup(3, "Group D", List.of(
                    new Team(12, "Switzerland", Double.valueOf("1654.1"), "https://www.worldometers.info/img/flags/sz-flag.gif"),
                    new Team(13, "Spain", Double.valueOf("1835.67"), "https://www.worldometers.info/img/flags/sp-flag.gif"),
                    new Team(14, "Denmark", Double.valueOf("1607.52"), "https://www.worldometers.info/img/flags/da-flag.gif"),
                    new Team(15, "Serbia", Double.valueOf("1506.9"), "https://www.worldometers.info/img/flags/ri-flag.gif")
                ))
        );
    }

}
