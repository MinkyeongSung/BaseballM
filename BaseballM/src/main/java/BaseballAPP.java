import db.DBConnection;

import model.outplayer.OutPlayerDAO;

import model.player.PlayerDAO;
import model.service.*;

import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
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
        Scanner scanner = new Scanner(System.in);
        PrintOut printOut = new PrintOut();
        printOut.mainPrint();
        String input = "한템포 쉬고";

        while (true) {

            input = scanner.nextLine();

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
            if (action.equals("1") || action.equals("야구장등록")) {
                String name = "";
                if (input.indexOf('?') == -1) {
                    printOut.select();
                    int input1 = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 비우기

                    if (input1 == 1) {
                        System.out.println("야구장 이름을 적으시오");
                        name = scanner.nextLine();
                    } else if (input1 == 2) {
                        printOut.firstInfo();
                        printOut.waitForEnterKey();
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }

                    // StadiumService의 야구장등록() 메서드 호출
                    StadiumService stadiumService = new StadiumService(stadiumDAO);
                    stadiumService.registerStadium(name);
                } else {
                    name = info.get(0);
                }

            } else if
                // 2. 야구장 목록
            (action.equals("야구장목록") || action.equals("2")) {
                StadiumService stadiumService = new StadiumService(stadiumDAO);
                stadiumService.stadiumList();

            } else if
                // 3. 팀 등록
            (action.equals("팀등록") || action.equals("3")) {
                String teamname = "";
                int teamIdx = 0;
                if (input.indexOf('?') == -1) {
                    printOut.select();
                    int input1 = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 비우기

                    if (input1 == 1) {
                        System.out.println("팀번호를 적으시오");
                        teamIdx = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 비우기

                        System.out.println("팀 이름을 적으시오");
                        teamname = scanner.nextLine();
                    } else if (input1 == 2) {
                        printOut.thridInfo();
                        printOut.waitForEnterKey();
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                } else {
                    teamname = info.get(0);
                    teamIdx = Integer.parseInt(info.get(1));
                }
                // TeamService의 팀등록() 메서드 호출
                TeamService teamService = new TeamService(teamDAO);
                teamService.registerTeam(teamname, teamIdx);

            } else if
                // 4. 팀 목록
            (action.equals("팀목록") || action.equals("4")) {
                TeamService teamService = new TeamService(teamDAO);
                teamService.teamList();

            } else if

                // 5. 선수등록
            (action.equals("선수등록") || action.equals("5")) {
                int teamId = 0;
                String playerName = "";
                String playerPosition = "";
                if (input.indexOf('?') == -1) {
                    printOut.select();
                    int input1 = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 비우기

                    if (input1 == 1) {
                        System.out.println("팀번호를 적으시오");
                        teamId = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 비우기

                        System.out.println("선수이름을 적으시오");
                        playerName = scanner.nextLine();


                        System.out.println("포지션을 적으시오");
                        playerPosition = scanner.nextLine();
                    } else if (input1 == 2) {
                        printOut.fifthInfo();
                        printOut.waitForEnterKey();
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                } else {
                    teamId = Integer.parseInt(info.get(0));
                    playerName = info.get(1);
                    playerPosition = info.get(2);
                }
                // TeamService의 팀등록() 메서드 호출
                PlayerService playerService = new PlayerService(playerDAO);
                playerService.registerPlayer(teamId, playerName, playerPosition);

            } else if

                // 6. 팀 별 선수목록    요청 : 선수목록?teamId=1
            (action.equals("선수목록") || action.equals("6")) {
                int teamId = 0;
                if (input.indexOf('?') == -1) {
                    printOut.select();
                    int input1 = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 비우기

                    if (input1 == 1) {
                        System.out.println("팀번호를 적으시오");
                        teamId = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 비우기

                    } else if (input1 == 2) {
                        printOut.sixthInfo();
                        printOut.waitForEnterKey();
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                } else {
                    teamId = Integer.parseInt(info.get(0));
                }

                // PlayerService의 선수목록() 메서드 호출
                PlayerService playerService = new PlayerService(playerDAO);
                playerService.playerListByTeamId(teamId);

            } else if

                // 7. 선수 퇴출 등록
            (action.equals("퇴출등록") || action.equals("7")) {
                int playerId = 0;
                String reason = "";
                if (input.indexOf('?') == -1) {
                    printOut.select();
                    int input1 = scanner.nextInt();
                    scanner.nextLine(); // 버퍼 비우기

                    if (input1 == 1) {
                        System.out.println("선수번호를 적으시오");
                        playerId = scanner.nextInt();
                        scanner.nextLine(); // 버퍼 비우기
                        System.out.println("사유를 적으시오");
                        reason = scanner.nextLine();
                    } else if (input1 == 2) {
                        printOut.seventhInfo();
                        printOut.waitForEnterKey();
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                } else {
                    playerId = Integer.parseInt(info.get(0));
                    reason = info.get(1);
                }

                // PlayerService의 선수목록() 메서드 호출
                PlayerService playerService = new PlayerService(playerDAO);
                playerService.playerListByTeamId(playerId);

                // OutPlayerService의 퇴출등록() 메서드 호출
                OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
                outPlayerService.registerOutPlayer(playerId, reason);

            } else if

                // 8. 퇴출목록
            (action.equals("퇴출목록") || action.equals("8")) {
                OutPlayerService outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
                outPlayerService.outPlayerList();

            } else if
                // 9. 포지션별목록
            (action.equals("포지션별목록") || action.equals("9")) {
                PlayerService playerService = new PlayerService(playerDAO);
                playerService.callpotisionlist();

            } else if (action.equals("종료") || action.equals("0")) {
                System.out.println("종료합니다");
                break;
            } else {
                System.out.println("입력한 값이 이상합니다. 확인 바랍니다.");
            }
            printOut.mainPrint();
        }
    }
}