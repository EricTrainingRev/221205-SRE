package com.example.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.entities.Team;
import com.example.boot.exceptions.EntityNotFound;
import com.example.boot.repository.TeamDao;

@Service // tells Spring this class handles service layer operations
public class TeamService {

    @Autowired // tells Spring to instantiate the field when a TeamService object is created
    private TeamDao teamDao;

    public Team getTeamById(int id){
        Optional<Team> possibleTeam = this.teamDao.findById(id); // findById is provided by the JPA repository
        if(possibleTeam.isPresent()){ // is present returns true of the object we want was returned in the Optional
            return possibleTeam.get(); // get() returns the object we want if it is present
        } else {
            throw new EntityNotFound("Team not found");
        }
    }

    public Team getTeamByName(String name){
        Optional<Team> possibleTeam = this.teamDao.findByTeamName(name);
        if(possibleTeam.isPresent()){
            return possibleTeam.get();
        } else {
            throw new EntityNotFound("Team not found");
        }
    }

    public List<Team> getAllTeams(){
        List<Team> teams = this.teamDao.findAll();
        if(teams.size() != 0){
            return teams;
        } else {
            throw new EntityNotFound("No teams were found");
        }
    }

    public String createTeam(Team team){
        this.teamDao.createTeam(team.getTeamName());
        return "Team created";
    }

    public String updateTeam(String teamName, int id){
        int result = this.teamDao.updateTeam(teamName, id);
        if (result == 1){
            return "team updated successfully";
        } else {
            throw new EntityNotFound("Team not found");
        }
    }

    public String deleteTeam(int id){
        this.teamDao.deleteById(id);
        return "Deleted team with id of " + id;
    }
    
}
