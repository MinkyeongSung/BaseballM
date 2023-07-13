package model.service;

import anno.Controller;
import anno.RequestMapping;
import db.DBConnection;
import lombok.Getter;
import lombok.Setter;
import model.outplayer.OutPlayerDAO;
import model.player.PlayerDAO;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;
import model.team.TeamDAO;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Getter
@Setter
public class Service {
    private Connection connection;
    private StadiumDAO stadiumDAO;
    private TeamDAO teamDAO ;
    private PlayerDAO playerDAO ;
    private OutPlayerDAO outPlayerDAO ;
    private PrintOut printOut;
    Scanner scanner = new Scanner(System.in);

    public Service(Connection connection) {
        this.connection = connection;
        this.stadiumDAO = new StadiumDAO(connection);
        this.teamDAO = new TeamDAO(connection);
        this.playerDAO = new PlayerDAO(connection);
        this.outPlayerDAO = new OutPlayerDAO(connection);
    }


}

