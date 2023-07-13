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
import java.util.List;

@Getter
@Setter
public class Service {
    private Connection connection;
    private StadiumDAO stadiumDAO;
    private TeamDAO teamDAO;
    private PlayerDAO playerDAO;
    private OutPlayerDAO outPlayerDAO;

    public Service(Connection connection) {
        this.connection = connection;
        this.stadiumDAO = new StadiumDAO(connection);
        this.teamDAO = new TeamDAO(connection);
        this.playerDAO = new PlayerDAO(connection);
        this.outPlayerDAO = new OutPlayerDAO(connection);
    }

    public void staAdd(String stdiumName) {
        try {
            stadiumDAO.createStadium(stdiumName);
            System.out.println("등록되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void teamAdd(int teamStadiumIdx,String teamName) {
        try {
            teamDAO.createTeam(teamStadiumIdx,teamName);
            System.out.println("등록되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void playerAdd(int teamidx,String playerName,String playerPosition){
        try {
            playerDAO.playerInsert(teamidx,playerName,playerPosition);
            System.out.println("등록 되었습니다");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void stadiumFindByAll(){
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

