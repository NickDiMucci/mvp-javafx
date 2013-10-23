package com.madballneek.github.mvpboostrap.view;

import com.madballneek.github.mvpboostrap.presenter.MessagePresenterImpl;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * A barebones view with some widgets.
 */
public class ExampleView extends VBox implements MessagePresenterImpl.View {
	private Label serviceResonse;
	private TextField name;
	private Button sayHello;
	private Button sayGoodbye;
	private ProgressBar progressBar;

	public ExampleView() {
		buildView();
	}

	public void buildView() {
		setSpacing(10);

		Label header = new Label("JavaFX MVP");
		getChildren().add(header);

		name = new TextField();
		getChildren().add(name);

		sayHello = new Button("Say Hello");
		getChildren().add(sayHello);

		sayGoodbye = new Button("Say Goodbye");
		getChildren().add(sayGoodbye);

		serviceResonse = new Label();
		getChildren().add(serviceResonse);
	}

	@Override
	public Label getServiceResponse() {
		return serviceResonse;
	}

	@Override
	public TextField getName() {
		return name;
	}

	@Override
	public Button getSayHello() {
		return sayHello;
	}

	@Override
	public Button getSayGoodbye() {
		return sayGoodbye;
	}
}
