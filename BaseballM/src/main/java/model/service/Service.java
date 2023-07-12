package model.service;

import lombok.AllArgsConstructor;

import model.outplayer.OutPlayerDAO;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;

@AllArgsConstructor
public class Service {
    private Connection connection;
    private StadiumDAO stadiumDAO;
    private TeamDAO teamDAO;
    private PlayerDAO playerDAO;
    private OutPlayerDAO outPlayerDAO;
    public String processInput(String input) {
        return input;
    }
}
