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
        System.out.println("5. 팀정보");
        System.out.println("6. 선수등록 (선수등록?teamId=팀번호&name=선수이름&position=포지션)");
        System.out.println("7. 선수목록 (선수목록?teamId=1)");
        System.out.println("8. 선수 퇴출 등록 (퇴출등록?playerId=플레이어번호&reason=사유)");
        System.out.println("9. 퇴출목록");
        System.out.println("10. 포지션별목록");
        System.out.println("0. 종료");
        System.out.println("--------------------------");
        String input = scanner.nextLine();


        // 1 야구장 등록
        if (input.startsWith("야구장등록")) {
            String[] params = input.substring(input.indexOf('?') + 1).split("&");
            String name = null;

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("name")) {
                    name = value;
                }
            }

            // Service의 야구장등록() 메서드 호출
            StadiumService stadiumService = new StadiumService(stadiumDAO);
            int result = stadiumService.registerStadium(name);

            if (result == 1) {
                System.out.println("야구장 등록 성공");

            } else {
                System.out.println("야구장 등록 실패");

            }
        }

        // 2. 야구장 목록

        // 전체 야구장 목록보기
        if (input.equals("야구장목록")) {
            try {
                List<Stadium> stadiums = stadiumDAO.getAllStadiums();

                System.out.println("등록된 야구장 목록:");
                for (Stadium stadium : stadiums) {
                    System.out.println("야구장 이름: " + stadium.getStadiumName());
                    System.out.println("등록일시: " + stadium.getStadiumCreatedAt());
                    System.out.println("-------------------------");
                }
            } catch (SQLException e) {
                System.out.println("야구장 목록 조회 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        }

        // 3. 팀 등록

        if (input.startsWith("팀등록")) {
            String[] params = input.substring(input.indexOf('?') + 1).split("&");
            int stadiumId = 0;
            String teamName = null;

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("stadiumId")) {
                    stadiumId = Integer.parseInt(value);
                } else if (key.equals("name")) {
                    teamName = value;
                }
            }

            // Service의 팀등록() 메서드 호출
            TeamService teamService = new TeamService(teamDAO, connection);
            int result = teamService.registerTeam(stadiumId, teamName);

            if (result == 1) {
                System.out.println("팀 등록 성공");
            } else {
                System.out.println("팀 등록 실패");
            }
        }

        // 4. 팀 목록
        if (input.equals("팀목록")) {
            try {
                List<TeamRespDTO> teamList = teamDAO.getTeamList();

                for (TeamRespDTO team : teamList) {
                    System.out.printf("팀 이름 : " + team.getTeamName());
                    System.out.printf("(" + team.getStadium() + ")");
                    System.out.println(team.getTeamCreatedAt());
                }

            } catch (Exception e) {
                System.out.println("팀 목록 조회 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        }

        // 5. 팀정보 (하나만)
        if (input.equals("팀정보")) {
            System.out.println("팀 ID를 입력하세요: ");
            int teamId = scanner.nextInt();
            scanner.nextLine();

            TeamRespDTO team = teamDAO.getTeam(teamId);

            if (team != null) {
                System.out.printf("팀 이름: %s (%s)%n", team.getTeamName(), team.getStadium());
                System.out.println(team.getTeamCreatedAt());
                System.out.println("-------------------------------");
            } else {
                System.out.println("팀을 찾을 수 없습니다.");
            }
        }
        // 6. 선수등록

        if (input.startsWith("선수등록")) {
            String[] params = input.substring(input.indexOf('?') + 1).split("&");
            int teamId = 0;
            String playerName = null;
            String position = null;

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("teamId")) {
                    teamId = Integer.parseInt(value);
                } else if (key.equals("name")) {
                    playerName = value;
                } else if (key.equals("position")) {
                    position = value;
                }
            }

            // Service의 선수등록() 메서드 호출
            PlayerService playerService = new PlayerService(playerDAO, connection);
            int result = playerService.registerPlayer(teamId, playerName, position);

            if (result == 1) {
                System.out.println("선수 등록 성공");
            } else {
                System.out.println("선수 등록 실패");
            }
        }

        // 7. 팀 별 선수목록
        if (input.startsWith("선수목록")) {
            String[] params = input.substring(input.indexOf('?') + 1).split("&");
            int teamId = 0;

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("teamId")) {
                    teamId = Integer.parseInt(value);
                }
            }
            TeamRespDTO team = teamDAO.getTeam(teamId);
            System.out.printf("팀 이름: %s \n", team.getTeamName());
            System.out.println("-------------------------");

            try {
                List<PlayerTeamDTO> playerList = playerDAO.playerFindByTeamId(teamId);
                for (PlayerTeamDTO player : playerList) {
                    System.out.println("선수 이름: " + player.getPlayerName());
                    System.out.println("포지션: " + player.getPosition());
                    System.out.println("-------------------------");
                }
            } catch (SQLException e) {
                System.out.println("선수 목록 조회 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        }
        // 8. 선수 퇴출 등록

        if (input.startsWith("퇴출등록")) {
            String[] params = input.substring(input.indexOf('?') + 1).split("&");
            int playerId = 0;
            String reason = null;

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("playerId")) {
                    playerId = Integer.parseInt(value);
                } else if (key.equals("reason")) {
                    reason = value;
                }
            }

            // Service의 선수퇴출() 메서드 호출
            OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO,connection);
            int result = outPlayerService.registerOutPlayer(playerId, reason);

            if (result == 1) {
                System.out.println("선수 퇴출 성공");
            } else {
                System.out.println("선수 퇴출 실패");
            }
        }

                // 9. 퇴출목록

                // 10. 포지션별목록
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
