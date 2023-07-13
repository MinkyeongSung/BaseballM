//package dto;
//
//import db.DBConnection;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import model.player.Player;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@Builder
//public class PositionRespDTO {
//
//    private String position;
//    private String playerName1;
//    private String playerName2;
//    private String playerName3;
//    private Connection connection;
//    // 생성자, 게터, 세터 등 필요한 코드들...
//
//    public PositionRespDTO(Connection connection) {
//        connection = DBConnection.getInstance();
//    }
//
//    public void getPositionResp() {
//        List<PositionRespDTO> positionResps = new ArrayList<PositionRespDTO>();
//        String query = "select \n" +
//                "pr.position '포지션',\n" +
//                "MAX(if(tm.id = 1, pr.name, null)) '롯데',\n" +
//                "MAX(if(tm.id = 2, pr.name, null)) 'SK',\n" +
//                "MAX(if(tm.id = 3, pr.name, null)) 'NC'\n" +
//                "from player pr\n" +
//                "left outer join team tm on pr.team_id = tm.id\n" +
//                "group by pr.position";
//        try (
//                PreparedStatement statement = connection.prepareStatement(query)) {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    PositionRespDTO positionRespDto = buildPositionRespsFromResultSet(resultSet);
//                    positionResps.add(positionRespDto);
//                }
//            }
//        } catch (
//                SQLException e) {
//            System.out.println("SQL을 적용하는데 문제가 발생했습니다.");
//            e.printStackTrace();
//        }
//        System.out.println("---------- 포지션별 팀 선수 -------------------");
//        System.out.println("포지션   롯데   SK   NC");
//        for (PositionRespDto positionRespDto : positionResps) {
//            System.out.println(
//                    positionRespDto.getPosition() + ": "
//                            + positionRespDto.getTeam1() + ", "
//                            + positionRespDto.getTeam2() + ", "
//                            + positionRespDto.getTeam3());
//        }
//    }
///*
//if (input.equals("1")||input.equals("야구장등록")) {
//            String name = "";
//            if (input.indexOf('?')==-1){
//                System.out.println("야구장 이름을 적으시오");
//                name = scanner.nextLine();
//            }else{
//                name = input.substring(input.indexOf('?') + 1);
//            }
//            // StadiumService의 야구장등록() 메서드 호출
//            StadiumService stadiumService = new StadiumService(stadiumDAO);
//            stadiumService.registerStadium(name);
//        }
// */
//
//
//    private Player buildPositionRespsFromResultSet(ResultSet resultSet) throws SQLException {
//        String playerPosition = resultSet.getString("포지션");
//        String playerName1 = resultSet.getString("롯데");
//        String playerName2 = resultSet.getString("LG");
//        String playerName3 = resultSet.getString("NC");
//
//        return Player.builder()
//                .playerPosition(playerPosition)
//                .playerName(playerName1)
//                .playerName2(playerName2)
//                .playerName3(playerName3)
//                .build();
//    }
//    public static List<PositionResDTO> positionList(PlayerDAO playerDAO) throws SQLException {
//
//        List<PositionResDTO> dtos = new ArrayList<>();
//        String query = "SELECT\n" +
//                "    p.position,\n" +
//                "    MAX(IF(t.stadium_idx = 1, p.name, NULL)) 'lotte',\n" +
//                "    MAX(IF(t.stadium_idx = 2, p.name, NULL)) 'hanhwa',\n" +
//                "    MAX(IF(t.stadium_idx = 3, p.name, NULL)) 'samsung'\n" +
//                "FROM\n" +
//                "    player p\n" +
//                "    INNER JOIN team t ON p.team_idx = t.idx\n" +
//                "GROUP BY\n" +
//                "    p.position;";
//
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            try (ResultSet rs = statement.executeQuery()) {
//                while (rs.next()) {
//                    PositionResDTO dto = PositionResDTO.builder()
//                            .playerName1(rs.getString("lotte"))
//                            .playerName2(rs.getString("hanhwa"))
//                            .playerName3(rs.getString("samsung"))
//                            .position(rs.getString("position"))
//                            .build();
//                    dtos.add(dto);
//                }
//
//            }
//        }
//
//        return dtos;
//    }
//
//}


package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class PositionRespDTO {
    private int playerIdx;
    private int teamIdx;
    private String playerTeamName;
    private String playerName1;
    private String playerName2;
    private String playerName3;
    private String position;
}