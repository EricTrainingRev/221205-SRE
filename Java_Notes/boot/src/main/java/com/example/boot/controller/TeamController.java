package com.example.boot.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.entities.Team;
import com.example.boot.exceptions.AuthenticationFailed;
import com.example.boot.exceptions.EntityNotFound;
import com.example.boot.service.TeamService;


@RestController // this tells Spring we are using this class as a controller to handle http requests & responses
public class TeamController {

    private static Logger teamLogger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @ExceptionHandler(AuthenticationFailed.class)
    public ResponseEntity<String> authenticationFailed(AuthenticationFailed e){
        teamLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        teamLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e){
        teamLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        teamLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>("could not delete team", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/team/id/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable int id){
        return new ResponseEntity<>(this.teamService.getTeamById(id), HttpStatus.OK);
    }

    @GetMapping("/api/team/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String name){
        return new ResponseEntity<>(this.teamService.getTeamByName(name), HttpStatus.OK);
    }

    @GetMapping("/api/team")
    public ResponseEntity<List<Team>> getAllTeams(){
        System.out.println("getAllTeams API method called");
        return new ResponseEntity<>(this.teamService.getAllTeams(), HttpStatus.OK);
    }

    @PostMapping("/api/team")
    public ResponseEntity<String> createTeam(@RequestBody Team team){
        return new ResponseEntity<>(this.teamService.createTeam(team), HttpStatus.CREATED);
    }

    @PatchMapping("/api/team")
    public ResponseEntity<String> updateTeam(@RequestBody Team team){
        return new ResponseEntity<>(this.teamService.updateTeam(team.getTeamName(), team.getTeamId()), HttpStatus.OK);
    }

    @DeleteMapping("/api/team/{id}")
    public ResponseEntity<String> deleteTeamById(@PathVariable int id){
        return new ResponseEntity<>(this.teamService.deleteTeam(id), HttpStatus.OK);
    }
    
}
