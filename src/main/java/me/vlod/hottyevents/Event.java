package me.vlod.hottyevents;

/**
 * A HottyEvents event
 * 
 * @author Vlad
 */
public abstract class Event {
	/**
	 * Gets the cancellation state of this event
	 * 
	 * @return the cancellation state
	 */
	public abstract boolean getCancelled();
	/**
	 * Sets the cancellation state of this event
	 * 
	 * @param cancelState the new cancellation state
	 */
	public abstract void setCancelled(boolean cancelState);
}
