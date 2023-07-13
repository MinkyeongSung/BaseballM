import db.DBConnection;
import dto.PlayerDTO;
import dto.PlayerTeamDTO;
import dto.PositionResDTO;
import dto.TeamRespDTO;

import model.outplayer.OutPlayerDAO;

import model.player.PlayerDAO;
import model.service.OutPlayerService;
import model.service.PlayerService;

import model.service.StadiumService;
import model.service.TeamService;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BaseballAPP {
    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();

        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);
        PlayerDAO playerDAO = new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);

        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        System.out.println("--------------------------");
        System.out.println("1. 야구장등록 (야구장등록?name=야구장이름)");
        System.out.println("2. 야구장목록");
        System.out.println("3. 팀등록 (팀등록?stadiumId=팀번호&name=팀이름)");
        System.out.println("4. 팀목록");
//        System.out.println("5. 팀정보");
        System.out.println("5. 선수등록 (선수등록?teamId=팀번호&name=선수이름&position=포지션)");
        System.out.println("6. 선수목록 (선수목록?teamId=1)");
        System.out.println("7. 선수 퇴출 등록 (퇴출등록?playerId=플레이어번호&reason=사유)");
        System.out.println("8. 퇴출목록");
        System.out.println("9. 포지션별목록");
        System.out.println("0. 종료");
        System.out.println("--------------------------");
        String input = scanner.nextLine();


        // 1 야구장 등록
        if (input.startsWith("야구장등록")) {
            String name = input.substring(input.indexOf('?') + 1);

            // StadiumService의 야구장등록() 메서드 호출
            StadiumService stadiumService = new StadiumService(stadiumDAO);
            stadiumService.registerStadium(name);
        }

        // 2. 야구장 목록

        if (input.equals("야구장목록")) {
            StadiumService stadiumService = new StadiumService(stadiumDAO);
            stadiumService.stadiumList();
        }


        // 3. 팀 등록

        if (input.startsWith("팀등록")) {
            String paramsString = input.substring(input.indexOf('?') + 1);

            // TeamService의 팀등록() 메서드 호출
            TeamService teamService = new TeamService(teamDAO);
            teamService.registerTeam(paramsString);
        }


        // 4. 팀 목록
        if (input.equals("팀목록")) {
            TeamService teamService = new TeamService(teamDAO);
            teamService.teamList();
        }

        // 5. 선수등록
        if (input.startsWith("선수등록")) {
            String paramsString = input.substring(input.indexOf('?') + 1);

            // TeamService의 팀등록() 메서드 호출
            PlayerService playerService = new PlayerService(playerDAO);
            playerService.registerPlayer(paramsString);
        }

        // 6. 팀 별 선수목록    요청 : 선수목록?teamId=1
        if (input.startsWith("선수목록")) {
            String name = input.substring(input.indexOf('?') + 1);

            // PlayerService의 선수목록() 메서드 호출
            PlayerService playerService = new PlayerService(playerDAO);
            playerService.playerListByTeamId(name);
        }

        // 7. 선수 퇴출 등록
        if (input.startsWith("퇴출등록")) {
            String paramsString = input.substring(input.indexOf('?') + 1);

            // OutPlayerService의 퇴출등록() 메서드 호출
            OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
            outPlayerService.registerOutPlayer(paramsString);
        }

        // 8. 퇴출목록
        if (input.equals("퇴출목록")) {
            OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
            outPlayerService.outPlayerList();
        }
        // 9. 포지션별목록
        if (input.equals("포지션별목록")) {
            try {
                List<PositionResDTO> positionList = playerDAO.positionList();

                for (PositionResDTO position : positionList) {
                    System.out.println(position);
                }

            } catch (Exception e) {
                System.out.println("포지션별목록 조회 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        }

    }
}



//        팀정보 (하나만)
//        if (input.equals("팀정보")) {
//            System.out.println("팀 ID를 입력하세요: ");
//            int teamId = scanner.nextInt();
//            scanner.nextLine();
//
//            TeamRespDTO team = teamDAO.getTeam(teamId);
//
//            if (team != null) {
//                System.out.printf("팀 이름: %s (%s)%n", team.getTeamName(), team.getStadium());
//                System.out.println(team.getTeamCreatedAt());
//                System.out.println("-------------------------------");
//            } else {
//                System.out.println("팀을 찾을 수 없습니다.");
//            }
//        }