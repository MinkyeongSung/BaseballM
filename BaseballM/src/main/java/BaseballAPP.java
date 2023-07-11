import db.DBConnection;
import dto.PlayerDTO;
import dto.PlayerTeamDTO;
import model.outplayer.OutPlayer;
import model.outplayer.OutPlayerDAO;
import model.player.Player;
import model.player.PlayerDAO;
import model.team.Team;

import java.sql.*;
import java.util.List;

public class BaseballAPP {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        PlayerDAO playerDAO = new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);


        try {
            int playerIdx = 3;
//            아웃플레이어 인서트 플레이어 업데이트 성공
//            OutPlayer outPlayer = outPlayerDAO.outplayerInsert(playerIdx,"음주");
//            playerDAO.playerUpdate(playerIdx);


//            데이터 삽입
//            Player player = playerDAO.playerInsert(2,"한화","내야수");

//            전체 데이터 불러오기
//            List<Player> player = playerDAO.playerFindByAll();
//            for (int i = 0; i < player.size(); i++) {
//                System.out.println(player.get(i));
//            }

//            팀번호로 전체 불러오기
//            List<Player> players = playerDAO.playerFindByTeamId()



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
