
package model.service;

import dto.TeamRespDTO;
import lombok.AllArgsConstructor;

import model.outplayer.OutPlayerDAO;
import model.player.Player;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

//@AllArgsConstructor
public class OutPlayerService {
    private OutPlayerDAO outPlayerDAO;
    private Connection connection;
    private PlayerDAO playerDAO;

    public OutPlayerService(OutPlayerDAO outPlayerDAO, Connection connection) {
        this.outPlayerDAO = outPlayerDAO;
        this.connection = connection;
    }

    // 선수 퇴출 등록
    public int registerOutPlayer(int playerId, String reason) {
        try {
            connection.setAutoCommit(false);

            outPlayerDAO.outplayerInsert(playerId, reason);

            playerDAO.playerUpdate(playerId);

            connection.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return -1;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}