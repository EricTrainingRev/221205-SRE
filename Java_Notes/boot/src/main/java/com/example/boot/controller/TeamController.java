package com.example.boot.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.entities.Team;
import com.example.boot.service.TeamService;


@RestController // this tells Spring we are using this class as a controller to handle http requests & responses
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/team/id/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable int id){
        Team team = this.teamService.getTeamById(id); // getTeamById will return a team for us
        if(team.getTeamId() != 0){ // check to see if team has an initialized Id
            return new ResponseEntity<>(team, HttpStatus.OK); // return the team with a success message if we got data
        } else {
            return new ResponseEntity<>(team,HttpStatus.NOT_FOUND); // return an empty team with a not found status if necessary
        }
    }

    @GetMapping("/team/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String name){
        Team team = this.teamService.getTeamByName(name);
        if(team.getTeamId() != 0){ // check to see if team has an initialized Id
            return new ResponseEntity<>(team, HttpStatus.OK); // return the team with a success message if we got data
        } else {
            return new ResponseEntity<>(team,HttpStatus.NOT_FOUND); // return an empty team with a not found status if necessary
        }
    }

    @GetMapping("/team")
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> teams = this.teamService.getAllTeams();
        if(teams.size() != 0){
            return new ResponseEntity<>(teams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(teams, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping("/team")
    public ResponseEntity<String> createTeam(@RequestBody Team team){
        String message = this.teamService.createTeam(team);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PatchMapping("/team")
    public ResponseEntity<String> updateTeam(@RequestBody Team team){
        String message = this.teamService.updateTeam(team.getTeamName(), team.getTeamId());
        if(message.length() == 25){
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/team/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable int id){
        try {
            String message = this.teamService.deleteTeam(id);
            return new ResponseEntity<>(message, HttpStatus.OK);  
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("team with given id not found: please try again", HttpStatus.NOT_FOUND);  
        }
        
    }
    
}
