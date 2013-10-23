package com.madballneek.github.mvpboostrap.module;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.madballneek.github.mvpboostrap.presenter.MessagePresenterImpl;
import com.madballneek.github.mvpboostrap.service.AsyncTaskManager;
import com.madballneek.github.mvpboostrap.service.TaskManager;
import com.madballneek.github.mvpboostrap.service.message.MessageService;
import com.madballneek.github.mvpboostrap.service.message.SimpleMessageServiceImpl;
import com.madballneek.github.mvpboostrap.view.ExampleView;

/**
 * A Google Guice module that defines the required dependencies needed when we create a MessagePresenterImpl.
 * This particular module will inject a <code>AsyncTaskManager</code>.
 * For each Interface required, we define the concrete class to bind it to.
 * Please see https://code.google.com/p/google-guice/wiki/GettingStarted for more info on Guice and Dependency Injection.
 */
public class AsyncSimpleMessengerModule extends AbstractModule {

	@Override
	protected void configure() {
		// This states "when I see a request for a class that implements the TaskManager interface,
		// I will provide the concrete implementation of AsyncSimpleMessengerModule".
		bind(TaskManager.class).to(AsyncTaskManager.class).in(Singleton.class);
		bind(EventBus.class).in(Singleton.class);
		bind(MessagePresenterImpl.View.class).to(ExampleView.class);
		bind(MessageService.class).to(SimpleMessageServiceImpl.class);
	}
}
