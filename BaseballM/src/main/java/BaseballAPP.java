import db.DBConnection;
import dto.PlayerDTO;
import model.outplayer.OutPlayerDAO;
import model.player.Player;
import model.player.PlayerDAO;

import java.sql.*;
import java.util.List;

public class BaseballAPP {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();

        PlayerDAO playerDAO = new PlayerDAO(connection);
        OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);


        try {
            int playerIdx = 1;
//            데이터 삽입
//            Player player = playerDAO.playerInsert(2,"한화","내야수");
            List<Player> player = playerDAO.playerFindByAll();
            for (int i = 0; i < player.size(); i++) {
                System.out.println(player.get(i));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
