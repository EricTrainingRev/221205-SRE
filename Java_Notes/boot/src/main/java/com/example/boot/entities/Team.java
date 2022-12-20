package com.example.boot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // this tells Spring the class represents data in our database
@Table(name = "teams") // this tells Spring that the table to match this class to is called "teams"
public class Team {

    @Id // tells Spring this field represents the primary key
    @Column(name = "team_id") // tells Spring to match the field with the column provided
    private int teamId;
    @Column(name = "team_name") // tells Spring to match the field with the column provided
    private String teamName;

    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Team [teamId=" + teamId + ", teamName=" + teamName + "]";
    }

    

    
    
}
