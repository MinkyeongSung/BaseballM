package model.service;

import lombok.*;

import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class NoneParse {
    private String list;
    private int input1;
    private PrintOut printOut;
    private Scanner scanner;
    private StadiumService stadiumService;
    private TeamService teamService;
    private PlayerService playerService;
    private OutPlayerService outPlayerService;
    private int teamIdx;
    private String teamName;
    private int teamId;
    private String playerName;
    private String playerPosition;
    private int id;
    private String reason;


    public NoneParse(StadiumService stadiumService, TeamService teamService, PlayerService playerService, OutPlayerService outPlayerService) {
        this.printOut = new PrintOut();
        this.scanner = new Scanner(System.in);
        this.stadiumService = stadiumService;
        this.teamService = teamService;
        this.playerService = playerService;
        this.outPlayerService = outPlayerService;


    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }



    public void noneParsingByName() {
        printOut.select1();
        input1 = Integer.parseInt(scanner.nextLine());  // 정수 입력 후 버퍼 비움

        if (input1 == 1) {
            System.out.println("야구장 이름을 적으시오");
            String name = scanner.nextLine();
            stadiumService.registerStadium(name);
        } else if (input1 == 2) {
            printOut.firstInfo();
            printOut.waitForEnterKey();
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

    public void noneParsingByIdAndName() {
        printOut.select1();
        input1 = Integer.parseInt(scanner.nextLine());  // 정수 입력 후 버퍼 비움

        if (input1 == 1) {
            System.out.println("팀번호를 적으시오");
            teamIdx = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            System.out.println("팀 이름을 적으시오");
            teamName = scanner.nextLine();

            teamService.registerTeam(teamIdx, teamName);
        } else if (input1 == 2) {
            printOut.thridInfo();
            printOut.waitForEnterKey();
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

    public void noneparsingByIdAndNameAndPosition() {
        printOut.select1();
        input1 = Integer.parseInt(scanner.nextLine());  // 정수 입력 후 버퍼 비움

        if (input1 == 1) {
            System.out.println("팀번호를 적으시오");
            teamId = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            System.out.println("선수이름을 적으시오");
            playerName = scanner.nextLine();


            System.out.println("포지션을 적으시오");
            playerPosition = scanner.nextLine();

            playerService.registerPlayer(teamId, playerName, playerPosition);

        } else if (input1 == 2) {
            printOut.fifthInfo();
            printOut.waitForEnterKey();
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }

    }
    public void noneparsingById() {
        printOut.select2();
        int input1 = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (input1 == 1) {
            System.out.println("팀번호를 적으시오");
            teamId = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            playerService.playerListByTeamId(teamId);
        } else if (input1 == 2) {
            printOut.sixthInfo();
            printOut.waitForEnterKey();
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

    public void noneparsingByIdAndReason() {
        printOut.select1();
        int input1 = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (input1 == 1) {
            System.out.println("선수번호를 적으시오");
            id = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            System.out.println("사유를 적으시오");
            outPlayerService.registerOutPlayer(id, reason);

            reason = scanner.nextLine();
        } else if (input1 == 2) {
            printOut.seventhInfo();
            printOut.waitForEnterKey();
        } else {
            System.out.println("잘못 입력하셨습니다.");
        }
    }

}
