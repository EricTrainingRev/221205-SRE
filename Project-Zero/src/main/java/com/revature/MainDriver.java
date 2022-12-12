package com.revature;


import com.revature.controller.RequestMapping;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		Javalin app = Javalin.create(confg ->{
			confg.plugins.enableDevLogging();
		});
		RequestMapping.setupEndpoints(app);
		app.start(7000);
	}
}
