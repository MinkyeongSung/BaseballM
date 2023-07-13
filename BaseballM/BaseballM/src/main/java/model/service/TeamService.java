package model.service;

import dto.TeamRespDTO;

import model.team.TeamDAO;

import java.util.List;


public class TeamService {
    private TeamDAO teamDAO;

    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;

    }

    // 팀 등록
    public int registerTeam(String teamName, int teamIdx) {
        try {

            // 팀 등록 로직
            teamDAO.createTeam(teamIdx, teamName);


            return 1; // 팀 등록 성공 시 1 반환
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 팀 등록 실패 시 -1 반환
        }
    }

    // 팀 목록
    public void teamList() {
        try {
            List<TeamRespDTO> teamList = teamDAO.getTeamList();

            for (TeamRespDTO team : teamList) {
                System.out.printf("팀 이름: %s", team.getTeamName());
                System.out.printf("(%s)", team.getStadium());
                System.out.println(team.getTeamCreatedAt());
            }

        } catch (Exception e) {
            System.out.println("팀 목록 조회 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

}