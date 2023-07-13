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
            // 입력값 파싱
            String name = null;
            String[] params = input.split("&");

            for (String param : params) {
                String[] keyValue = param.split("=");
                String key = keyValue[0];
                String value = keyValue[1];

                if (key.equals("name")) {
                    name = value;
                }
            }

            // 야구장 등록 로직
            stadiumDAO.createStadium(name);
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