package model.service;

import model.stadium.StadiumDAO;
import model.stadium.Stadium;

import java.sql.SQLException;
import java.util.List;

//@AllArgsConstructor
public class StadiumService {
    private StadiumDAO stadiumDAO;

    public StadiumService(StadiumDAO stadiumDAO) {
        this.stadiumDAO = stadiumDAO;
    }

    // 야구장 등록
    public int registerStadium(String input) {
        try {
            // 야구장 등록 로직
            stadiumDAO.createStadium(input);
            System.out.println("야구장이 되었습니다");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void stadiumList() {
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


}