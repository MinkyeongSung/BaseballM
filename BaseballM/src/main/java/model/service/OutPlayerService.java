
package model.service;

import model.outplayer.OutPlayerDAO;

import model.player.PlayerDAO;

//@AllArgsConstructor
public class OutPlayerService {
    private OutPlayerDAO outPlayerDAO;
    private PlayerDAO playerDAO;

    public OutPlayerService(OutPlayerDAO outPlayerDAO, PlayerDAO playerDAO) {
        this.outPlayerDAO = outPlayerDAO;
        this.playerDAO = playerDAO;
    }

    // 선수 퇴출 등록
    public int registerOutPlayer(String paramsString) {
        try {
            // 입력값 파싱
            int playerId = 0;
            String reason = null;

            String[] params = paramsString.split("&");

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
    }
}

