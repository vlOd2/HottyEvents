package me.vlod.hottyevents.test;

import me.vlod.hottyevents.Event;
import me.vlod.hottyevents.EventSender;

public class HottyEventsTest {
	public static void main(String[] args) {
		EventSender<Event> sender = new EventSender<Event>();
		System.out.println("Adding a FooBarEventListener");
		sender.addListener(new FooBarEventListener());
		System.out.println("Adding a FooBarEventListener");
		sender.addListener(new FooBarEventListener());
		
		FooEvent foo = new FooEvent();
		BarEvent bar = new BarEvent();
		System.out.println("Sending foo event");
		sender.send(foo);
		System.out.println("Sending bar event");
		sender.send(bar);
		System.out.println("Is foo cancelled: " + foo.getCancelled());
		System.out.println("Is bar cancelled: " + bar.getCancelled());
	}
}
