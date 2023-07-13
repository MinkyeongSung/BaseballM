
package model.service;

import dto.OutPlayerRespDTO;
import model.outplayer.OutPlayerDAO;

import model.player.PlayerDAO;

import java.time.LocalDate;
import java.util.List;

//@AllArgsConstructor
public class OutPlayerService {
    private OutPlayerDAO outPlayerDAO;
    private PlayerDAO playerDAO;

    public OutPlayerService(OutPlayerDAO outPlayerDAO, PlayerDAO playerDAO) {
        this.outPlayerDAO = outPlayerDAO;
        this.playerDAO = playerDAO;
    }

    // 선수 퇴출 등록
    public int registerOutPlayer(int playerId,String reason) {
        try {
            // 입력값 파싱

            // 선수 퇴출 로직
            outPlayerDAO.outplayerInsert(playerId, reason);

            // 선수 정보 업데이트
            playerDAO.playerUpdate(playerId);

            System.out.println("선수 퇴출 성공");
            return 1;
        } catch (Exception e) {
            System.out.println("선수 퇴출 실패");
            e.printStackTrace();
            return -1;

        }
    }

    public void outPlayerList() {
        try {
            List<OutPlayerRespDTO> outPlayerList = outPlayerDAO.outplayerFindByAll();
            System.out.println("p.id p.name p.position o.reason(사유) o.day(퇴출일)");
            for (OutPlayerRespDTO outPlayer : outPlayerList) {

                System.out.printf(" " + outPlayer.getPlayerIdx() + "   ");
                System.out.printf("%s", outPlayer.getPlayerName());
                System.out.printf("    %s", outPlayer.getPosition());
                System.out.printf("        %s", outPlayer.getReason());

                LocalDate createdAt = outPlayer.getCreatedAt().toLocalDateTime().toLocalDate();
                String formattedDate = createdAt.format(DateTimeFormatter.ISO_DATE);

                System.out.printf("     %s", formattedDate);
                System.out.println("\n----------------------");
            }

        } catch (Exception e) {
            System.out.println("퇴출된 선수 목록 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }


}

