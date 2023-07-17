package model.service;

import db.DBConnection;
import lombok.Getter;
import lombok.Setter;
import model.outplayer.OutPlayerDAO;
import model.player.PlayerDAO;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Service {
    private Connection connection;
    private StadiumDAO stadiumDAO;
    private TeamDAO teamDAO;
    private PlayerDAO playerDAO;
    private OutPlayerDAO outPlayerDAO;
    private StadiumService stadiumService;
    private TeamService teamService;
    private PlayerService playerService;
    private OutPlayerService outPlayerService;
    private PrintOut printOut;

    Scanner scanner = new Scanner(System.in);


    public Service(Connection connection) {
        this.connection = connection;
        this.stadiumDAO = new StadiumDAO(connection);
        this.teamDAO = new TeamDAO(connection);
        this.playerDAO = new PlayerDAO(connection);
        this.outPlayerDAO = new OutPlayerDAO(connection);
        this.stadiumService = new StadiumService(stadiumDAO);
        this.teamService = new TeamService(teamDAO);
        this.playerService = new PlayerService(playerDAO);
        this.outPlayerService = new OutPlayerService(outPlayerDAO, playerDAO);
        this.printOut = new PrintOut();

    }

    public void run(String input) {
        while (true) {
            Parse parse = new Parse();
            NoneParse noneParse = new NoneParse(stadiumService, teamService, playerService, outPlayerService);

            try {
                if (input.contains("?")) {
                    parse.parsing(input);
                } else {
                    parse.setAction(input);
                }
                // 이후 코드 생략
            } catch (Exception e) {
                System.err.println("오류가 발생했습니다.");
                e.printStackTrace();
            }
            if (parse.getAction().equals("1") || parse.getAction().equals("야구장등록")) {
                if (parse.getList() != null) {
                    parse.parsingByName(parse.getList());
                    String name = parse.getName();
                    stadiumService.registerStadium(name);
                } else {
                    noneParse.noneParsingByName();
                }

            } else if (parse.getAction().equals("2") || parse.getAction().equals("야구장목록")) {
                stadiumService.stadiumList();
            } else if (parse.getAction().equals("3") || parse.getAction().equals("팀등록")) {
                if (parse.getList() != null) {
                    parse.parsingByIdAndName(parse.getList());
                    int id = parse.getId();
                    String name = parse.getName();
                    teamService.registerTeam(id, name);
                } else {
                    noneParse.noneParsingByIdAndName();
                }
            } else if (parse.getAction().equals("4") || parse.getAction().equals("팀목록")) {
                teamService.teamList();
            } else if (parse.getAction().equals("5") || parse.getAction().equals("선수등록")) {
                if (parse.getList() != null) {
                    parse.parsingByIdAndNameAndPosition(parse.getList());
                    int id = parse.getId();
                    String name = parse.getName();
                    String position = parse.getPosition();
                    playerService.registerPlayer(id, name, position);
                } else {
                    noneParse.noneparsingByIdAndNameAndPosition();
                }
            } else if (parse.getAction().equals("6") || parse.getAction().equals("선수목록")) {
                if (parse.getList() != null) {
                    parse.parsingById(parse.getList());
                    int id = parse.getId();
                    playerService.playerListByTeamId(id);
                } else {
                    noneParse.noneparsingById();
                }
            } else if (parse.getAction().equals("7") || parse.getAction().equals("퇴출등록")) {
                if (parse.getList() != null) {
                    parse.parsingByIdAndReason(parse.getList());
                    int id = parse.getId();
                    String reason = parse.getReason();
                    outPlayerService.registerOutPlayer(id, reason);
                } else {
                    noneParse.noneparsingByIdAndReason();
                }
            } else if (parse.getAction().equals("8") || parse.getAction().equals("퇴출목록")) {
                outPlayerService.outPlayerList();
            } else if (parse.getAction().equals("9") || parse.getAction().equals("포지션별목록")) {
                playerService.callpotisionlist();
            } else if (parse.getAction().equals("0") || parse.getAction().equals("종료")) {
                System.out.println("종료합니다");
                break;
            } else {
                System.out.println("입력한 값이 이상합니다. 확인 바랍니다.");
            }
            printOut.mainPrint();
            input = scanner.nextLine();
        }
    }
}

