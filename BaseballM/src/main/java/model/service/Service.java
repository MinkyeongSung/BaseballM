package model.service;

import lombok.AllArgsConstructor;

import model.outplayer.OutPlayerDAO;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class Service {
//    private Connection connection;
    private StadiumDAO stadiumDAO;
//    private TeamDAO teamDAO;
//    private PlayerDAO playerDAO;
//    private OutPlayerDAO outPlayerDAO;

    public int registerStadium(String name) {
        try {

            stadiumDAO.createStadium(name);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
