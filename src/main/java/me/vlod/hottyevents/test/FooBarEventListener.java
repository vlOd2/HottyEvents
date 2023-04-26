package me.vlod.hottyevents.test;

import me.vlod.hottyevents.EventHandler;
import me.vlod.hottyevents.EventListener;

public class FooBarEventListener implements EventListener {
	@EventHandler
	public void onFooEvent(FooEvent e) {
		System.out.println("[FooBarEventListener] Received a foo event!");
	}
	
	@EventHandler
	public void onBarEvent(BarEvent e) {
		System.out.println("[FooBarEventListener] Received a bar event!");
		System.out.println("[FooBarEventListener] Now cancelling it to prevent others from receiving it"
				+ " and let the sender know the action has been cancelled ;)");
		e.setCancelled(true);
	}
}
