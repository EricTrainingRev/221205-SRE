package com.revature.controller;

import com.revature.exceptions.NotLoggedInException;

import io.javalin.Javalin;

public class RequestMapping {
	
	private static AuthenticateController authController = new AuthenticateController();
	private static PlanetController planetController = new PlanetController();
	private static MoonController moonController = new MoonController();
	
	public static void setupEndpoints(Javalin app) {
		
		// Authenticate user and create a session for the user, sending username/password in the body as JSON
		app.post("/login", ctx -> authController.authenticate(ctx));

		// Register a new user, sending username/password in the body as JSON
		app.post("/register", ctx -> authController.register(ctx));
		
		// Invalidate session
		app.post("/logout", ctx -> authController.invalidateSession(ctx));
		
		// Check for valid sessions
		// Throw a custom exception if a session is not valid
		// This exception will be handled separately
		app.before("/api/*", ctx -> {	
			if(!authController.verifySession(ctx)) {
				throw new NotLoggedInException();
			}
		});
		
		// Handling the exception when a session doesn't exist
		app.exception(NotLoggedInException.class, (e,ctx) -> {
			ctx.json(e.getMessage()).status(401);
		});
		
		
		// Get all Planets
		app.get("api/planets", ctx -> planetController.getAllPlanets(ctx));
		
		// Get a planet with matching name
		app.get("api/planet/{name}", ctx -> planetController.getPlanetByName(ctx));
		
		// Get a planet with matching ID
		app.get("api/planet/id/{id}", ctx -> planetController.getPlanetByID(ctx));
		
		// Get moons associated with a planet
		app.get("api/planet/{id}/moons", ctx -> moonController.getPlanetMoons(ctx));
		
		// Get all moons
		app.get("api/moons", ctx -> moonController.getAllMoons(ctx));
		
		// Get a moon with matching name
		app.get("api/moon/{name}", ctx -> moonController.getMoonByName(ctx));
		
		// Get a moon with matching ID
		app.get("api/moon/id/{id}", ctx -> moonController.getMoonById(ctx));
		

		// Create a new planet, sending the data in the body as JSON
		app.post("api/planet", ctx -> planetController.createPlanet(ctx));
		
		// Create a new moon, sending the data in the body as JSON
		app.post("api/moon", ctx -> moonController.createMoon(ctx));
		

		// Delete a planet and all of its moons
		app.delete("api/planet/{id}", ctx -> planetController.deletePlanet(ctx));
		
		// Delete a moon
		app.delete("api/moon/{id}", ctx -> moonController.deleteMoon(ctx));
	}
}
