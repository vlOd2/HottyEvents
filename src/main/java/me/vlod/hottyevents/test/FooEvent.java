package me.vlod.hottyevents.test;

import me.vlod.hottyevents.Event;

public class FooEvent extends Event {
	private boolean cancelled;
	
	@Override
	public boolean getCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelState) {
		this.cancelled = cancelState;
	}
}
