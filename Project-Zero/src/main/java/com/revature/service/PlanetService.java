package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(){
		this.dao = new PlanetDao();
	}

	public List<Planet> getAllPlanets() {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet getPlanetByName(String owner, String planetName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet getPlanetById(String username, int planetId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Planet createPlanet(String username, Planet p) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletePlanetById(int planetId) {
		// TODO Auto-generated method stub
	}
}
