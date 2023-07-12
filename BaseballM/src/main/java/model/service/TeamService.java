
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
public class TeamService {
    private TeamDAO teamDAO;
    private Connection connection;
    public TeamService(TeamDAO teamDAO, Connection connection) {
        this.teamDAO = teamDAO;
        this.connection = connection;
    }

    // 팀 등록
    public int registerTeam(int stadiumId, String teamName) {
        try {
            teamDAO.createTeam(stadiumId, teamName);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}