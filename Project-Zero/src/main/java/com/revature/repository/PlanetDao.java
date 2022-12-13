package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
	/*
	 * added the throws clause to the method signature because the alternative is to return an empty
	 * arraylist, but the method could succeed with no planets returned, so this is not an ideal solution.
	 * Instead we will let the service layer and/or API handle the exception being thrown
	 */
    public List<Planet> getAllPlanets() throws SQLException {
		try(Connection connection = ConnectionUtil.createConnection())  {
			String sql = "select * from planets";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<Planet> planets = new ArrayList<>();
			while(rs.next()){ // the resultset next method returns a boolean, so we can use it in our loop
				Planet planet = new Planet();
				planet.setId(rs.getInt(1));
				planet.setName(rs.getString(2));
				planet.setOwnerId(rs.getInt(3));
				planets.add(planet);
			}
			return planets;
		}
		
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
		try(Connection connection = ConnectionUtil.createConnection()) {
			String sql = "delete from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			System.out.println(e.getMessage()); // good spot to add some logging?
		}
	}

	public static void main(String[] args) {
		PlanetDao planetDao = new PlanetDao();
		// planetDao.deletePlanetById(6);
		try {
			System.out.println(planetDao.getAllPlanets());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
