package com.madballneek.github.mvpboostrap.presenter;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;
import com.madballneek.github.mvpboostrap.service.TaskManager;
import com.madballneek.github.mvpboostrap.service.message.MessageService;
import com.madballneek.github.mvpboostrap.service.task.SayGoodByeTask;
import com.madballneek.github.mvpboostrap.service.task.SayHelloTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * <code>MessagePresenter</code> implementation. Binds the view's event handlers and passes the necessary model to the
 * view as well. We also submit {@link com.madballneek.github.mvpboostrap.service.task.ServiceTask}s to the
 * {@link MessageService} here as well.
 */
public class MessagePresenterImpl implements Presenter {
	private TaskManager taskManager;
	private EventBus eventBus;
	private View view;
	private MessageService messageService;

	@Inject
	public MessagePresenterImpl(TaskManager taskManager, EventBus eventBus, View exampleView, MessageService messageService) {
		this.taskManager = taskManager;
		this.eventBus = eventBus;
		this.eventBus.register(this);
		this.view = exampleView;
		this.messageService = messageService;
	}

	@Override
	public void go(Stage stage) {
		bind();
		stage.setScene(new Scene((Parent) view, 300, 275));
		stage.setTitle("Message App");
		stage.show();
	}

	public void bind() {
		view.getSayHello().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sayHello();
			}
		});
		view.getSayGoodbye().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sayGoodbye();
			}
		});
	}

	public void sayHello() {
		MessageRequestData messageRequestData = new MessageRequestData(view.getName().getText());
		taskManager.submitNewTask(new SayHelloTask(eventBus, messageService, messageRequestData));
	}

	public void sayGoodbye() {
		MessageRequestData messageRequestData = new MessageRequestData(view.getName().getText());
		taskManager.submitNewTask(new SayGoodByeTask(eventBus, messageService, messageRequestData));
	}

	@Subscribe
	public void onMessageServiceResponse(MessageResponseData messageResponseData) {
		view.getServiceResponse().setText(messageResponseData.message);
	}

	public interface View {
		public Label getServiceResponse();

		public TextField getName();

		public Button getSayHello();

		public Button getSayGoodbye();
	}
}
