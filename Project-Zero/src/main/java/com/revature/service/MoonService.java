package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(){
		this.dao = new MoonDao();
	}

	public List<Moon> getAllMoons() {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon getMoonByName(String username, String moonName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon getMoonById(String username, int moonId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Moon createMoon(String username, Moon m) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteMoonById(int moonId) {
		// TODO Auto-generated method stub
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		// TODO Auto-generated method stub
		return null;
	}
}
