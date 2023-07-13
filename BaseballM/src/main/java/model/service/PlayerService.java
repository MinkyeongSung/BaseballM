
package model.service;

import dto.PlayerTeamDTO;
import dto.PositionRespDTO;
import model.player.PlayerDAO;
import model.team.TeamDAO;


import java.sql.SQLException;
import java.util.List;

//@AllArgsConstructor
public class PlayerService {
    private PlayerDAO playerDAO;
    private TeamDAO teamDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
        this.teamDAO = teamDAO;


    }


    public int registerPlayer(String paramsString) {
        try {
            // 입력값 파싱
            String playerName = null;
            String playerPosition = null;
            int teamId = -1;

            String[] params = paramsString.split("&");

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("teamId")) {
                    teamId = Integer.parseInt(value);
                } else if (key.equals("name")) {
                    playerName = value;
                } else if (key.equals("position")) {
                    playerPosition = value;
                }
            }
            playerDAO.playerInsert(teamId, playerName, playerPosition);

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void playerListByTeamId(String input) {
        try {
            // 입력값 파싱
            int teamId = -1;
            String[] params = input.split("&");

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("teamId")) {
                    teamId = Integer.parseInt(value);
                }
            }

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


    public void callpotisionlist() {
        try {
            List<PositionRespDTO> positionList = playerDAO.positionList();

            for (PositionRespDTO dto : positionList) {
                System.out.println("포지션: " + dto.getPosition());
                System.out.println("롯데 선수: " + dto.getPlayerName1());
                System.out.println("LG 선수: " + dto.getPlayerName2());
                System.out.println("NC 선수: " + dto.getPlayerName3());
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스에서 포지션별 선수 목록을 가져오는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}


