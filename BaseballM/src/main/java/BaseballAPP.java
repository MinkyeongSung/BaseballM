import db.DBConnection;
import dto.PlayerDTO;
import dto.PlayerTeamDTO;
import dto.PositionResDTO;
import model.outplayer.OutPlayer;
import model.outplayer.OutPlayerDAO;
import model.player.Player;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;
import model.team.Team;
import model.team.TeamDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static model.service.Service.componentScan;
import static model.service.Service.findUri;

public class BaseballAPP {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        PlayerDAO playerDAO = new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);
        StadiumDAO stadiumDAO = new StadiumDAO(connection);

        String test = "선수등록?teamId=1&name=이대호&position=1루수";

//        String uri = sc.nextLine();
        String action = test.split("\\?")[0];
        String list = test.split("\\?")[1];
        List<String> info = new ArrayList<>();
        for (int i = 0; i < list.split("&").length; i++) {
            info.add(list.split("&")[i].split("=")[1]);
//            System.out.println(info.get(i));
        }



//        Set<Object> instances = componentScan("model");
//        findUri(instances, uri);
        try {
            int playerIdx = 2;
//            아웃플레이어 인서트 플레이어 업데이트 성공
//            OutPlayer outPlayer = outPlayerDAO.outplayerInsert(playerIdx,"음주");
//            playerDAO.playerUpdate(playerIdx);


//            데이터 삽입
//            Player player = playerDAO.playerInsert(3,"송재익","내야수");

//            전체 데이터 불러오기
//            List<Player> player = playerDAO.playerFindByAll();
//            for (int i = 0; i < player.size(); i++) {
//                System.out.println(player.get(i));
//            }

//            팀번호로 전체 불러오기
//            Team team = teamDAO.getTeam(playerIdx);
//            List<PlayerTeamDTO> dtos = playerDAO.playerFindByTeamId(playerIdx);

//            dtos.forEach(dto -> {
//                System.out.println(dto.getStadiumName());
//                System.out.println(dto.getTeamName());
//                System.out.println(dto.getPlayerName());
//                System.out.println(dto.getPosition());
//                System.out.println(dto.getCreatedAt());
//            });
            List<PositionResDTO> dtos = playerDAO.positionList();
            dtos.forEach(dto -> {
                System.out.print(dto.getPosition());
                System.out.print(" "+dto.getPlayerName1());
                System.out.print(" "+dto.getPlayerName2());
                System.out.print(" "+dto.getPlayerName3());
                System.out.println(" ");
            });


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
