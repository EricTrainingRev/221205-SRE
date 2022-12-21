package com.example.boot.controller;

import java.util.List;

import org.postgresql.util.PSQLException;
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

import com.example.boot.entities.Player;
import com.example.boot.exceptions.EntityNotFound;
import com.example.boot.service.PlayerService;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /*
     * Use exception handling anytime you have a potential recurring issue
     * like a non-existant entity being referenced
     */
    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> sqlIssue(PSQLException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> deleteFailed(EmptyResultDataAccessException e){
        return new ResponseEntity<>("could not delete player", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/player/id/{id}")
    public ResponseEntity<Player> findById(@PathVariable int id){
        return new ResponseEntity<>(this.playerService.findPlayerById(id),HttpStatus.OK);
    }

    @GetMapping("/player/{name}")
    public ResponseEntity<Player> findByName(@PathVariable String name){
        return new ResponseEntity<>(this.playerService.findByPlayerName(name),HttpStatus.OK);
    }

    @GetMapping("/player")
    public ResponseEntity<List<Player>> findAll(){
        return new ResponseEntity<>(this.playerService.findAllPlayers(), HttpStatus.OK);
    }

    @PostMapping("/player")
    public ResponseEntity<String> createPlayer(@RequestBody Player player){
        String message = this.playerService.createPlayer(player);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PatchMapping("/player")
    public ResponseEntity<String> updatePlayer(@RequestBody Player player){
        return new ResponseEntity<>(this.playerService.updatePlayer(player),HttpStatus.OK);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id){
        return new ResponseEntity<>(this.playerService.deletePlayerById(id), HttpStatus.OK);
    }
    
}
