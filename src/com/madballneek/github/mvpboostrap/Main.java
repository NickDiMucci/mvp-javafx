package com.madballneek.github.mvpboostrap;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		AppController appController = new AppController();
		appController.go(primaryStage);
	}


	public static void main(String[] args) {
		launch(args);
	}
}
