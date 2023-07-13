
package model.service;

import dto.PlayerTeamDTO;
import dto.TeamRespDTO;
import model.player.PlayerDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.util.List;

//@AllArgsConstructor
public class PlayerService {
    private PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;

    }


    public int registerPlayer(int teamId ,String playerName , String playerPosition) {
        try {
            // 입력값 파싱
            playerDAO.playerInsert(teamId, playerName,playerPosition);
            System.out.println("선수가 등록되었습니다");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void playerListByTeamId(int teamId) {
        try {
            // 선수 목록 조회 로직
            List<PlayerTeamDTO> playerList = playerDAO.playerFindByTeamId(teamId);


            // 선수 목록 출력
            for (PlayerTeamDTO player : playerList) {
                System.out.println("선수 이름: " + player.getPlayerName());
                System.out.println("포지션: " + player.getPosition());
                System.out.println("등록일: " + player.getCreatedAt());
                System.out.println("----------------------");
            }
        } catch (Exception e) {
            System.out.println("선수 목록 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

}
