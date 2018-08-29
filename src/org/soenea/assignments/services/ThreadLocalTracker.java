package org.soenea.assignments.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Provided anything ThreadLocal registers itself, this will set everything null for a given
 * thread, cleaning up all the ThreadLocals in case the Thread gets re-used. A must for dealing
 * with servlets.
 * @author sthiel
 *
 */
public class ThreadLocalTracker {

	private static Set<ThreadLocal> tracker = Collections.synchronizedSet(new HashSet<ThreadLocal>());
		
	public static void registerThreadLocal(ThreadLocal t) {
		tracker.add(t);
	}
	
	public static void purgeThreadLocal() {
		for(ThreadLocal<Object> t: tracker) {
			if(t==null) continue;
			t.set(null);
		}
	}
}
