package com.madballneek.github.mvpboostrap.module;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.madballneek.github.mvpboostrap.presenter.MessagePresenterImpl;
import com.madballneek.github.mvpboostrap.service.BlockingTaskManager;
import com.madballneek.github.mvpboostrap.service.TaskManager;
import com.madballneek.github.mvpboostrap.service.message.MessageService;
import com.madballneek.github.mvpboostrap.service.message.RudeMessageServiceImpl;
import com.madballneek.github.mvpboostrap.view.ExampleView;

/**
 * I hate Mondays. So much so, that I'm going to really make this application a chore to work with
 * by using a {@link BlockingTaskManager} and a {@link RudeMessageServiceImpl}.
 * Slow and a bad UX experience? That'll show them!
 */
public class BlockingRudeMessengerModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(TaskManager.class).to(BlockingTaskManager.class).in(Singleton.class);
		bind(EventBus.class).in(Singleton.class);
		bind(MessagePresenterImpl.View.class).to(ExampleView.class);
		bind(MessageService.class).to(RudeMessageServiceImpl.class);
	}
}
