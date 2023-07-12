
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
public class PlayerService {
    private PlayerDAO playerDAO;
    private Connection connection;
    private TeamDAO teamDAO;

    public PlayerService(PlayerDAO playerDAO, Connection connection) {
        this.playerDAO = playerDAO;
        this.connection = connection;
    }

    // 선수 등록
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