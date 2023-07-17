package model.service;

import java.io.IOException;

public class PrintOut {
    public void mainPrint(){
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
    }
    public void waitForEnterKey() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void select(){
        System.out.println("원하시는 항목을 선택하시오.");
        System.out.println("1.볼러오기");
        System.out.println("2.설명");
    }

    public void firstInfo(){
        System.out.println(" 해당 기능은 야구장을 등록합니다.");
        System.out.println("'1.등록'을 선택 하신 후 야구장이름을 입력하시면 등록됩니다.");
        System.out.println("또는 첫 화면에서 '야구장등록?name=야구장이름'을 입력하셔서 등록 할 수 있습니다.");
        System.out.println("Enter키를 입력하시면 메인화면으로 돌아갑니다.");
    }

    public void thridInfo(){
        System.out.println(" 해당 기능은 팀을 등록합니다.");
        System.out.println("'1.등록'을 선택 하신 후 입력하실 팀에 해당하는 구장번호");
        System.out.println("(예시: 롯데는 1(사직) LG는 2(잠실))를 입력하신 후 ");
        System.out.println("팀 이름을 적으시면 등록됩니다.");
        System.out.println("또는 첫 화면에서 '팀등록?stadiumId=팀번호&name=팀이름'을 입력하셔서 등록 할 수 있습니다.");
        System.out.println("Enter키를 입력하시면 메인화면으로 돌아갑니다.");
    }

    public void fifthInfo(){
        System.out.println(" 해당 기능은 선수를 등록합니다.");
        System.out.println("'1.등록'을 선택 하신 후 등록하실 선수의 팀 번호");
        System.out.println("(예시 : 롯데 1, LG 2, NC 3)를 입력하신 후 ");
        System.out.println("선수이름과 포지션을 입력하시면 해당선수가 등록됩니다.");
        System.out.println("또는 첫 화면에서 '선수등록?teamId=팀번호&name=선수이름&position=포지션'을 입력하셔서 등록 할 수 있습니다.");
        System.out.println("Enter키를 입력하시면 메인화면으로 돌아갑니다.");
    }

    public void sixthInfo(){
        System.out.println(" 해당 기능은 선수목록을 출력합니다.");
        System.out.println("'1.불러오기'를 선택 하신 후 팀 번호 (예시 : 롯데 1, LG 2)");
        System.out.println("를 입력하시면 해당 팀의 선수목록이 출력됩니다.");
        System.out.println("또는 첫 화면에서 '선수목록?teamId=팀 번호'를 입력하셔서 출력 할 수 있습니다.");
        System.out.println("Enter키를 입력하시면 메인화면으로 돌아갑니다.");
    }

    public void seventhInfo(){
        System.out.println(" 해당 기능은 선수를 퇴출합니다.");
        System.out.println("'1.등록'을 선택 하신 후 퇴출시킬 선수의 번호를 입력하시면 해당 선수는 퇴출됩니다.");
        System.out.println("또는 첫 화면에서 '퇴출등록?playerId=플레이어번호&reason=사유'를 입력하셔서 퇴출 할 수 있습니다.");
        System.out.println("Enter키를 입력하시면 메인화면으로 돌아갑니다.");
    }
    public void select1(){
        System.out.println("원하시는 항목을 선택하시오.");
        System.out.println("1.등록");
        System.out.println("2.설명");
    }

    public void select2(){
        System.out.println("원하시는 항목을 선택하시오.");
        System.out.println("1.불러오기");
        System.out.println("2.설명");
    }
}