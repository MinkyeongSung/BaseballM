
package model.service;

import dto.TeamRespDTO;
import lombok.AllArgsConstructor;

import model.outplayer.OutPlayerDAO;
import model.player.Player;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.SQLException;

//@AllArgsConstructor
public class Service {
    private StadiumDAO stadiumDAO;
    private TeamDAO teamDAO;
    private PlayerDAO playerDAO;

    public Service(StadiumDAO stadiumDAO, TeamDAO teamDAO, PlayerDAO playerDAO) {
        this.stadiumDAO = stadiumDAO;
        this.teamDAO = teamDAO;
        this.playerDAO = playerDAO;
    }
    public Service(StadiumDAO stadiumDAO) {
        this.stadiumDAO = stadiumDAO;
    }

    public int registerStadium(String name) {
        try {

            stadiumDAO.createStadium(name);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int registerTeam(int stadiumId, String teamName) {
        try {
            teamDAO.createTeam(stadiumId, teamName);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int registerPlayer(int teamId, String playerName, String position) {
        try {
            // 팀 정보 가져오기
            TeamRespDTO team = teamDAO.getTeam(teamId);

            if (team != null) {
                // 선수 등록
                Player player = playerDAO.playerInsert(teamId, playerName, position);

                if (player != null) {
                    return 1; // 등록 성공
                } else {
                    return 0; // 등록 실패
                }
            } else {
                System.out.println("팀을 찾을 수 없습니다.");
                return 0; // 등록 실패
            }
        } catch (SQLException e) {
            System.out.println("선수 등록 중 오류가 발생했습니다.");
            e.printStackTrace();
            return 0; // 등록 실패
        }

    }
}