package me.vlod.hottyevents;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * A sender for {@link Event}s
 * 
 * @author Vlad
 * @param <T> the type of events to send, 
 * can also be {@link Event} for any events
 */
public class EventSender<T extends Event> {
	private LinkedList<EventListener> listeners = new LinkedList<EventListener>();
	
	/**
	 * Adds the specified listener to the listeners
	 *  that are notified when an event is sent<br>
	 * NOTE: The order of addition is NOT irrelevant
	 * 
	 * @param listener the listener to add
	 * @throws IllegalStateException when the listener has already been added
	 */
	public void addListener(EventListener listener) {
		if (this.listeners.contains(listener))
			throw new IllegalStateException();
		this.listeners.add(listener);
	}
	
	/**
	 * Removes the specified listener from the listeners
	 *  that are notified when an event is sent
	 * 
	 * @param listener the listener to remove
	 * @throws IllegalStateException when the listener has not been added
	 */
	public void removeListener(EventListener listener) {
		if (!this.listeners.contains(listener))
			throw new IllegalStateException();
		this.listeners.remove(listener);
	}

	/**
	 * Sends the specified event to all listeners
	 * 
	 * @param event the event to send
	 * @return any exception that might've been thrown, null on success
	 */
	public Exception send(T event) {
		// TODO: Proper error handling LMAO
		Exception exception = null;
		
		try {
			for (EventListener listener : this.listeners.toArray(new EventListener[0])) {
				this.sendToListener(listener, event);
			}	
		} catch (Exception ex) {
			exception = ex;
		}
		
		return exception;
	}
	
	/**
	 * Internal method for sending an event to the specified listener
	 * 
	 * @param listener the listener to send the event to
	 * @param event the event to send
	 * @throws IllegalAccessException see {@link java.lang.reflect.Method#invoke(Object, Object...)}
	 * @throws IllegalArgumentException see {@link java.lang.reflect.Method#invoke(Object, Object...)}
	 * @throws InvocationTargetException see {@link java.lang.reflect.Method#invoke(Object, Object...)}
	 */
	private void sendToListener(EventListener listener, T event) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = listener.getClass().getDeclaredMethods();
		
		for (Method method : methods) {
			// Check if the event has been cancelled
			if (event.getCancelled()) return;
			
			// Set the method as accessible
			method.setAccessible(true);
			
			// Try to get the EventHandler annotation of the method
			EventHandler annotation = method.getAnnotation(EventHandler.class);
			// Check if the method has an EventHandler annotation, 
			// only takes one parameter and that parameter extends T
			if (annotation != null && 
				method.getParameterCount() == 1 && 
				event.getClass().isAssignableFrom(method.getParameterTypes()[0])) {
				method.invoke(listener, event);
			}
		}
	}
}
