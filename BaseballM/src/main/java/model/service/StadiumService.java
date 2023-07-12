
package model.service;

import dto.TeamRespDTO;
import lombok.AllArgsConstructor;

import model.outplayer.OutPlayerDAO;
import model.player.Player;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

//@AllArgsConstructor
public class StadiumService {
    private StadiumDAO stadiumDAO;

    public StadiumService(StadiumDAO stadiumDAO) {
        this.stadiumDAO = stadiumDAO;
    }

    // 야구장 등록
    public int registerStadium(String name) {
        try {

            stadiumDAO.createStadium(name);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 나머지 Stadium 관련 기능들...
}