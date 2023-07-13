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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseballAPP {
    public static void main(String[] args) {

        Connection connection = DBConnection.getInstance();

        StadiumDAO stadiumDAO = new StadiumDAO(connection);
        TeamDAO teamDAO = new TeamDAO(connection);
        PlayerDAO playerDAO = new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);
        BaseBallDriver baseBallDriver = new BaseBallDriver();
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
        List<String> info = new ArrayList<>();
        String action = "";
        String list = "";

        if (input.indexOf('?') != -1) {
            action = input.split("\\?")[0];
            list = input.split("\\?")[1];
            if (list.indexOf("&") != -1) {
                for (int i = 0; i < list.split("&").length; i++) {
                    info.add(list.split("&")[i].split("=")[1]);

                }
            } else {
                info.add(list.split("=")[1]);
            }
        } else {
            action = input;
        }

        // 1 야구장 등록
        if (action.equals("1")||action.equals("야구장등록")) {
            String name = "";
            if (input.indexOf('?')==-1){
                System.out.println("야구장 이름을 적으시오");
                name = scanner.nextLine();
            }else{
                name = info.get(0);
            }
            // StadiumService의 야구장등록() 메서드 호출
            StadiumService stadiumService = new StadiumService(stadiumDAO);
            stadiumService.registerStadium(name);
        }

        // 2. 야구장 목록

        if (action.equals("야구장목록")||action.equals("2")) {
            StadiumService stadiumService = new StadiumService(stadiumDAO);
            stadiumService.stadiumList();
        }


        // 3. 팀 등록

        if (action.equals("팀등록")||action.equals("3")) {
            String teamname = "";
            int teamIdx = 0;
            if (input.indexOf('?')==-1){
                System.out.println("팀번호를 적으시오");
                teamIdx = scanner.nextInt();
                System.out.println("팀 이름을 적으시오");
                teamname = scanner.nextLine();
            }else{
                teamname = info.get(0);
                teamIdx = Integer.parseInt(info.get(1));
            }
            // TeamService의 팀등록() 메서드 호출
            TeamService teamService = new TeamService(teamDAO);
            teamService.registerTeam(teamname,teamIdx);
        }


        // 4. 팀 목록
        if (action.equals("팀목록")||action.equals("4")) {
            TeamService teamService = new TeamService(teamDAO);
            teamService.teamList();
        }

        // 5. 선수등록
        if (action.equals("선수등록")||action.equals("5")) {
            int teamId = 0;
            String playerName = "";
            String playerPosition = "";
            if (input.indexOf('?')==-1){
                System.out.println("팀번호를 적으시오");
                teamId = scanner.nextInt();
                System.out.println("선수이름을 적으시오");
                playerName = scanner.nextLine();
                System.out.println("포지션을 적으시오");
                playerPosition = scanner.nextLine();
            }else{
                teamId = Integer.parseInt(info.get(0));
                playerName = info.get(1);
                playerPosition = info.get(2);
            }
            // TeamService의 팀등록() 메서드 호출
            PlayerService playerService = new PlayerService(playerDAO);
            playerService.registerPlayer(teamId,playerName,playerPosition);
        }

        // 6. 팀 별 선수목록    요청 : 선수목록?teamId=1
        if (action.equals("선수목록")||action.equals("6")) {
            int teamId = 0;
            if (input.indexOf('?')==-1){
                System.out.println("팀번호를 적으시오");
                teamId = scanner.nextInt();
            }else{
                teamId = Integer.parseInt(info.get(0));
            }

            // PlayerService의 선수목록() 메서드 호출
            PlayerService playerService = new PlayerService(playerDAO);
            playerService.playerListByTeamId(teamId);
        }

        // 7. 선수 퇴출 등록
        if (action.equals("퇴출등록")||action.equals("7")) {
            int playerId = 0;
            String reason= "" ;
            if (input.indexOf('?')==-1){
                System.out.println("선수번호를 적으시오");
                playerId = scanner.nextInt();
                System.out.println("사유를 적으시오");
                reason = scanner.nextLine();
            }else{
                playerId = Integer.parseInt(info.get(0));
                reason = info.get(1);
            }

            // PlayerService의 선수목록() 메서드 호출
            PlayerService playerService = new PlayerService(playerDAO);
            playerService.playerListByTeamId(playerId);

            // OutPlayerService의 퇴출등록() 메서드 호출
            OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
            outPlayerService.registerOutPlayer(playerId,reason);
        }

        // 8. 퇴출목록
        if (action.equals("퇴출목록")||action.equals("8")) {
            OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
            outPlayerService.outPlayerList();
        }
        // 9. 포지션별목록
        if (action.equals("포지션별목록")||action.equals("9")) {
            playerService.callpotisionlist();
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