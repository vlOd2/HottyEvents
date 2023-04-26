package me.vlod.hottyevents;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks a method in an {@link EventListener} as an event handler
 * 
 * @author Vlad
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface EventHandler {
	/**
	 * !!! NOT YET IMPLEMENTED !!!<br>
	 * <br>
	 * The priority of this event handler, 
	 * see {@link EventHandlerPriority} for more information
	 */
	public EventHandlerPriority priority() default EventHandlerPriority.LOW;
}
